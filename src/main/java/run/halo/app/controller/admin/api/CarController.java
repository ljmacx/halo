package run.halo.app.controller.admin.api;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import run.halo.app.model.dto.CarBrandDTO;
import run.halo.app.model.dto.TagDTO;
import run.halo.app.model.entity.CarBrand;
import run.halo.app.model.entity.Tag;
import run.halo.app.model.params.CarBrandParam;
import run.halo.app.model.params.TagParam;
import run.halo.app.service.CarBrandService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/admin/carinfo")
public class CarController {
    private final CarBrandService carBrandService;

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
}
