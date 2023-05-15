package credibanco.credibanco.assessment.card.model;


import javax.persistence.*;

@Entity
@Table(name = "tarjeta")
public class TarjetaEntity {
    @Id
    @Column(nullable = false,unique = true)
    private Long id;
    private String titular;
    private String cedula;
    private String tipo;
    private Long telefono;
    private String numeroValidacion;
    private String status;

    public TarjetaEntity() {
    }

    public TarjetaEntity(Long id, String titular, String cedula, String tipo, Long telefono, String numeroValidacion, String status) {
        this.id = id;
        this.titular = titular;
        this.cedula = cedula;
        this.tipo = tipo;
        this.telefono = telefono;
        this.numeroValidacion = numeroValidacion;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public String getNumeroValidacion() {
        return numeroValidacion;
    }

    public void setNumeroValidacion(String numeroValidacion) {
        this.numeroValidacion = numeroValidacion;
    }


}