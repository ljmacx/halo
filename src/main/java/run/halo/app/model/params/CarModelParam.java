package run.halo.app.model.params;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import run.halo.app.model.dto.base.InputConverter;
import run.halo.app.model.entity.CarModel;
import run.halo.app.model.entity.Category;
import run.halo.app.utils.HaloUtils;
import run.halo.app.utils.SlugUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CarModelParam implements InputConverter<CarModel> {
    @NotBlank(message = "车型名称不能为空")
    @Size(max = 50, message = "车型名称的字符长度不能超过 {max}")
    private String name;

    /**
     * Parent category.
     */
    private Integer brandId = 0;

    @Override
    public CarModel convertTo() {

        return InputConverter.super.convertTo();
    }
}
