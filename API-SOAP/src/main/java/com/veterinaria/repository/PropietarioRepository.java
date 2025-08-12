package com.veterinaria.repository;

import com.veterinaria.entity.Propietario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropietarioRepository extends JpaRepository<Propietario, Integer> {
    // Métodos adicionales personalizados pueden ser agregados aquí si es necesario
}