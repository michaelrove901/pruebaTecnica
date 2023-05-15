package credibanco.credibanco.assessment.card.service.impl;

import credibanco.credibanco.assessment.card.dto.*;
import credibanco.credibanco.assessment.card.exceptions.exceptions.CodigoTransaccionNotFound;
import credibanco.credibanco.assessment.card.exceptions.exceptions.TarjetaNotFoundException;
import credibanco.credibanco.assessment.card.model.TarjetaEntity;
import credibanco.credibanco.assessment.card.model.TransaccionEntity;
import credibanco.credibanco.assessment.card.repository.TarjetaRepository;
import credibanco.credibanco.assessment.card.repository.TransaccionRepository;
import credibanco.credibanco.assessment.card.service.TransaccionService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TransaccionServiceImpl implements TransaccionService {
    @Autowired
    private TransaccionRepository transaccionRepository;
    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<TransaccionDTO> getAllTransaccion() {
        List<TransaccionEntity> transacciones = transaccionRepository.findAll();
        List<TransaccionDTO> transaccionDTOs = new ArrayList<>();

        for (TransaccionEntity transaccion : transacciones) {
            TransaccionDTO transaccionDTO = modelMapper.map(transaccion, TransaccionDTO.class);
            transaccionDTOs.add(transaccionDTO);
        }
        return transaccionDTOs;
    }

    @Override
    public TransaccionDTO getTransaccionDto(Long id) {
        TransaccionEntity transaccion = transaccionRepository.findById(id).orElseThrow(() -> new CodigoTransaccionNotFound("Codigo De Transaccion No Encontrado"));
        return modelMapper.map(transaccion, TransaccionDTO.class);
    }


    @Override
    public ResponseEntityDTO<RespuestaCrearTransaccionDTO> createTransaccion(TransaccionDTO transaccionDTO) {
        TransaccionEntity transaccion = convertToEntity(transaccionDTO);
        Optional<TarjetaEntity> transaccione = tarjetaRepository.findById(transaccionDTO.getTarjeta());
        String mensaje = "Compra exitosa";
        HttpStatus estado = HttpStatus.CREATED;
        if (transaccione==null) {
            transaccion.setEstado("Rechazada");
            mensaje = "Tarjeta no existe";
            estado = HttpStatus.NOT_ACCEPTABLE;

        } else {
            if (!transaccione.get().getStatus().equalsIgnoreCase("Enrolada")) {
                transaccion.setEstado("Rechazada");
                mensaje = " Tarjeta no Enrolada";
                estado = HttpStatus.ACCEPTED;
            } else {
                transaccion.setEstado("Aprobada");
            }
        }
        transaccion.setFechaCreacion(LocalDateTime.now());
        RespuestaCrearTransaccionDTO respuestaCrearTransaccionDTO = modelMapper.map(transaccionRepository.save(transaccion), RespuestaCrearTransaccionDTO.class);

        ResponseEntityDTO<RespuestaCrearTransaccionDTO> response = ResponseEntityDTO
                .<RespuestaCrearTransaccionDTO>builder()
                .mensaje(mensaje)
                .code(String.valueOf(estado.value()))
                .data((RespuestaCrearTransaccionDTO) respuestaCrearTransaccionDTO).build();
        return response;
    }

    public ResponseEntityDTO<RespuestaCrearTransaccionDTO> anularTransaccion(TransaccionDTO transaccionDTO) {
        TransaccionEntity transaccion = convertToEntity(transaccionDTO);
        TransaccionEntity datosTransaccion = transaccionRepository.findById(transaccionDTO.getId()).orElseThrow(() -> new CodigoTransaccionNotFound("Numero de referencia invalido"));
        String mensaje = "";
        HttpStatus estado = HttpStatus.ACCEPTED;


            if (datosTransaccion.getTarjeta().equals(transaccionDTO.getTarjeta())) {
                LocalDateTime ahora = LocalDateTime.now();
                LocalDateTime fechaCreacion = datosTransaccion.getFechaCreacion();
                Duration duracion = Duration.between(fechaCreacion, ahora);
                if (duracion.toMinutes() >= 5) {
                    mensaje = "No se puede anular transacción";
                    estado = HttpStatus.NOT_ACCEPTABLE;
                } else {
                    mensaje = "Transaccion Anulada";
                    transaccion.setEstado("Anulada");
                }
            } else {
                mensaje = "El numero de tarjeta no coincide";
                estado = HttpStatus.NOT_FOUND;
            }


        RespuestaCrearTransaccionDTO respuestaCrearTransaccionDTO = modelMapper.map(transaccionRepository.save(datosTransaccion), RespuestaCrearTransaccionDTO.class);

        ResponseEntityDTO<RespuestaCrearTransaccionDTO> response = ResponseEntityDTO
                .<RespuestaCrearTransaccionDTO>builder()
                .mensaje(mensaje)
                .code(String.valueOf(estado.value()))
                .data((RespuestaCrearTransaccionDTO) respuestaCrearTransaccionDTO).build();
        return response;
    }

    private TransaccionEntity convertToEntity(TransaccionDTO transaccionDTO) {
        if (transaccionDTO == null) {
            return null;
        }
        TransaccionEntity transaccion = new TransaccionEntity();
        transaccion.setId(transaccionDTO.getId());
        transaccion.setEstado(transaccionDTO.getEstado());
        transaccion.setFechaCreacion(transaccionDTO.getFechaCreacion());
        transaccion.setTarjeta(transaccionDTO.getTarjeta());
        transaccion.setTotalCompra(transaccionDTO.getTotalCompra());
        transaccion.setDireccion(transaccionDTO.getDireccion());
        return transaccion;
    }

}
