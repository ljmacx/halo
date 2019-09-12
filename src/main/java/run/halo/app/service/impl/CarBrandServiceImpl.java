package run.halo.app.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import run.halo.app.exception.AlreadyExistsException;
import run.halo.app.model.dto.CarBrandDTO;
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.CarBrand;
import run.halo.app.model.entity.Tag;
import run.halo.app.repository.CarBrandRepository;
import run.halo.app.service.CarBrandService;
import run.halo.app.service.base.AbstractCrudService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CarBrandServiceImpl extends AbstractCrudService<CarBrand, Integer> implements CarBrandService {

    private final CarBrandRepository carBrandRepository;
    public CarBrandServiceImpl(CarBrandRepository carBrandRepository){
        super(carBrandRepository);
        this.carBrandRepository = carBrandRepository;
    };

    @Override
    public CarBrand create(CarBrand carBrand) {
        long count = carBrandRepository.countByNameOrSlugName(carBrand.getName(), carBrand.getSlugName());
        if (count > 0) {
            throw new AlreadyExistsException("该品牌已存在").setErrorData( carBrand);
        }
        return super.create(carBrand);
    }

    @Override
    public CarBrandDTO convertTo(CarBrand carBrand) {
        Assert.notNull(carBrand, "Tag must not be null");

        return new CarBrandDTO().convertFrom(carBrand);
    }

    @Override
    public List<CarBrandDTO> convertTo(List<CarBrand> carBrands) {
        if (CollectionUtils.isEmpty(carBrands)) {
            return Collections.emptyList();
        }

        return carBrands.stream()
                .map(this::convertTo)
                .collect(Collectors.toList());
    }
}
