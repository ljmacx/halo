package run.halo.app.controller.content;

import cn.hutool.core.util.PageUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import run.halo.app.model.entity.Category;
import run.halo.app.model.entity.Post;
import run.halo.app.model.enums.PostStatus;
import run.halo.app.model.params.PostQuery;
import run.halo.app.model.vo.PostListVO;
import run.halo.app.service.CategoryService;
import run.halo.app.service.OptionService;
import run.halo.app.service.PostService;
import run.halo.app.service.ThemeService;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Slf4j
@Controller
@RequestMapping("/carnews")
public class ContentCarNewsController {

    private final PostService postService;
    private final OptionService optionService;
    private final ThemeService themeService;
    private final CategoryService categoryService;

    public ContentCarNewsController(PostService postService,
                                    OptionService optionService,
                                    ThemeService themeService,
                                    CategoryService categoryService) {
        this.postService = postService;
        this.optionService = optionService;
        this.themeService = themeService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public String newsIndex(Model model) {
        return this.index(model, 1, Sort.by(DESC, "topPriority").and(Sort.by(DESC, "createTime")));
    }

    @GetMapping(value = "page/{page}")
    public String index(Model model,
                        @PathVariable(value = "page") Integer page,
                        @SortDefault.SortDefaults({
                                @SortDefault(sort = "topPriority", direction = DESC),
                                @SortDefault(sort = "createTime", direction = DESC)
                        }) Sort sort) {
        log.debug("Requested index page, sort info: [{}]", sort);
        int pageSize = optionService.getPostPageSize();
        Pageable pageable = PageRequest.of(page >= 1 ? page - 1 : page, pageSize, sort);

        //Page<Post> postPage = postService.pageBy(PostStatus.PUBLISHED, pageable);
        PostQuery query = new PostQuery();
        Category category = categoryService.getByName("carnews");
        query.setCategoryId(category.getId());
        Page<Post> postPage = postService.pageBy(query, pageable);

        Page<PostListVO> posts = postService.convertToListVo(postPage);

        int[] rainbow = PageUtil.rainbow(page, posts.getTotalPages(), 3);

        model.addAttribute("is_index", false);
        model.addAttribute("posts", posts);
        model.addAttribute("rainbow", rainbow);
        return themeService.render("index");
    }
}
