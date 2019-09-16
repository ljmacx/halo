package run.halo.app.controller.admin.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import run.halo.app.model.dto.CarBrandDTO;
import run.halo.app.model.dto.CarModelDTO;
import run.halo.app.model.dto.CategoryDTO;
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.CarBrand;
import run.halo.app.model.entity.CarModel;
import run.halo.app.model.entity.Tag;
import run.halo.app.model.params.CarBrandParam;
import run.halo.app.model.params.CarModelParam;
import run.halo.app.model.params.TagParam;
import run.halo.app.service.CarBrandService;
import run.halo.app.service.CarModelService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.data.domain.Sort.Direction.DESC;

@Slf4j
@RestController
@RequestMapping("/api/admin/carinfo")
public class CarController {
    private final CarBrandService carBrandService;

    @Autowired
    CarModelService carModelService;

    public CarController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @GetMapping("/brand/list")
    public List<? extends CarBrandDTO> listBrands(@SortDefault(sort = "updateTime", direction = Sort.Direction.DESC) Sort sort) {
        return carBrandService.convertTo(carBrandService.listAll(sort));
    }

    @PostMapping("/brand/addBrand")
    public CarBrandDTO createCarBrand(@Valid @RequestBody CarBrandParam brandParam) {
        // Convert to tag
        CarBrand brand = brandParam.convertTo();

        log.debug("Tag to be created: [{}]", brand);

        // Create and convert
        return carBrandService.convertTo(carBrandService.create(brand));
    }

    @PutMapping("/brand/{brandId:\\d+}")
    public CarBrandDTO updateBy(@PathVariable("brandId") Integer brandId,
                           @Valid @RequestBody CarBrandParam brandParam) {
        // Get old tag
        CarBrand brand = carBrandService.getById(brandId);

        // Update tag
        brandParam.update(brand);

        // Update tag
        return carBrandService.convertTo(carBrandService.update(brand));
    }

    @DeleteMapping("/brand/{brandId:\\d+}")
    public CarBrandDTO deletePermanently(@PathVariable("brandId") Integer brandId) {
        // Remove the tag
        CarBrand deletedBrand = carBrandService.removeById(brandId);
        // Remove the post tag relationship
//        carBrandService.removeById(brandId);

        return carBrandService.convertTo(deletedBrand);
    }

    @GetMapping("/model/list")
    public List<? extends CarModelDTO> listAll(
            @SortDefault(sort = "updateTime", direction = DESC) Sort sort) {
        return carModelService.convertTo(carModelService.listAll(sort));
    }

    @GetMapping("/model/blist")
    public List<? extends CarModelDTO> listAll(@RequestParam("brandId") Integer brandId) {
        return carModelService.convertTo(carModelService.getListByBrandId(brandId));
    }

    @PostMapping("/model/addModel")
    public CarModelDTO createCarModel(@Valid @RequestBody CarModelParam modelParam) {
        // Convert to tag
        CarModel model = modelParam.convertTo();

        log.debug("Tag to be created: [{}]", model);

        // Create and convert
        return carModelService.convertTo(carModelService.create(model));
    }

    @PutMapping("/model/{modelId:\\d+}")
    public CarModelDTO updateBy(@PathVariable("modelId") Integer modelId,
                                @Valid @RequestBody CarModelParam modelParam) {
        // Get old tag
        CarModel model = carModelService.getById(modelId);

        // Update tag
        modelParam.update(model);

        // Update tag
        return carModelService.convertTo(carModelService.update(model));
    }

    @DeleteMapping("/model/{modelId:\\d+}")
    public CarModelDTO deleteModelPermanently(@PathVariable("modelId") Integer modelId) {
        // Remove the tag
        CarModel deletedModel = carModelService.removeById(modelId);
        // Remove the post tag relationship
//        carBrandService.removeById(brandId);
        return carModelService.convertTo(deletedModel);
    }
}
