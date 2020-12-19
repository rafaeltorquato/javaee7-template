package study.business.domain.model.person;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import study.components.validation.NotEmpty;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonName implements Serializable {

    @NotEmpty
    private String name;
    @NotEmpty
    private String surname;

}
