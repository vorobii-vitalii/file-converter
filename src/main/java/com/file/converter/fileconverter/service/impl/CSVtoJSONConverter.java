package com.file.converter.fileconverter.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.FileContentConverter;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class CSVtoJSONConverter implements FileContentConverter {

    @Override
    public byte[] convert(byte[] inputBytes) {
        CsvSchema csv = CsvSchema.emptySchema().withHeader();
        CsvMapper csvMapper = new CsvMapper();

        try {
            MappingIterator<Map<?, ?>> mappingIterator = csvMapper
                    .reader()
                    .forType(Map.class)
                    .with(csv)
                    .readValues(inputBytes);
            List<Map<?, ?>> csvRows = mappingIterator.readAll();

            return new ObjectMapper().writeValueAsBytes(csvRows);
        }
        catch (IOException e) {
            throw new FileProcessingException();
        }
    }

}
