package run.halo.app.model.dto;

import lombok.Data;
import run.halo.app.model.dto.base.OutputConverter;
import run.halo.app.model.entity.CarModel;

import java.util.Date;

@Data
public class CarModelDTO implements OutputConverter<CarModelDTO, CarModel> {
    private Integer id;

    private String name;

    private String slugName;

    private String description;

    private Integer brandId;

    private Date createTime;
}
