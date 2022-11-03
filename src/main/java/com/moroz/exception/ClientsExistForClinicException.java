package com.moroz.exception;

public class ClientsExistForClinicException extends RuntimeException {
    public ClientsExistForClinicException(Integer id) {
        super("There are available clients for 'clinic' with id=" + id);
    }
}
