package com.moroz.service.impl;

import com.moroz.dao.ClientDao;
import com.moroz.domain.City;
import com.moroz.domain.Client;
import com.moroz.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientDao clientDao;

    @Override
    public List<Client> findAll() {
        return clientDao.findAll();
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        return clientDao.findById(clientId);
    }

    @Override
    public int create(Client client) {
        return clientDao.create(client);
    }

    @Override
    public int update(Integer clientId, Client client) {
        return clientDao.update(clientId, client);
    }

    @Override
    public int delete(Integer clientId) {
        return clientDao.delete(clientId);
    }
}
