package com.veterinaria.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MascotaDTO {
    
    @JsonProperty("id")
    private Long id;
    
    @JsonProperty("nombre")
    private String nombre;
    
    @JsonProperty("especie")
    private String especie;
    
    @JsonProperty("raza")
    private String raza;
    
    @JsonProperty("edad")
    private Integer edad;
    
    @JsonProperty("propietarioId")
    private Long propietarioId;

    // Constructor vacío
    public MascotaDTO() {}

    // Constructor con parámetros
    public MascotaDTO(Long id, String nombre, String especie, String raza, Integer edad, Long propietarioId) {
        this.id = id;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.edad = edad;
        this.propietarioId = propietarioId;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Long getPropietarioId() {
        return propietarioId;
    }

    public void setPropietarioId(Long propietarioId) {
        this.propietarioId = propietarioId;
    }
}