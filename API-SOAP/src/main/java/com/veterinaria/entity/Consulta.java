package com.veterinaria.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
public class Consulta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")
    private Integer idConsulta;
    
    @Column(name = "id_mascota", nullable = false)
    private Integer idMascota;
    
    @Column(name = "fecha_consulta", nullable = false)
    private LocalDateTime fechaConsulta;
    
    @Column(name = "motivo", nullable = false, length = 200)
    private String motivo;
    
    @Column(name = "diagnostico", columnDefinition = "TEXT")
    private String diagnostico;
    
    @Column(name = "tratamiento", columnDefinition = "TEXT")
    private String tratamiento;
    
    @Column(name = "observaciones", columnDefinition = "TEXT")
    private String observaciones;
    
    @Column(name = "costo", precision = 10, scale = 2)
    private BigDecimal costo;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_mascota", insertable = false, updatable = false)
    private Mascota mascota;
    
    // Constructores
    public Consulta() {}
    
    public Consulta(Integer idMascota, LocalDateTime fechaConsulta, String motivo, 
                   String diagnostico, String tratamiento, String observaciones, BigDecimal costo) {
        this.idMascota = idMascota;
        this.fechaConsulta = fechaConsulta;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
        this.costo = costo;
    }
    
    // Getters y Setters
    public Integer getIdConsulta() {
        return idConsulta;
    }
    
    public void setIdConsulta(Integer idConsulta) {
        this.idConsulta = idConsulta;
    }
    
    public Integer getIdMascota() {
        return idMascota;
    }
    
    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }
    
    public LocalDateTime getFechaConsulta() {
        return fechaConsulta;
    }
    
    public void setFechaConsulta(LocalDateTime fechaConsulta) {
        this.fechaConsulta = fechaConsulta;
    }
    
    public String getMotivo() {
        return motivo;
    }
    
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }
    
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
    
    public String getTratamiento() {
        return tratamiento;
    }
    
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }
    
    public String getObservaciones() {
        return observaciones;
    }
    
    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    public BigDecimal getCosto() {
        return costo;
    }
    
    public void setCosto(BigDecimal costo) {
        this.costo = costo;
    }
    
    public Mascota getMascota() {
        return mascota;
    }
    
    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }
}