package com.moroz.exception;

public class DoctorsExistForClinicException extends RuntimeException {
    public DoctorsExistForClinicException(Integer id) {
        super("There are available doctors for 'clinic' with id=" + id);
    }
}
