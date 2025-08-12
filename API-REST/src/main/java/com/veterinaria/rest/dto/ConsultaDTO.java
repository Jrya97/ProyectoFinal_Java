package com.veterinaria.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConsultaDTO {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("fecha")
    private String fecha;
    
    @JsonProperty("motivo")
    private String motivo;
    
    @JsonProperty("diagnostico")
    private String diagnostico;
    
    @JsonProperty("tratamiento")
    private String tratamiento;
    
    @JsonProperty("mascotaId")
    private Long mascotaId;

    // Constructor vacío
    public ConsultaDTO() {}

    // Constructor con parámetros
    public ConsultaDTO(Long id, String fecha, String motivo, String diagnostico, String tratamiento, Long mascotaId) {
        this.id = id;
        this.fecha = fecha;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.mascotaId = mascotaId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public Long getMascotaId() {
        return mascotaId;
    }

    public void setMascotaId(Long mascotaId) {
        this.mascotaId = mascotaId;
    }
}