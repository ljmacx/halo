package run.halo.app.model.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car_brand")
@ToString
@EqualsAndHashCode(callSuper = true)
public class CarBrand extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", columnDefinition = "varchar(32) not null")
    private String name;

    @Column(name = "slug_name", columnDefinition = "varchar(32) not null")
    private String slugName;


    @Override
    protected void prePersist() {
        super.prePersist();
        id = null;

        if (name == null) {
            name = "";
        }

        if (slugName == null) {
            slugName = "";
        }
    }
}
