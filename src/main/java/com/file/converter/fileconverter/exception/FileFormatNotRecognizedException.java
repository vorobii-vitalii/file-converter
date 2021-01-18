package com.file.converter.fileconverter.exception;

public class FileFormatNotRecognizedException extends RuntimeException {

    public FileFormatNotRecognizedException() { }

    public FileFormatNotRecognizedException(String message) {
        super(message);
    }

    public FileFormatNotRecognizedException(String message, Throwable cause) {
        super(message, cause);
    }

}
