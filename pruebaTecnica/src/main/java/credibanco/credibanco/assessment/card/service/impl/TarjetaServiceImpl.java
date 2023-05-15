package credibanco.credibanco.assessment.card.service.impl;

import credibanco.credibanco.assessment.card.dto.*;
import credibanco.credibanco.assessment.card.exceptions.exceptions.CodigoTarjetaException;
import credibanco.credibanco.assessment.card.exceptions.exceptions.TarjetaNotFoundException;
import credibanco.credibanco.assessment.card.exceptions.exceptions.TarjetaValidaException;
import credibanco.credibanco.assessment.card.model.TarjetaEntity;
import credibanco.credibanco.assessment.card.model.TransaccionEntity;
import credibanco.credibanco.assessment.card.repository.TarjetaRepository;
import credibanco.credibanco.assessment.card.service.TarjetaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TarjetaServiceImpl implements TarjetaService {

    @Autowired
    private TarjetaRepository tarjetaRepository;

    @Autowired
    private ModelMapper modelMapper;


    public List<TarjetaDTO> getAllTarjetaDtos() {
        List<TarjetaEntity> tarjetas = tarjetaRepository.findAll();
        List<TarjetaDTO> tarjetaDTOs = new ArrayList<>();

        for (TarjetaEntity tarjeta : tarjetas) {
            TarjetaDTO tarjetaDTO = modelMapper.map(tarjeta, TarjetaDTO.class);
            tarjetaDTO.setEnmascaradoPAN(enmascararPAN(tarjeta.getId()).concat(rellenoPAN()));
            tarjetaDTOs.add(tarjetaDTO);
        }

        return tarjetaDTOs;
    }

    @Override
    public TarjetaResponseDTO getTarjetaDto(Long id) {
        TarjetaEntity tarjeta = tarjetaRepository.findById(id).orElseThrow(() -> new TarjetaNotFoundException("Tarjeta no encontrada"));
        return modelMapper.map(tarjeta, TarjetaResponseDTO.class);
    }

    @Override
    public RespuestaCrearTarjetaDTO createTarjeta(TarjetaDTO tarjetaDTO) {
        TarjetaEntity tarjeta = convertToEntity(tarjetaDTO);
        int TamañoDeCaracteresId;
        TamañoDeCaracteresId = Long.toString(tarjeta.getId()).length();
        if (TamañoDeCaracteresId < 16 || TamañoDeCaracteresId > 19) {
            throw new TarjetaValidaException("Error numero de caracteres de la tarjeta no validos");
        }
        int numero = (int) (Math.random() * 100) + 1;
        Formatter obj = new Formatter();
        String numeroCeros = String.valueOf(obj.format("%03d", numero));
        tarjeta.setNumeroValidacion(numeroCeros);
        tarjeta.setStatus("Creada");
        tarjeta.setTelefono(Long.parseLong(tarjetaDTO.getTelefono()));
        tarjeta.setTipo(tarjetaDTO.getTipo());
        RespuestaCrearTarjetaDTO respuestaCrearTarjetaDTO=modelMapper.map(tarjetaRepository.save(tarjeta),RespuestaCrearTarjetaDTO.class);
        respuestaCrearTarjetaDTO.setId(enmascararPAN(tarjeta.getId()).concat(rellenoPAN()));
        return respuestaCrearTarjetaDTO;

    }

    @Override
    public void updateTarjeta(TarjetaDTO tarjetaDTO) {
        TarjetaEntity tarjeta = convertToEntity(tarjetaDTO);
        tarjetaRepository.save(tarjeta);
    }

    @Override
    public void deleteTarjeta(TarjetaDTO tarjetaDTO) {
        TarjetaEntity tarjeta = tarjetaRepository.findById(tarjetaDTO.getId()).orElseThrow(() -> new TarjetaNotFoundException("Tarjeta no encontrada para borrar"));
        if (tarjeta.getNumeroValidacion().equals(tarjetaDTO.getNumeroValidacion())) {
            tarjetaRepository.deleteById(tarjeta.getId());
        } else {
            throw new CodigoTarjetaException("Número de validación inválido");
        }

    }

    public RespuestaEnrolarTarjeta enrolarTarjeta(TarjetaDTO tarjetaDTO) {
        TarjetaEntity tarjeta = tarjetaRepository.findById(tarjetaDTO.getId()).orElseThrow(() -> new TarjetaNotFoundException("Tarjeta no existe"));
        if (tarjeta.getNumeroValidacion().equals(tarjetaDTO.getNumeroValidacion())) {
            tarjeta.setStatus("Enrolada");
            RespuestaEnrolarTarjeta respuestaEnrolarTarjeta=modelMapper.map(tarjetaRepository.save(tarjeta),RespuestaEnrolarTarjeta.class);
            respuestaEnrolarTarjeta.setId(enmascararPAN(tarjeta.getId()).concat(rellenoPAN()));
            return respuestaEnrolarTarjeta;
        } else {
            throw new CodigoTarjetaException("Número de validación inválido");
        }

    }


    private TarjetaEntity convertToEntity(TarjetaDTO tarjetaDTO) {
        if (tarjetaDTO == null) {
            return null;
        }
        TarjetaEntity tarjeta = new TarjetaEntity();
        tarjeta.setId(tarjetaDTO.getId());
        tarjeta.setCedula(tarjetaDTO.getCedula());
        tarjeta.setTitular(tarjetaDTO.getTitular());
        return tarjeta;
    }



    private String enmascararPAN(Long id) {
        String PAMEncriptado = String.valueOf(id);
        return PAMEncriptado.substring(0, 6).concat(PAMEncriptado.substring(7).replaceAll("[0-9]", "*"));
    }

    private String rellenoPAN() {
        int numero = (int) (Math.random() * 1000) + 1;
        Formatter obj = new Formatter();
        String numeroCeros = String.valueOf(obj.format("%04d", numero));
        return numeroCeros;
    }
}
