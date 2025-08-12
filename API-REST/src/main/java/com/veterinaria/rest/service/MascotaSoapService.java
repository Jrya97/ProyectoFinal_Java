package com.veterinaria.rest.service;

import com.veterinaria.rest.dto.MascotaDTO;
import com.veterinaria.rest.soap.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.time.LocalDate;
import java.time.Period;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

@Service
public class MascotaSoapService {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    private final ObjectFactory objectFactory = new ObjectFactory();

    public List<MascotaDTO> getAllMascotas() {
        GetMascotasRequest request = objectFactory.createGetMascotasRequest();
        GetMascotasResponse response = (GetMascotasResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return response.getMascota().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public MascotaDTO getMascotaById(Long id) {
        GetMascotaRequest request = objectFactory.createGetMascotaRequest();
        request.setId(id.intValue());
        GetMascotaResponse response = (GetMascotaResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getMascota());
    }

    public MascotaDTO createMascota(MascotaDTO mascotaDTO) {
        CreateMascotaRequest request = objectFactory.createCreateMascotaRequest();
        MascotaInput mascotaInput = objectFactory.createMascotaInput();
        
        mascotaInput.setNombre(mascotaDTO.getNombre());
        mascotaInput.setEspecie(mascotaDTO.getEspecie());
        mascotaInput.setRaza(mascotaDTO.getRaza());
        
        // Convertir edad a fecha de nacimiento
        if (mascotaDTO.getEdad() != null && mascotaDTO.getEdad() > 0) {
            try {
                LocalDate fechaNacimiento = LocalDate.now().minusYears(mascotaDTO.getEdad());
                GregorianCalendar gc = GregorianCalendar.from(fechaNacimiento.atStartOfDay().atZone(java.time.ZoneId.systemDefault()));
                XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                mascotaInput.setFechaNacimiento(xmlDate);
            } catch (Exception e) {
                // Si hay error, no se establece fecha de nacimiento
            }
        }
        
        mascotaInput.setIdPropietario(mascotaDTO.getPropietarioId().intValue());
        
        request.setMascota(mascotaInput);
        CreateMascotaResponse response = (CreateMascotaResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getMascota());
    }

    public MascotaDTO updateMascota(Long id, MascotaDTO mascotaDTO) {
        UpdateMascotaRequest request = objectFactory.createUpdateMascotaRequest();
        request.setId(id.intValue());
        
        MascotaInput mascotaInput = objectFactory.createMascotaInput();
        mascotaInput.setNombre(mascotaDTO.getNombre());
        mascotaInput.setEspecie(mascotaDTO.getEspecie());
        mascotaInput.setRaza(mascotaDTO.getRaza());
        
        // Convertir edad a fecha de nacimiento
        if (mascotaDTO.getEdad() != null && mascotaDTO.getEdad() > 0) {
            try {
                LocalDate fechaNacimiento = LocalDate.now().minusYears(mascotaDTO.getEdad());
                GregorianCalendar gc = GregorianCalendar.from(fechaNacimiento.atStartOfDay().atZone(java.time.ZoneId.systemDefault()));
                XMLGregorianCalendar xmlDate = DatatypeFactory.newInstance().newXMLGregorianCalendar(gc);
                mascotaInput.setFechaNacimiento(xmlDate);
            } catch (Exception e) {
                // Si hay error, no se establece fecha de nacimiento
            }
        }
        
        mascotaInput.setIdPropietario(mascotaDTO.getPropietarioId().intValue());
        
        request.setMascota(mascotaInput);
        UpdateMascotaResponse response = (UpdateMascotaResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getMascota());
    }

    public boolean deleteMascota(Long id) {
        DeleteMascotaRequest request = objectFactory.createDeleteMascotaRequest();
        request.setId(id.intValue());
        DeleteMascotaResponse response = (DeleteMascotaResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return response.isSuccess();
    }

    private MascotaDTO convertToDTO(Mascota mascota) {
        Integer edad = null;
        if (mascota.getFechaNacimiento() != null) {
            try {
                LocalDate fechaNacimiento = mascota.getFechaNacimiento().toGregorianCalendar().toZonedDateTime().toLocalDate();
                edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
            } catch (Exception e) {
                // Si hay error al calcular la edad, se mantiene como null
                edad = null;
            }
        }
        
        return new MascotaDTO(
                (long) mascota.getIdMascota(),
                mascota.getNombre(),
                mascota.getEspecie(),
                mascota.getRaza(),
                edad,
                (long) mascota.getIdPropietario()
        );
    }
}