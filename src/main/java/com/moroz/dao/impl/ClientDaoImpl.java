package com.moroz.dao.impl;

import com.moroz.dao.ClientDao;
import com.moroz.domain.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientDaoImpl implements ClientDao {
    private static final String FIND_ALL = "SELECT * FROM `client`";
    private static final String CREATE = "INSERT `client`(id, `name`, surname, contact_number)" +
            " VALUES (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `client` SET `name`=?, surname = ?, contact_number = ? " +
            "WHERE id=?";
    private static final String DELETE = "DELETE FROM `client` WHERE id=?";
    private static final String FIND_BY_ID = "SELECT * FROM `client` WHERE id=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Client> findAll() {
        return jdbcTemplate.query(FIND_ALL, BeanPropertyRowMapper.newInstance(Client.class));
    }

    @Override
    public Optional<Client> findById(Integer clientId) {
        Optional<Client> client;
        try {
            client = Optional.ofNullable(jdbcTemplate.queryForObject(FIND_BY_ID,
                    BeanPropertyRowMapper.newInstance(Client.class), clientId));
        } catch (EmptyResultDataAccessException e) {
            client = Optional.empty();
        }
        return client;
    }

    @Override
    public int create(Client client) {
        return jdbcTemplate.update(CREATE,
                client.getId(), client.getName(), client.getSurname(), client.getContactNumber());
    }

    @Override
    public int update(Integer clientId, Client client) {
        return jdbcTemplate.update(UPDATE, client.getId(), client.getName(), client.getSurname(),
                client.getContactNumber(), clientId);
    }

    @Override
    public int delete(Integer clientId) {
        return jdbcTemplate.update(DELETE, clientId);
    }
}
