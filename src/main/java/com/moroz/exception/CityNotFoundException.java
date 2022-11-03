package com.moroz.exception;

public class CityNotFoundException extends RuntimeException {
    public CityNotFoundException(String name) {
        super("Could not find 'city' with name=" + name);
    }
}
