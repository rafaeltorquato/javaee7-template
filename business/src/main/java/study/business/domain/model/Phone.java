package study.business.domain.model;

import lombok.Getter;
import lombok.Setter;
import study.components.validation.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Getter
@Setter
public class Phone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 1, max = 3)
    @Column(length = 3, nullable = false)
    private String countryCode;
    @NotEmpty
    @Size(min = 1, max = 5)
    @Column(length = 5, nullable = false)
    private String areaCode;
    @NotEmpty
    @Size(min = 5, max = 10)
    @Column(length = 10, nullable = false)
    private String number;
    @Version
    @Column(nullable = false)
    private Integer version;
    @NotNull
    @ManyToOne
    private Person owner;

}
