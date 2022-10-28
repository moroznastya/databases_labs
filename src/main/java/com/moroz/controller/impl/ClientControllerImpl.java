package com.moroz.controller.impl;

import com.moroz.controller.ClientController;
import com.moroz.domain.Client;
import com.moroz.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientControllerImpl implements ClientController {
    @Autowired
    ClientService clientService;

    @Override
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        return clientService.findById(clientId);
    }

    @Override
    public int create(Client client) {
        return clientService.create(client);
    }

    @Override
    public int update(Integer clientId, Client client) {
        return clientService.update(clientId, client);
    }

    @Override
    public int delete(Integer clientId) {
        return clientService.delete(clientId);
    }
}
