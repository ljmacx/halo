package run.halo.app.repository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import run.halo.app.model.entity.CarBrand;
import run.halo.app.repository.base.BaseRepository;

@Repository
public interface CarBrandRepository extends BaseRepository<CarBrand, Integer> {

//    long listAll();
    long countByNameOrSlugName(@NonNull String name, @NonNull String slugName);

}
