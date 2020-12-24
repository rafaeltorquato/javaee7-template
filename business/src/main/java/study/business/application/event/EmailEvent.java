package study.business.application.event;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmailEvent implements Serializable {
    private String email;
    private String subject;
    private String message;
}
