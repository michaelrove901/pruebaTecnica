package credibanco.credibanco.assessment.card.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  ResponseEntityDTO <T>{
    private String code;
    private T data;
    private String mensaje;

}
