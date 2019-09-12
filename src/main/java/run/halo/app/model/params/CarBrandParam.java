package run.halo.app.model.params;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.CarBrand;
import run.halo.app.model.entity.Tag;
import run.halo.app.utils.HaloUtils;
import run.halo.app.utils.SlugUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CarBrandParam implements InputConverter<CarBrand> {

    @NotBlank(message = "品牌名称不能为空")
    @Size(max = 255, message = "品牌名称的字符长度不能超过 {max}")
    private String name;

    @Size(max = 255, message = "标签别名的字符长度不能超过 {max}")
    private String slugName;

    @Override
    public CarBrand convertTo() {
        if (StringUtils.isBlank(slugName)) {
            // Handle slug name
            slugName = SlugUtils.slugify(name);
        }

        slugName = HaloUtils.initializeUrlIfBlank(slugName);

        return InputConverter.super.convertTo();
    }
}
