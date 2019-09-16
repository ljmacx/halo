package run.halo.app.service;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import run.halo.app.model.dto.CarModelDTO;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.entity.CarModel;
import run.halo.app.model.entity.Category;
import run.halo.app.service.base.AbstractCrudService;
import run.halo.app.service.base.CrudService;

import java.util.List;

public interface CarModelService extends CrudService<CarModel, Integer> {
    CarModelDTO convertTo(@NonNull CarModel model);
    List<CarModelDTO> convertTo(@Nullable List<CarModel> models);
    List<CarModel> getListByBrandId(Integer brandId);
}
