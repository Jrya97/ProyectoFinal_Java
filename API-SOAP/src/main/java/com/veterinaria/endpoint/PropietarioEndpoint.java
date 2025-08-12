package com.veterinaria.endpoint;

import com.veterinaria.entity.Propietario;
import com.veterinaria.service.PropietarioService;
import com.veterinaria.propietarios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Endpoint
public class PropietarioEndpoint {
    
    private static final String NAMESPACE_URI = "http://veterinaria.com/propietarios";
    
    @Autowired
    private PropietarioService propietarioService;
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPropietariosRequest")
    @ResponsePayload
    public GetPropietariosResponse getPropietarios(@RequestPayload GetPropietariosRequest request) {
        GetPropietariosResponse response = new GetPropietariosResponse();
        List<Propietario> propietarios = propietarioService.getAllPropietarios();
        
        for (Propietario propietario : propietarios) {
            response.getPropietario().add(convertToSoapPropietario(propietario));
        }
        
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPropietarioRequest")
    @ResponsePayload
    public GetPropietarioResponse getPropietario(@RequestPayload GetPropietarioRequest request) {
        GetPropietarioResponse response = new GetPropietarioResponse();
        Optional<Propietario> propietario = propietarioService.getPropietarioById(request.getId());
        
        if (propietario.isPresent()) {
            response.setPropietario(convertToSoapPropietario(propietario.get()));
        } else {
            throw new RuntimeException("Propietario no encontrado con id: " + request.getId());
        }
        
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createPropietarioRequest")
    @ResponsePayload
    public CreatePropietarioResponse createPropietario(@RequestPayload CreatePropietarioRequest request) {
        CreatePropietarioResponse response = new CreatePropietarioResponse();
        
        Propietario propietario = convertFromSoapPropietarioInput(request.getPropietario());
        Propietario savedPropietario = propietarioService.createPropietario(propietario);
        
        response.setPropietario(convertToSoapPropietario(savedPropietario));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updatePropietarioRequest")
    @ResponsePayload
    public UpdatePropietarioResponse updatePropietario(@RequestPayload UpdatePropietarioRequest request) {
        UpdatePropietarioResponse response = new UpdatePropietarioResponse();
        
        Propietario propietario = convertFromSoapPropietarioInput(request.getPropietario());
        Propietario updatedPropietario = propietarioService.updatePropietario(request.getId(), propietario);
        
        response.setPropietario(convertToSoapPropietario(updatedPropietario));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deletePropietarioRequest")
    @ResponsePayload
    public DeletePropietarioResponse deletePropietario(@RequestPayload DeletePropietarioRequest request) {
        DeletePropietarioResponse response = new DeletePropietarioResponse();
        
        boolean deleted = propietarioService.deletePropietario(request.getId());
        response.setSuccess(deleted);
        
        return response;
    }
    
    private com.veterinaria.propietarios.Propietario convertToSoapPropietario(Propietario entity) {
        com.veterinaria.propietarios.Propietario soapPropietario = new com.veterinaria.propietarios.Propietario();
        soapPropietario.setIdPropietario(entity.getIdPropietario());
        soapPropietario.setNombre(entity.getNombre());
        soapPropietario.setApellido(entity.getApellido());
        soapPropietario.setTelefono(entity.getTelefono());
        soapPropietario.setEmail(entity.getEmail());
        soapPropietario.setDireccion(entity.getDireccion());
        
        if (entity.getFechaRegistro() != null) {
            soapPropietario.setFechaRegistro(convertToXMLGregorianCalendar(entity.getFechaRegistro()));
        }
        
        return soapPropietario;
    }
    
    private Propietario convertFromSoapPropietarioInput(PropietarioInput soapInput) {
        Propietario propietario = new Propietario();
        propietario.setNombre(soapInput.getNombre());
        propietario.setApellido(soapInput.getApellido());
        propietario.setTelefono(soapInput.getTelefono());
        propietario.setEmail(soapInput.getEmail());
        propietario.setDireccion(soapInput.getDireccion());
        return propietario;
    }
    
    private XMLGregorianCalendar convertToXMLGregorianCalendar(LocalDateTime localDateTime) {
        try {
            GregorianCalendar gregorianCalendar = GregorianCalendar.from(localDateTime.atZone(ZoneId.systemDefault()));
            return DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCalendar);
        } catch (DatatypeConfigurationException e) {
            throw new RuntimeException("Error converting LocalDateTime to XMLGregorianCalendar", e);
        }
    }
}