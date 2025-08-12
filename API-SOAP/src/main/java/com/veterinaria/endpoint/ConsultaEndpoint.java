package com.veterinaria.endpoint;

import com.veterinaria.entity.Consulta;
import com.veterinaria.service.ConsultaService;
import com.veterinaria.consultas.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

@Endpoint
public class ConsultaEndpoint {
    
    private static final String NAMESPACE_URI = "http://veterinaria.com/consultas";
    
    @Autowired
    private ConsultaService consultaService;
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getConsultasRequest")
    @ResponsePayload
    public GetConsultasResponse getConsultas(@RequestPayload GetConsultasRequest request) {
        GetConsultasResponse response = new GetConsultasResponse();
        List<Consulta> consultas = consultaService.getAllConsultas();
        
        for (Consulta consulta : consultas) {
            response.getConsulta().add(convertToSoapConsulta(consulta));
        }
        
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getConsultaRequest")
    @ResponsePayload
    public GetConsultaResponse getConsulta(@RequestPayload GetConsultaRequest request) {
        GetConsultaResponse response = new GetConsultaResponse();
        Optional<Consulta> consulta = consultaService.getConsultaById(request.getId());
        
        if (consulta.isPresent()) {
            response.setConsulta(convertToSoapConsulta(consulta.get()));
        } else {
            throw new RuntimeException("Consulta no encontrada con id: " + request.getId());
        }
        
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createConsultaRequest")
    @ResponsePayload
    public CreateConsultaResponse createConsulta(@RequestPayload CreateConsultaRequest request) {
        CreateConsultaResponse response = new CreateConsultaResponse();
        
        Consulta consulta = convertFromSoapConsultaInput(request.getConsulta());
        Consulta savedConsulta = consultaService.createConsulta(consulta);
        
        response.setConsulta(convertToSoapConsulta(savedConsulta));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateConsultaRequest")
    @ResponsePayload
    public UpdateConsultaResponse updateConsulta(@RequestPayload UpdateConsultaRequest request) {
        UpdateConsultaResponse response = new UpdateConsultaResponse();
        
        Consulta consulta = convertFromSoapConsultaInput(request.getConsulta());
        Consulta updatedConsulta = consultaService.updateConsulta(request.getId(), consulta);
        
        response.setConsulta(convertToSoapConsulta(updatedConsulta));
        return response;
    }
    
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteConsultaRequest")
    @ResponsePayload
    public DeleteConsultaResponse deleteConsulta(@RequestPayload DeleteConsultaRequest request) {
        DeleteConsultaResponse response = new DeleteConsultaResponse();
        
        boolean deleted = consultaService.deleteConsulta(request.getId());
        response.setSuccess(deleted);
        
        return response;
    }
    
    private com.veterinaria.consultas.Consulta convertToSoapConsulta(Consulta entity) {
        com.veterinaria.consultas.Consulta soapConsulta = new com.veterinaria.consultas.Consulta();
        soapConsulta.setIdConsulta(entity.getIdConsulta());
        soapConsulta.setIdMascota(entity.getIdMascota());
        
        if (entity.getFechaConsulta() != null) {
            soapConsulta.setFechaConsulta(convertToXMLGregorianCalendar(entity.getFechaConsulta()));
        }
        
        soapConsulta.setMotivo(entity.getMotivo());
        soapConsulta.setDiagnostico(entity.getDiagnostico());
        soapConsulta.setTratamiento(entity.getTratamiento());
        soapConsulta.setObservaciones(entity.getObservaciones());
        
        if (entity.getCosto() != null) {
            soapConsulta.setCosto(entity.getCosto());
        }
        
        return soapConsulta;
    }
    
    private Consulta convertFromSoapConsultaInput(ConsultaInput soapInput) {
        Consulta consulta = new Consulta();
        consulta.setIdMascota(soapInput.getIdMascota());
        
        if (soapInput.getFechaConsulta() != null) {
            consulta.setFechaConsulta(soapInput.getFechaConsulta().toGregorianCalendar().toZonedDateTime().toLocalDateTime());
        }
        
        consulta.setMotivo(soapInput.getMotivo());
        consulta.setDiagnostico(soapInput.getDiagnostico());
        consulta.setTratamiento(soapInput.getTratamiento());
        consulta.setObservaciones(soapInput.getObservaciones());
        
        if (soapInput.getCosto() != null) {
            consulta.setCosto(soapInput.getCosto());
        }
        
        return consulta;
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