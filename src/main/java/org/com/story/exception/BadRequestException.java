package org.com.story.exception;

public class BadRequestException extends AppException {

    public BadRequestException(String message) {
        super("BAD_REQUEST", message);
    }
}
