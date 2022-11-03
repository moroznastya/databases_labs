package com.moroz.exception;

public class ClinicNotFoundException extends RuntimeException {
    public ClinicNotFoundException(Integer id) {
        super("Could not find 'clinic' with id=" + id);
    }
}
