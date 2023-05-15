package credibanco.credibanco.assessment.card.controller;


import credibanco.credibanco.assessment.card.dto.*;
import credibanco.credibanco.assessment.card.service.TarjetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/tarjetas")
public class TarjetaController {
    @Autowired
    private TarjetaService tarjetaService;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public List<TarjetaDTO> getAllTarjetas() {
        return tarjetaService.getAllTarjetaDtos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarjetaResponseDTO> getTarjetaById(@PathVariable Long id) {
        return new ResponseEntity<>(tarjetaService.getTarjetaDto(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseEntityDTO<RespuestaCrearTarjetaDTO>> createTarjeta(@Valid @RequestBody TarjetaDTO tarjetaDTO) {
        HttpStatus estado = HttpStatus.CREATED;
        ResponseEntityDTO<RespuestaCrearTarjetaDTO> response= ResponseEntityDTO
                .<RespuestaCrearTarjetaDTO>builder()
                .mensaje("Éxito")
                .code(String.valueOf(estado.value()))
                .data((RespuestaCrearTarjetaDTO)tarjetaService.createTarjeta(tarjetaDTO)).build();
        return new ResponseEntity<>(response,estado);
    }

    @PutMapping("/{id}")
    public void updateTarjeta(@PathVariable Long id, @RequestBody TarjetaDTO tarjetaDTO) {
        tarjetaDTO.setId(id);
        tarjetaService.updateTarjeta(tarjetaDTO);
    }

    @DeleteMapping
    public ResponseEntity<ResponseEntityDTO<EliminarRespuestaDTO>> eliminarTarjeta(@RequestBody TarjetaDTO tarjetaDTO) {
        tarjetaService.deleteTarjeta(tarjetaDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/enrolar")
    public ResponseEntity<ResponseEntityDTO<RespuestaEnrolarTarjeta>> enrolarTarjeta(@RequestBody TarjetaDTO tarjetaDTO) {
        HttpStatus estado = HttpStatus.OK;
        ResponseEntityDTO<RespuestaEnrolarTarjeta> response= ResponseEntityDTO
                .<RespuestaEnrolarTarjeta>builder()
                .mensaje("Éxito")
                .code(String.valueOf(estado.value()))
                .data((RespuestaEnrolarTarjeta)tarjetaService.enrolarTarjeta(tarjetaDTO)).build();
        return new ResponseEntity<>(response,estado);

    }


}