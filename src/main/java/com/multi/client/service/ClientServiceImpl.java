package com.multi.client.service;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.multi.client.entity.Client;
import com.multi.client.entity.dto.ClientDTO;
import com.multi.client.repository.ClientRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ClientServiceImpl implements IClientService {

	@Autowired
	private final ClientRepository clientRepository;

	public ClientServiceImpl(ClientRepository clientRepository) {
		this.clientRepository = clientRepository;
	}

	@Override
	public List<ClientDTO> getAllClients() {
		List<Client> clients = clientRepository.findAll();
		return clients.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ClientDTO getClientById(Long clientId) {
		Optional<Client> client = clientRepository.findById(clientId);
		return client.map(this::convertToDTO).orElseThrow(() -> new IllegalArgumentException("Client not found"));
	}

	@Override
	public ClientDTO createClient(ClientDTO clientDTO) {
		Client client = convertToEntity(clientDTO);
		Client savedClient = clientRepository.save(client);
		return convertToDTO(savedClient);
	}

	@Override
	public ClientDTO updateClient(Long clientId, ClientDTO clientDTO) {
	    // Fetch existing client
	    Client existingClient = clientRepository.findById(clientId)
	            .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + clientId));
	    
	    // Update only the fields that are provided in the DTO
	    if (clientDTO.getName() != null) {
	        existingClient.setName(clientDTO.getName());
	    }
	    if (clientDTO.getAge() != 0) { // Assuming age is not allowed to be zero; adjust as necessary
	        existingClient.setAge(clientDTO.getAge());
	    }
	    if (clientDTO.getEmail() != null) {
	        existingClient.setEmail(clientDTO.getEmail());
	    }

	    // Save the updated client
	    Client updatedClient = clientRepository.save(existingClient);
	    
	    // Convert to DTO and return
	    return convertToDTO(updatedClient);
	}

	@Override
	public void deleteClientById(Long clientId) {
		if (clientRepository.existsById(clientId)) {
			clientRepository.deleteById(clientId);
		} else {
			throw new IllegalArgumentException("Client not found");
		}
	}

// Conversion methods(It's a common practice to maintain separation between 
//your domain entities and the data transfer objects (DTOs))

	private ClientDTO convertToDTO(Client client) {
		ClientDTO dto = new ClientDTO();
		dto.setClientId(client.getClientId());
		dto.setName(client.getName());
		dto.setAge(client.getAge());
		dto.setEmail(client.getEmail());
		return dto;
	}

	private Client convertToEntity(ClientDTO clientDTO) {
		Client client = new Client();
		client.setClientId(clientDTO.getClientId());
		client.setName(clientDTO.getName());
		client.setAge(clientDTO.getAge());
		client.setEmail(clientDTO.getEmail());
		return client;
	}

}
