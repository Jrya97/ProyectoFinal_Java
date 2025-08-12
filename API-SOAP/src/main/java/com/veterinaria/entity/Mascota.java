package com.veterinaria.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mascotas")
public class Mascota {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mascota")
    private Integer idMascota;
    
    @Column(name = "id_propietario", nullable = false)
    private Integer idPropietario;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "especie", nullable = false, length = 50)
    private String especie;
    
    @Column(name = "raza", length = 100)
    private String raza;
    
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    
    @Column(name = "peso", precision = 5, scale = 2)
    private BigDecimal peso;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sexo")
    private Sexo sexo;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propietario", insertable = false, updatable = false)
    private Propietario propietario;
    
    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Consulta> consultas;
    
    public enum Sexo {
        Macho, Hembra
    }
    
    public Mascota() {
        this.fechaRegistro = LocalDateTime.now();
    }
    
    public Mascota(Integer idPropietario, String nombre, String especie, String raza, 
                   LocalDate fechaNacimiento, BigDecimal peso, Sexo sexo) {
        this.idPropietario = idPropietario;
        this.nombre = nombre;
        this.especie = especie;
        this.raza = raza;
        this.fechaNacimiento = fechaNacimiento;
        this.peso = peso;
        this.sexo = sexo;
        this.fechaRegistro = LocalDateTime.now();
    }
    
    // Getters y Setters
    public Integer getIdMascota() {
        return idMascota;
    }
    
    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }
    
    public Integer getIdPropietario() {
        return idPropietario;
    }
    
    public void setIdPropietario(Integer idPropietario) {
        this.idPropietario = idPropietario;
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
    
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    
    public BigDecimal getPeso() {
        return peso;
    }
    
    public void setPeso(BigDecimal peso) {
        this.peso = peso;
    }
    
    public Sexo getSexo() {
        return sexo;
    }
    
    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
    
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public Propietario getPropietario() {
        return propietario;
    }
    
    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }
    
    public List<Consulta> getConsultas() {
        return consultas;
    }
    
    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }
}