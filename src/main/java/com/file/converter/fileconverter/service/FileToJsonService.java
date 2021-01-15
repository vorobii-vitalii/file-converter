package com.file.converter.fileconverter.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

public interface FileToJsonService {
    InputStream processFile(MultipartFile multipartFile);
}
