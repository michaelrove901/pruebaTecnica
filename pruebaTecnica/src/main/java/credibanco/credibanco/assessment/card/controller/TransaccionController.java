package credibanco.credibanco.assessment.card.controller;


import credibanco.credibanco.assessment.card.dto.*;
import credibanco.credibanco.assessment.card.service.TarjetaService;
import credibanco.credibanco.assessment.card.service.TransaccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/transacciones")
public class TransaccionController {
    @Autowired
    private TarjetaService tarjetaService;
    @Autowired
    private TransaccionService transaccionService;
    @GetMapping
    public List<TransaccionDTO> getAllTransacciones() {
        return transaccionService.getAllTransaccion();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransaccionDTO> getTransaccionById(@PathVariable Long id) {
        return new ResponseEntity<>(transaccionService.getTransaccionDto(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ResponseEntityDTO<RespuestaCrearTransaccionDTO>> createTransaccion(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return new ResponseEntity<>(transaccionService.createTransaccion(transaccionDTO),HttpStatus.CREATED);
    }

    @PostMapping("/anular")
    public ResponseEntity<ResponseEntityDTO<RespuestaCrearTransaccionDTO>> anularTransaccion(@Valid @RequestBody TransaccionDTO transaccionDTO) {
        return new ResponseEntity<>(transaccionService.anularTransaccion(transaccionDTO),HttpStatus.CREATED);
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