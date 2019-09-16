package run.halo.app.repository;

import org.springframework.stereotype.Repository;
import run.halo.app.model.entity.CarModel;
import run.halo.app.repository.base.BaseRepository;

@Repository
public interface CarModelRepository extends BaseRepository<CarModel, Integer> {

}
