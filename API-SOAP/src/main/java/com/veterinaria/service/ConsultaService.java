package com.veterinaria.service;

import com.veterinaria.entity.Consulta;
import com.veterinaria.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {
    
    @Autowired
    private ConsultaRepository consultaRepository;
    
    public List<Consulta> getAllConsultas() {
        return consultaRepository.findAll();
    }
    
    public Optional<Consulta> getConsultaById(Integer id) {
        return consultaRepository.findById(id);
    }
    
    public List<Consulta> getConsultasByMascota(Integer idMascota) {
        return consultaRepository.findByIdMascota(idMascota);
    }
    
    public Consulta createConsulta(Consulta consulta) {
        return consultaRepository.save(consulta);
    }
    
    public Consulta updateConsulta(Integer id, Consulta consultaDetails) {
        Optional<Consulta> optionalConsulta = consultaRepository.findById(id);
        if (optionalConsulta.isPresent()) {
            Consulta consulta = optionalConsulta.get();
            consulta.setIdMascota(consultaDetails.getIdMascota());
            consulta.setFechaConsulta(consultaDetails.getFechaConsulta());
            consulta.setMotivo(consultaDetails.getMotivo());
            consulta.setDiagnostico(consultaDetails.getDiagnostico());
            consulta.setTratamiento(consultaDetails.getTratamiento());
            consulta.setObservaciones(consultaDetails.getObservaciones());
            consulta.setCosto(consultaDetails.getCosto());
            return consultaRepository.save(consulta);
        }
        throw new RuntimeException("Consulta no encontrada con id: " + id);
    }
    
    public boolean deleteConsulta(Integer id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}