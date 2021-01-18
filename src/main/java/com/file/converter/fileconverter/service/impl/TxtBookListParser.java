package com.file.converter.fileconverter.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.file.converter.fileconverter.domain.Book;
import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.TxtParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TxtBookListParser implements TxtParser<List<Book>> {
    @Override
    public List<Book> parse(TxtFile file) {
        final byte[] fileContent = file.getContent();

        CsvSchema csv = CsvSchema.builder().setColumnSeparator('\t').build().withHeader();
        CsvMapper csvMapper = new CsvMapper();

        try {
            MappingIterator<Book> mappingIterator = csvMapper
                    .reader()
                    .forType(Book.class)
                    .with(csv)
                    .readValues(fileContent);

            return mappingIterator.readAll();
        }
        catch (IOException e) {
            throw new FileProcessingException();
        }
    }
}
