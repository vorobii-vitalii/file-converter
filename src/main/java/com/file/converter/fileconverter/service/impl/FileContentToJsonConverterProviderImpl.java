package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.exception.FileFormatNotSupportedException;
import com.file.converter.fileconverter.service.FileContentConverter;
import com.file.converter.fileconverter.service.FileContentConverterProvider;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileContentToJsonConverterProviderImpl implements FileContentConverterProvider {
    private static final String TEXT_CSV = "text/csv";

    private final Map<String, FileContentConverter> contentTypeConverterMap = new HashMap<>();

    @PostConstruct
    public void populateMap() {
        final FileContentConverter XML_JSON_CONVERTER = new XMLtoJSONConverter();
        final FileContentConverter CSV_JSON_CONVERTER = new CSVtoJSONConverter();
        final FileContentConverter TXT_JSON_CONVERTER = new TxtToJSONConverter();

        contentTypeConverterMap.put(MediaType.APPLICATION_XML.toString(), XML_JSON_CONVERTER);
        contentTypeConverterMap.put(MediaType.TEXT_PLAIN_VALUE, TXT_JSON_CONVERTER);
        contentTypeConverterMap.put(TEXT_CSV, CSV_JSON_CONVERTER);
    }

    @Override
    public FileContentConverter getByContentType(String contentType) {
        final FileContentConverter correspondingConverter = contentTypeConverterMap.get(contentType);

        if (correspondingConverter == null) {
            throw new FileFormatNotSupportedException(contentType);
        }
        return correspondingConverter;
    }

}
