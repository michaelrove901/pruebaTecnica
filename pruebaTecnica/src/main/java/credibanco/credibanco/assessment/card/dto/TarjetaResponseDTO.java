package credibanco.credibanco.assessment.card.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TarjetaResponseDTO {


    private String id;

    private String numeroValidacion;

    private String titular;

    private String cedula;

    private String telefono;

    private String status;
}
