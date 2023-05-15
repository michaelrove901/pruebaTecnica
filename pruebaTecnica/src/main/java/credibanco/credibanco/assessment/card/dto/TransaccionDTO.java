package credibanco.credibanco.assessment.card.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransaccionDTO {
    private Long id;
    private String estado;
    private LocalDateTime fechaCreacion;
    private Long tarjeta;

    private Float totalCompra;

    private String direccion;


}
