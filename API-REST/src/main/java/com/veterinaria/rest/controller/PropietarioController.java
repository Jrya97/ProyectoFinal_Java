package com.veterinaria.rest.controller;

import com.veterinaria.rest.dto.PropietarioDTO;
import com.veterinaria.rest.service.PropietarioSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/propietarios")
@CrossOrigin(origins = "*")
public class PropietarioController {

    @Autowired
    private PropietarioSoapService propietarioSoapService;

    @GetMapping
    public ResponseEntity<List<PropietarioDTO>> getAllPropietarios() {
        try {
            List<PropietarioDTO> propietarios = propietarioSoapService.getAllPropietarios();
            return ResponseEntity.ok(propietarios);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropietarioDTO> getPropietarioById(@PathVariable Long id) {
        try {
            PropietarioDTO propietario = propietarioSoapService.getPropietarioById(id);
            return ResponseEntity.ok(propietario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<PropietarioDTO> createPropietario(@RequestBody PropietarioDTO propietarioDTO) {
        try {
            PropietarioDTO nuevoPropietario = propietarioSoapService.createPropietario(propietarioDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPropietario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropietarioDTO> updatePropietario(@PathVariable Long id, @RequestBody PropietarioDTO propietarioDTO) {
        try {
            PropietarioDTO propietarioActualizado = propietarioSoapService.updatePropietario(id, propietarioDTO);
            return ResponseEntity.ok(propietarioActualizado);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePropietario(@PathVariable Long id) {
        try {
            boolean eliminado = propietarioSoapService.deletePropietario(id);
            if (eliminado) {
                return ResponseEntity.noContent().build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}