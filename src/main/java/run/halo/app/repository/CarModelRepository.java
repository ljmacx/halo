package run.halo.app.repository;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import run.halo.app.model.entity.CarModel;
import run.halo.app.model.entity.Tag;
import run.halo.app.repository.base.BaseRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository extends BaseRepository<CarModel, Integer> {
    Optional<List<CarModel>> getByBrandId(@NonNull Integer brandId);
}
