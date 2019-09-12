package run.halo.app.model.dto;

import lombok.Data;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.CarBrand;

import java.util.Date;

@Data
public class CarBrandDTO implements OutputConverter<CarBrandDTO, CarBrand> {

    private Integer id;

    private String name;

    private String slugName;

    private Date createTime;
}
