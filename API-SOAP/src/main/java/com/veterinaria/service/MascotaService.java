package com.veterinaria.service;

import com.veterinaria.entity.Mascota;
import com.veterinaria.repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MascotaService {
    
    @Autowired
    private MascotaRepository mascotaRepository;
    
    public List<Mascota> getAllMascotas() {
        return mascotaRepository.findAll();
    }
    
    public Optional<Mascota> getMascotaById(Integer id) {
        return mascotaRepository.findById(id);
    }
    
    public List<Mascota> getMascotasByPropietario(Integer idPropietario) {
        return mascotaRepository.findByIdPropietario(idPropietario);
    }
    
    public Mascota createMascota(Mascota mascota) {
        return mascotaRepository.save(mascota);
    }
    
    public Mascota updateMascota(Integer id, Mascota mascotaDetails) {
        Optional<Mascota> optionalMascota = mascotaRepository.findById(id);
        if (optionalMascota.isPresent()) {
            Mascota mascota = optionalMascota.get();
            mascota.setIdPropietario(mascotaDetails.getIdPropietario());
            mascota.setNombre(mascotaDetails.getNombre());
            mascota.setEspecie(mascotaDetails.getEspecie());
            mascota.setRaza(mascotaDetails.getRaza());
            mascota.setFechaNacimiento(mascotaDetails.getFechaNacimiento());
            mascota.setPeso(mascotaDetails.getPeso());
            mascota.setSexo(mascotaDetails.getSexo());
            return mascotaRepository.save(mascota);
        }
        throw new RuntimeException("Mascota no encontrada con id: " + id);
    }
    
    public boolean deleteMascota(Integer id) {
        if (mascotaRepository.existsById(id)) {
            mascotaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}