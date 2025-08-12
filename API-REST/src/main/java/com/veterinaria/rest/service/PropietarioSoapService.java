package com.veterinaria.rest.service;

import com.veterinaria.rest.dto.PropietarioDTO;
import com.veterinaria.rest.soap.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropietarioSoapService {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    private final ObjectFactory objectFactory = new ObjectFactory();

    public List<PropietarioDTO> getAllPropietarios() {
        GetPropietariosRequest request = objectFactory.createGetPropietariosRequest();
        GetPropietariosResponse response = (GetPropietariosResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return response.getPropietario().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PropietarioDTO getPropietarioById(Long id) {
        GetPropietarioRequest request = objectFactory.createGetPropietarioRequest();
        request.setId(id.intValue());
        GetPropietarioResponse response = (GetPropietarioResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getPropietario());
    }

    public PropietarioDTO createPropietario(PropietarioDTO propietarioDTO) {
        CreatePropietarioRequest request = objectFactory.createCreatePropietarioRequest();
        PropietarioInput propietarioInput = objectFactory.createPropietarioInput();
        
        propietarioInput.setNombre(propietarioDTO.getNombre());
        propietarioInput.setApellido(propietarioDTO.getApellido());
        propietarioInput.setTelefono(propietarioDTO.getTelefono());
        propietarioInput.setEmail(propietarioDTO.getEmail());
        propietarioInput.setDireccion(propietarioDTO.getDireccion());
        
        request.setPropietario(propietarioInput);
        CreatePropietarioResponse response = (CreatePropietarioResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getPropietario());
    }

    public PropietarioDTO updatePropietario(Long id, PropietarioDTO propietarioDTO) {
        UpdatePropietarioRequest request = objectFactory.createUpdatePropietarioRequest();
        request.setId(id.intValue());
        
        PropietarioInput propietarioInput = objectFactory.createPropietarioInput();
        propietarioInput.setNombre(propietarioDTO.getNombre());
        propietarioInput.setApellido(propietarioDTO.getApellido());
        propietarioInput.setTelefono(propietarioDTO.getTelefono());
        propietarioInput.setEmail(propietarioDTO.getEmail());
        propietarioInput.setDireccion(propietarioDTO.getDireccion());
        
        request.setPropietario(propietarioInput);
        UpdatePropietarioResponse response = (UpdatePropietarioResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return convertToDTO(response.getPropietario());
    }

    public boolean deletePropietario(Long id) {
        DeletePropietarioRequest request = objectFactory.createDeletePropietarioRequest();
        request.setId(id.intValue());
        DeletePropietarioResponse response = (DeletePropietarioResponse) webServiceTemplate.marshalSendAndReceive(request);
        
        return response.isSuccess();
    }

    private PropietarioDTO convertToDTO(Propietario propietario) {
        return new PropietarioDTO(
                (long) propietario.getIdPropietario(),
                propietario.getNombre(),
                propietario.getApellido(),
                propietario.getTelefono(),
                propietario.getEmail(),
                propietario.getDireccion()
        );
    }
}