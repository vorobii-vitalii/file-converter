package com.file.converter.fileconverter.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.FileContentConverter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TxtToJSONConverter implements FileContentConverter {
    private static final String DELIMITER = "\t";

    @Override
    public byte[] convert(byte[] inputBytes) {
        final List<Map<String, String>> parseResult = new ArrayList<>();

        try (BufferedReader bufferedReader = getReaderFromByteArray(inputBytes)) {
            final String columnDefinition = bufferedReader.readLine();

            if (columnDefinition == null) {
                throw new FileProcessingException();
            }
            final String[] columnNames = columnDefinition.split(DELIMITER);

            bufferedReader.lines().forEach(line -> {
                final String[] columnValues = line.split(DELIMITER);

                if (columnValues.length != columnNames.length) {
                    throw new FileProcessingException();
                }

                parseResult.add(matchStringArrays(columnNames, columnValues));
            });

            return new ObjectMapper().writeValueAsBytes(parseResult);
        }
        catch (IOException e) {
            throw new FileProcessingException();
        }
    }

    /**
     * Method assumes that the arrays have an equal size
     */
    private static Map<String, String> matchStringArrays(String[] keys, String[] values) {
        final Map<String, String> matchResult = new HashMap<>();

        for (int i = 0; i < keys.length; i++) {
            matchResult.put(keys[i], values[i]);
        }

        return matchResult;
    }

    private static BufferedReader getReaderFromByteArray(byte[] bytes) {
        return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(bytes)));
    }

}
