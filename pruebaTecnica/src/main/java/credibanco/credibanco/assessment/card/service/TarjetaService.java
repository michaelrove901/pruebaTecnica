package credibanco.credibanco.assessment.card.service;

import credibanco.credibanco.assessment.card.dto.RespuestaCrearTarjetaDTO;
import credibanco.credibanco.assessment.card.dto.RespuestaEnrolarTarjeta;
import credibanco.credibanco.assessment.card.dto.TarjetaDTO;
import credibanco.credibanco.assessment.card.dto.TarjetaResponseDTO;
import credibanco.credibanco.assessment.card.repository.TarjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface TarjetaService {
    List<TarjetaDTO> getAllTarjetaDtos();
    TarjetaResponseDTO getTarjetaDto(Long id);
    RespuestaCrearTarjetaDTO createTarjeta(TarjetaDTO tarjetaDTO);
    void updateTarjeta(TarjetaDTO tarjetaDTO);
    void deleteTarjeta(TarjetaDTO tarjetaDTO);
    RespuestaEnrolarTarjeta enrolarTarjeta(TarjetaDTO tarjeta);

}
