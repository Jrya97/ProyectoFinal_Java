package com.veterinaria.endpoint;

import com.veterinaria.entity.Mascota;
import com.veterinaria.service.MascotaService;
import com.veterinaria.mascotas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Endpoint
public class MascotaEndpoint {
    
    private static final String NAMESPACE_URI = "http://veterinaria.com/mascotas";
    
    @Autowired
    private MascotaService mascotaService;
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMascotasRequest")
    @ResponsePayload
    public GetMascotasResponse getMascotas(@RequestPayload GetMascotasRequest request) {
        GetMascotasResponse response = new GetMascotasResponse();
        List<Mascota> mascotas = mascotaService.getAllMascotas();
        
        for (Mascota mascota : mascotas) {
            response.getMascota().add(convertToSoapMascota(mascota));
        }
        
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getMascotaRequest")
    @ResponsePayload
    public GetMascotaResponse getMascota(@RequestPayload GetMascotaRequest request) {
        GetMascotaResponse response = new GetMascotaResponse();
        Optional<Mascota> mascota = mascotaService.getMascotaById(request.getId());
        
        if (mascota.isPresent()) {
            response.setMascota(convertToSoapMascota(mascota.get()));
        } else {
            throw new RuntimeException("Mascota no encontrada con id: " + request.getId());
        }
        
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createMascotaRequest")
    @ResponsePayload
    public CreateMascotaResponse createMascota(@RequestPayload CreateMascotaRequest request) {
        CreateMascotaResponse response = new CreateMascotaResponse();
        
        Mascota mascota = convertFromSoapMascotaInput(request.getMascota());
        Mascota savedMascota = mascotaService.createMascota(mascota);
        
        response.setMascota(convertToSoapMascota(savedMascota));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateMascotaRequest")
    @ResponsePayload
    public UpdateMascotaResponse updateMascota(@RequestPayload UpdateMascotaRequest request) {
        UpdateMascotaResponse response = new UpdateMascotaResponse();
        
        Mascota mascota = convertFromSoapMascotaInput(request.getMascota());
        Mascota updatedMascota = mascotaService.updateMascota(request.getId(), mascota);
        
        response.setMascota(convertToSoapMascota(updatedMascota));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMascotaRequest")
    @ResponsePayload
    public DeleteMascotaResponse deleteMascota(@RequestPayload DeleteMascotaRequest request) {
        DeleteMascotaResponse response = new DeleteMascotaResponse();
        
        boolean deleted = mascotaService.deleteMascota(request.getId());
        response.setSuccess(deleted);
        
        return response;
    }
    
    private com.veterinaria.mascotas.Mascota convertToSoapMascota(Mascota entity) {
        com.veterinaria.mascotas.Mascota soapMascota = new com.veterinaria.mascotas.Mascota();
        soapMascota.setIdMascota(entity.getIdMascota());
        soapMascota.setIdPropietario(entity.getIdPropietario());
        soapMascota.setNombre(entity.getNombre());
        soapMascota.setEspecie(entity.getEspecie());
        soapMascota.setRaza(entity.getRaza());
        
        if (entity.getFechaNacimiento() != null) {
            soapMascota.setFechaNacimiento(convertLocalDateToXMLGregorianCalendar(entity.getFechaNacimiento()));
        }
        
        if (entity.getPeso() != null) {
            soapMascota.setPeso(entity.getPeso());
        }
        
        if (entity.getSexo() != null) {
            soapMascota.setSexo(Sexo.fromValue(entity.getSexo().name()));
        }
        
        if (entity.getFechaRegistro() != null) {
            soapMascota.setFechaRegistro(convertToXMLGregorianCalendar(entity.getFechaRegistro()));
        }
        
        return soapMascota;
    }
    
    private Mascota convertFromSoapMascotaInput(MascotaInput soapInput) {
        Mascota mascota = new Mascota();
        mascota.setIdPropietario(soapInput.getIdPropietario());
        mascota.setNombre(soapInput.getNombre());
        mascota.setEspecie(soapInput.getEspecie());
        mascota.setRaza(soapInput.getRaza());
        
        if (soapInput.getFechaNacimiento() != null) {
            mascota.setFechaNacimiento(soapInput.getFechaNacimiento().toGregorianCalendar().toZonedDateTime().toLocalDate());
        }
        
        if (soapInput.getPeso() != null) {
            mascota.setPeso(soapInput.getPeso());
        }
        
        if (soapInput.getSexo() != null) {
            mascota.setSexo(Mascota.Sexo.valueOf(soapInput.getSexo().value()));
        }
        
        return mascota;
    }
    
    private XMLGregorianCalendar convertToXMLGregorianCalendar(LocalDateTime localDateTime) {
        try {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDateTime.atZone(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Error converting LocalDateTime to XMLGregorianCalendar", e);
        }
    }
    
    private XMLGregorianCalendar convertLocalDateToXMLGregorianCalendar(LocalDate localDate) {
        try {
            return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(
                localDate.getYear(),
                localDate.getMonthValue(),
                localDate.getDayOfMonth(),
                javax.xml.datatype.DatatypeConstants.FIELD_UNDEFINED
            );
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Error converting LocalDate to XMLGregorianCalendar", e);
        }
    }
}