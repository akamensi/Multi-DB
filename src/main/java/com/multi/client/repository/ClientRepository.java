package com.multi.client.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.multi.client.entity.Client;

@Repository
public interface ClientRepository  extends JpaRepository<Client, Long> {
	
	Client findByEmail(String email);
	
	void deleteById(Long clientId);

}
