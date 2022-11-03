package com.moroz.exception;

public class ClinicsExistForCityException extends RuntimeException {
    public ClinicsExistForCityException(String name) {
        super("There are available clinics for 'city' with name=" + name);
    }
}
