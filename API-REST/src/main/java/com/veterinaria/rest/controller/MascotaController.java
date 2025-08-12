package com.veterinaria.rest.controller;

import com.veterinaria.rest.dto.MascotaDTO;
import com.veterinaria.rest.service.MascotaSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/mascotas")
@CrossOrigin(origins = "*")
public class MascotaController {

    @Autowired
    private MascotaSoapService mascotaSoapService;

    @GetMapping
    public ResponseEntity<List<MascotaDTO>> getAllMascotas() {
        try {
            List<MascotaDTO> mascotas = mascotaSoapService.getAllMascotas();
            return ResponseEntity.ok(mascotas);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MascotaDTO> getMascotaById(@PathVariable Long id) {
        try {
            MascotaDTO mascota = mascotaSoapService.getMascotaById(id);
            return ResponseEntity.ok(mascota);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping
    public ResponseEntity<MascotaDTO> createMascota(@RequestBody MascotaDTO mascotaDTO) {
        try {
            MascotaDTO nuevaMascota = mascotaSoapService.createMascota(mascotaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaMascota);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<MascotaDTO> updateMascota(@PathVariable Long id, @RequestBody MascotaDTO mascotaDTO) {
        try {
            MascotaDTO mascotaActualizada = mascotaSoapService.updateMascota(id, mascotaDTO);
            return ResponseEntity.ok(mascotaActualizada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMascota(@PathVariable Long id) {
        try {
            boolean eliminada = mascotaSoapService.deleteMascota(id);
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