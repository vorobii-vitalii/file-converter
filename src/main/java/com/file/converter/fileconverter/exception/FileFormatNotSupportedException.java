package com.file.converter.fileconverter.exception;

public class FileFormatNotSupportedException extends RuntimeException {

    public FileFormatNotSupportedException() {}

    public FileFormatNotSupportedException(String message) {
        super(message);
    }

    public FileFormatNotSupportedException(String message, Throwable cause) {
        super(message, cause);
    }

}
