package run.halo.app.service;

import org.springframework.lang.Nullable;
import run.halo.app.model.dto.CarBrandDTO;
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.CarBrand;
import run.halo.app.model.entity.Tag;
import run.halo.app.service.base.CrudService;

import java.util.List;

public interface CarBrandService extends CrudService<CarBrand, Integer> {
    public CarBrandDTO convertTo(CarBrand carBrand);
    List<CarBrandDTO> convertTo(@Nullable List<CarBrand> carBrands);
}
