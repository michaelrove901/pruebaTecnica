package credibanco.credibanco.assessment.card.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "transaccion")
public class TransaccionEntity {
    @Id
    @Column(nullable = false, unique = true)
    private Long id;
    private String estado;
    private LocalDateTime fechaCreacion;
    private Long tarjeta;

    private Float totalCompra;

    private String direccion;

    public TransaccionEntity() {
    }

    public TransaccionEntity(Long id, String estado, LocalDateTime fechaCreacion, Long tarjeta, Float totalCompra, String direccion) {
        this.id = id;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
        this.tarjeta = tarjeta;
        this.totalCompra = totalCompra;
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Long getTarjeta() {
        return tarjeta;
    }

    public void setTarjeta(Long tarjeta) {
        this.tarjeta = tarjeta;
    }

    public Float getTotalCompra() {
        return totalCompra;
    }

    public void setTotalCompra(Float totalCompra) {
        this.totalCompra = totalCompra;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}

