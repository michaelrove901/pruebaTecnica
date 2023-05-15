package credibanco.credibanco.assessment.card.service;

import credibanco.credibanco.assessment.card.dto.*;

import java.util.List;

public interface TransaccionService {

    List<TransaccionDTO> getAllTransaccion();
    TransaccionDTO getTransaccionDto(Long id);
    ResponseEntityDTO<RespuestaCrearTransaccionDTO> createTransaccion(TransaccionDTO transaccionDTO);

    ResponseEntityDTO<RespuestaCrearTransaccionDTO> anularTransaccion(TransaccionDTO transaccionDTO);
}
