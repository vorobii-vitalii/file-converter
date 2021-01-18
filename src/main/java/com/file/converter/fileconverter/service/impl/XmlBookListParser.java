package com.file.converter.fileconverter.service.impl;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.file.converter.fileconverter.domain.Book;
import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.XmlParser;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class XmlBookListParser implements XmlParser<List<Book>> {

    @Override
    public List<Book> parse(XmlFile file) {
        final XmlMapper xmlMapper = new XmlMapper();
        final byte[] fileContent = file.getContent();

        final ObjectReader booksReader = xmlMapper.readerForListOf(Book.class);

        try {
            return booksReader.readValue(fileContent);
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new FileProcessingException();
        }
    }

}
