package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.FileContentConverter;
import com.file.converter.fileconverter.service.FileContentConverterProvider;
import com.file.converter.fileconverter.service.FileToJsonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
@AllArgsConstructor
public class FileToJsonServiceImpl implements FileToJsonService {
    private final FileContentConverterProvider contentConverterProvider;

    @Override
    public InputStream processFile(MultipartFile multipartFile) {
        final String fileContentType = multipartFile.getContentType();
        final FileContentConverter fileContentConverter = contentConverterProvider.getByContentType(fileContentType);

        try (InputStream fileInputStream = multipartFile.getInputStream()) {
            final byte[] inputBytes  = fileInputStream.readAllBytes();
            final byte[] outputBytes = fileContentConverter.convert(inputBytes);
            return new ByteArrayInputStream(outputBytes);
        }
        catch (IOException e) {
            throw new FileProcessingException();
        }
    }

}
