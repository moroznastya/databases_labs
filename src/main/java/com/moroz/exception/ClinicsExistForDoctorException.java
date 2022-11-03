package com.moroz.exception;

public class ClinicsExistForDoctorException extends RuntimeException{
    public ClinicsExistForDoctorException(Integer id){
        super("There are available clinics for 'doctor' with id=" + id);
    }
}
