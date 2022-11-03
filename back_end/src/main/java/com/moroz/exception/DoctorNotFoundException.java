package com.moroz.exception;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(Integer id) {
        super("Could not find 'doctor' with id=" + id);
    }
}
