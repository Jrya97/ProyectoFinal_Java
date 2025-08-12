package com.veterinaria.service;

import com.veterinaria.entity.Propietario;
import com.veterinaria.repository.PropietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PropietarioService {
    
    @Autowired
    private PropietarioRepository propietarioRepository;
    
    public List<Propietario> getAllPropietarios() {
        return propietarioRepository.findAll();
    }
    
    public Optional<Propietario> getPropietarioById(Integer id) {
        return propietarioRepository.findById(id);
    }
    
    public Propietario createPropietario(Propietario propietario) {
        return propietarioRepository.save(propietario);
    }
    
    public Propietario updatePropietario(Integer id, Propietario propietarioDetails) {
        Optional<Propietario> optionalPropietario = propietarioRepository.findById(id);
        if (optionalPropietario.isPresent()) {
            Propietario propietario = optionalPropietario.get();
            propietario.setNombre(propietarioDetails.getNombre());
            propietario.setApellido(propietarioDetails.getApellido());
            propietario.setTelefono(propietarioDetails.getTelefono());
            propietario.setEmail(propietarioDetails.getEmail());
            propietario.setDireccion(propietarioDetails.getDireccion());
            return propietarioRepository.save(propietario);
        }
        throw new RuntimeException("Propietario no encontrado con id: " + id);
    }
    
    public boolean deletePropietario(Integer id) {
        if (propietarioRepository.existsById(id)) {
            propietarioRepository.deleteById(id);
            return true;
        }
        return false;
    }
}