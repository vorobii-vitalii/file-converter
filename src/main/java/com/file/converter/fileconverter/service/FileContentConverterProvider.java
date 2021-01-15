package com.file.converter.fileconverter.service;

public interface FileContentConverterProvider {
    FileContentConverter getByContentType(String contentType);
}
