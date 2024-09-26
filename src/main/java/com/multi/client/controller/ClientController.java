package com.multi.client.controller;

import java.util.*;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.multi.client.entity.dto.ClientDTO;
import com.multi.client.service.ClientServiceImpl;

@RestController
@RequestMapping("/api/clients")
public class ClientController {
	
	 private final ClientServiceImpl clientService;

	    public ClientController(ClientServiceImpl clientService) {
	        this.clientService = clientService;
	    }

	    // Get all clients
	    @GetMapping
	    public ResponseEntity<List<ClientDTO>> getAllClients() {
	        List<ClientDTO> clients = clientService.getAllClients();
	        return new ResponseEntity<>(clients, HttpStatus.OK);
	    }

	    // Get a client by ID
	    @GetMapping("/{id}")
	    public ResponseEntity<ClientDTO> getClientById(@PathVariable("id") Long clientId) {
	        ClientDTO client = clientService.getClientById(clientId);
	        return new ResponseEntity<>(client, HttpStatus.OK);
	    }

	    // Create a new client
	    @PostMapping
	    public ResponseEntity<ClientDTO> createClient(@RequestBody ClientDTO clientDTO) {
	        ClientDTO newClient = clientService.createClient(clientDTO);
	        return new ResponseEntity<>(newClient, HttpStatus.CREATED);
	    }

	    // Update an existing client
	    @PutMapping("/{id}")
	    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO) {
	        ClientDTO updatedClient = clientService.updateClient(id, clientDTO);
	        return ResponseEntity.ok(updatedClient);
	    }

	    // Delete a client by ID
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteClientById(@PathVariable("id") Long clientId) {
	        clientService.deleteClientById(clientId);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }



}
