package com.multi.client.service;

import java.util.List;

import com.multi.client.entity.dto.ClientDTO;

public interface IClientService {
	
	 
    List<ClientDTO> getAllClients();

    ClientDTO getClientById(Long clientId);

    ClientDTO createClient(ClientDTO clientDTO);

    ClientDTO updateClient(Long clientId, ClientDTO clientDTO);

    void deleteClientById(Long clientId);


}
