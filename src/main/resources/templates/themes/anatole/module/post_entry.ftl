<#list posts.content as post>
    <div class="post animated fadeInDown">
        <div class="post-title">
            <h4>
                <a href="javascript:openNewWin('${context!}/archives/${post.url}', '${post.title}')">${post.title}</a>
            </h4>
        </div>
        <#if post.thumbnail?length gt 0>
            <div class="post-thumbnail">
                <img src="${post.thumbnail}">
            </div>
        </#if>
        <div class="post-content">
            <div class="p_part">
                <p>${post.summary!}...</p>
            </div>
            <div class="p_part">
                <p></p>
            </div>
        </div>
        <div class="post-footer">
            <div class="meta">
                <div class="info">
                    <i class="fa fa-sun-o"></i>
                    <span class="date">${post.createTime?string("yyyy-MM-dd")}</span>
                    <i class="fa fa-comment-o"></i>
                    <a href="${context!}/archives/${post.url}#comment_widget">Comments</a>
                    <#if post.tags?size gt 0>
                        <i class="fa fa-tag"></i>
                        <#list post.tags as tag>
                            <a href="${context!}/tags/${tag.slugName}" class="tag">&nbsp;${tag.name}</a>
                        </#list>
                    </#if>
                    <#if post.brandId?length gt 0>
                        <i class="fa fa-tag"></i>
                        <a href="${context!}/album?bId=${post.brandId}" class="tag">${post.brandName}</a>
                    </#if>
                </div>
            </div>
        </div>
    </div>
</#list>
