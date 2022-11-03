package com.moroz.exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(Integer id){
        super("Could not find 'patient' with id=" + id);
    }
}
