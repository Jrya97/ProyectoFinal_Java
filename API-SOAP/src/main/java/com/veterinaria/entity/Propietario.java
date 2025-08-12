package com.veterinaria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "propietarios")
public class Propietario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propietario")
    private Integer idPropietario;
    
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;
    
    @Column(name = "telefono", nullable = false, length = 20)
    private String telefono;
    
    @Column(name = "email", length = 100)
    private String email;
    
    @Column(name = "direccion", length = 200)
    private String direccion;
    
    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;
    
    @OneToMany(mappedBy = "propietario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Mascota> mascotas;
    
    public Propietario() {
        this.fechaRegistro = LocalDateTime.now();
    }
    
    public Propietario(String nombre, String apellido, String telefono, String email, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.fechaRegistro = LocalDateTime.now();
    }
    
    // Getters y Setters
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
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getTelefono() {
        return telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getDireccion() {
        return direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }
    
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
    public List<Mascota> getMascotas() {
        return mascotas;
    }
    
    public void setMascotas(List<Mascota> mascotas) {
        this.mascotas = mascotas;
    }
}