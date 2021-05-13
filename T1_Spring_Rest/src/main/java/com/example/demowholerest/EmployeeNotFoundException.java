package com.example.demowholerest;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(Long id) {
        super("Could not find employee " + id);
    }
}
