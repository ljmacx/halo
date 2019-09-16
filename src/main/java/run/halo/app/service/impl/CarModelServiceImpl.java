package run.halo.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import run.halo.app.exception.NotFoundException;
import run.halo.app.model.dto.CarModelDTO;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.entity.CarModel;
import run.halo.app.repository.CarModelRepository;
import run.halo.app.service.CarModelService;
import run.halo.app.service.base.AbstractCrudService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarModelServiceImpl extends AbstractCrudService<CarModel, Integer> implements CarModelService {

    private final CarModelRepository carModelRepository;

    public CarModelServiceImpl(CarModelRepository repository){
        super(repository);
        this.carModelRepository = repository;
    }

    @Override
    public CarModelDTO convertTo(CarModel model) {
        return new CarModelDTO().convertFrom(model);

    }

    @Override
    public List<CarModelDTO> convertTo(List<CarModel> models) {
        if (CollectionUtils.isEmpty(models)) {
            return Collections.emptyList();
        }

        return models.stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }

    @Override
    public List<CarModel> getListByBrandId(Integer brandId) {
        return carModelRepository.getByBrandId(brandId).orElseThrow(() -> new NotFoundException("该品牌不存在").setErrorData(brandId));
   }
}
