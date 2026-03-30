package com.goros._9_keo_vuththana_springboot_homework003.exception;

public class EmailExistException extends RuntimeException {
    public EmailExistException(String message) {
        super(message);
    }
}
