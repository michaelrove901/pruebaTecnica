package credibanco.credibanco.assessment.card.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaDTO {
    @NotNull
    private Long id;
    private String titular;
    @NotNull
    @Size(min = 10, max = 15)
    private String cedula;
    private String tipo;
    @NotNull
    @Size(min = 10, max = 10)
    private String telefono;
    private String numeroValidacion;
    private String status;
    private String enmascaradoPAN;

}
