package run.halo.app.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car_model")
@ToString
@EqualsAndHashCode(callSuper = true)
public class CarModel extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(50) not null")
    private String name;

    @Column(name = "description", columnDefinition = "varchar(100) default ''")
    private String description;

    @Column(name = "brand_id", columnDefinition = "int default 0")
    private Integer brandId;

    @Override
    protected void prePersist() {
        id = null;

        if (description == null) {
            description = "";
        }

        if (name == null) {
            name = "";
        }
        if (brandId == null || brandId < 0) {
            brandId = 0;
        }
    }
}
