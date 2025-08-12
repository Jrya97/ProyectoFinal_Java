package com.veterinaria.rest.controller;

import com.veterinaria.rest.dto.ConsultaDTO;
import com.veterinaria.rest.service.ConsultaSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consultas")
@CrossOrigin(origins = "*")
public class ConsultaController {

    @Autowired
    private ConsultaSoapService consultaSoapService;

    @GetMapping
    public ResponseEntity<List<ConsultaDTO>> getAllConsultas() {
        try {
            List<ConsultaDTO> consultas = consultaSoapService.getAllConsultas();
            return ResponseEntity.ok(consultas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultaDTO> getConsultaById(@PathVariable Long id) {
        try {
            ConsultaDTO consulta = consultaSoapService.getConsultaById(id);
            return ResponseEntity.ok(consulta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<ConsultaDTO> createConsulta(@RequestBody ConsultaDTO consultaDTO) {
        try {
            ConsultaDTO nuevaConsulta = consultaSoapService.createConsulta(consultaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaConsulta);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultaDTO> updateConsulta(@PathVariable Long id, @RequestBody ConsultaDTO consultaDTO) {
        try {
            ConsultaDTO consultaActualizada = consultaSoapService.updateConsulta(id, consultaDTO);
            return ResponseEntity.ok(consultaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsulta(@PathVariable Long id) {
        try {
            boolean eliminada = consultaSoapService.deleteConsulta(id);
            if (eliminada) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}