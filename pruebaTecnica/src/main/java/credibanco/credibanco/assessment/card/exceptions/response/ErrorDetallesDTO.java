package credibanco.credibanco.assessment.card.exceptions.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDetallesDTO {

private String codigo;

private String mensaje;

private String path;


}
