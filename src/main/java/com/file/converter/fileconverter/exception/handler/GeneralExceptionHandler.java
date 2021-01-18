package com.file.converter.fileconverter.exception.handler;

import com.file.converter.fileconverter.exception.FileFormatNotRecognizedException;
import com.file.converter.fileconverter.exception.FileFormatNotSupportedException;
import com.file.converter.fileconverter.exception.FileProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler(FileFormatNotSupportedException.class)
    public ResponseEntity<String> handleFileFormatIsNotSupported(FileFormatNotSupportedException ignored) {
        return new ResponseEntity<>("The provided file's format is not supported yet", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FileProcessingException.class)
    public ResponseEntity<String> handleFileProcessingFailed(FileProcessingException ignored) {
        return new ResponseEntity<>("Processing of the file failed", HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(FileFormatNotRecognizedException.class)
    public ResponseEntity<String> handleFileFormatNotRecognized(FileFormatNotRecognizedException ignored) {
        return new ResponseEntity<>("Input's file format is not recognized", HttpStatus.BAD_REQUEST);
    }

}
