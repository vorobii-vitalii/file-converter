package com.file.converter.fileconverter.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.file.converter.fileconverter.domain.Book;
import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.CsvParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class CsvBookListParser implements CsvParser<List<Book>> {

    @Override
    public List<Book> parse(CsvFile file) {
        final byte[] fileContent = file.getContent();

        final CsvMapper csvMapper = new CsvMapper();
        final CsvSchema csvSchema = CsvSchema.emptySchema().withHeader();

        try {
            MappingIterator<Book> mappingIterator = csvMapper
                    .reader()
                    .forType(Book.class)
                    .with(csvSchema)
                    .readValues(fileContent);

            return mappingIterator.readAll();
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FileProcessingException();
        }
    }

}
