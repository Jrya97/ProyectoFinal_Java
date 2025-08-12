package com.veterinaria.rest.service;

import com.veterinaria.rest.dto.ConsultaDTO;
import com.veterinaria.rest.soap.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConsultaSoapService {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    private final ObjectFactory objectFactory = new ObjectFactory();

    public List<ConsultaDTO> getAllConsultas() {
        GetConsultasRequest request = objectFactory.createGetConsultasRequest();
        GetConsultasResponse response = (GetConsultasResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return response.getConsulta().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ConsultaDTO getConsultaById(Long id) {
        GetConsultaRequest request = objectFactory.createGetConsultaRequest();
        request.setId(id.intValue());
        GetConsultaResponse response = (GetConsultaResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getConsulta());
    }

    public ConsultaDTO createConsulta(ConsultaDTO consultaDTO) {
        CreateConsultaRequest request = objectFactory.createCreateConsultaRequest();
        ConsultaInput consultaInput = objectFactory.createConsultaInput();
        
        // Convertir fecha de String a XMLGregorianCalendar
        try {
            XMLGregorianCalendar fechaXML;
            if (consultaDTO.getFecha() != null && !consultaDTO.getFecha().isEmpty()) {
                // Parsear la fecha del DTO
                LocalDateTime fechaLocal = LocalDateTime.parse(consultaDTO.getFecha() + "T00:00:00");
                GregorianCalendar calendar = GregorianCalendar.from(fechaLocal.atZone(java.time.ZoneId.systemDefault()));
                fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } else {
                // Si no hay fecha, usar fecha actual
                fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
            }
            consultaInput.setFechaConsulta(fechaXML);
        } catch (Exception e) {
            // Si hay error con la fecha, usar fecha actual
            try {
                XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
                consultaInput.setFechaConsulta(fechaXML);
            } catch (Exception ex) {
                throw new RuntimeException("Error al procesar fecha", ex);
            }
        }
        consultaInput.setMotivo(consultaDTO.getMotivo());
        consultaInput.setDiagnostico(consultaDTO.getDiagnostico());
        consultaInput.setTratamiento(consultaDTO.getTratamiento());
        consultaInput.setIdMascota(consultaDTO.getMascotaId().intValue());
        
        request.setConsulta(consultaInput);
        CreateConsultaResponse response = (CreateConsultaResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getConsulta());
    }

    public ConsultaDTO updateConsulta(Long id, ConsultaDTO consultaDTO) {
        UpdateConsultaRequest request = objectFactory.createUpdateConsultaRequest();
        request.setId(id.intValue());
        
        ConsultaInput consultaInput = objectFactory.createConsultaInput();
        // Convertir fecha de String a XMLGregorianCalendar
        try {
            XMLGregorianCalendar fechaXML;
            if (consultaDTO.getFecha() != null && !consultaDTO.getFecha().isEmpty()) {
                // Parsear la fecha del DTO
                LocalDateTime fechaLocal = LocalDateTime.parse(consultaDTO.getFecha() + "T00:00:00");
                GregorianCalendar calendar = GregorianCalendar.from(fechaLocal.atZone(java.time.ZoneId.systemDefault()));
                fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
            } else {
                // Si no hay fecha, usar fecha actual
                fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
            }
            consultaInput.setFechaConsulta(fechaXML);
        } catch (Exception e) {
            // Si hay error con la fecha, usar fecha actual
            try {
                XMLGregorianCalendar fechaXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(new GregorianCalendar());
                consultaInput.setFechaConsulta(fechaXML);
            } catch (Exception ex) {
                throw new RuntimeException("Error al procesar fecha", ex);
            }
        }
        consultaInput.setMotivo(consultaDTO.getMotivo());
        consultaInput.setDiagnostico(consultaDTO.getDiagnostico());
        consultaInput.setTratamiento(consultaDTO.getTratamiento());
        consultaInput.setIdMascota(consultaDTO.getMascotaId().intValue());
        
        request.setConsulta(consultaInput);
        UpdateConsultaResponse response = (UpdateConsultaResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getConsulta());
    }

    public boolean deleteConsulta(Long id) {
        DeleteConsultaRequest request = objectFactory.createDeleteConsultaRequest();
        request.setId(id.intValue());
        DeleteConsultaResponse response = (DeleteConsultaResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return response.isSuccess();
    }

    private ConsultaDTO convertToDTO(Consulta consulta) {
        String fecha = consulta.getFechaConsulta() != null ? consulta.getFechaConsulta().toString() : null;
        return new ConsultaDTO(
                (long) consulta.getIdConsulta(),
                fecha,
                consulta.getMotivo(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                (long) consulta.getIdMascota()
        );
    }
}