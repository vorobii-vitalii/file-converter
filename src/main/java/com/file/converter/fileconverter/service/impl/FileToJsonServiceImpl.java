package com.file.converter.fileconverter.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.file.converter.fileconverter.domain.Book;
import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.File;
import com.file.converter.fileconverter.service.FileAdapter;
import com.file.converter.fileconverter.service.FileParser;
import com.file.converter.fileconverter.service.ParserProvider;
import com.file.converter.fileconverter.service.FileToJsonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;

@Service
@AllArgsConstructor
public class FileToJsonServiceImpl implements FileToJsonService {
    private final ParserProvider<List<Book>> bookListParserProvider;
    private final FileAdapter<MultipartFile> fileAdapter;

    @Override
    public InputStream processFile(MultipartFile multipartFile) {
        final File file = fileAdapter.adapt(multipartFile);
        final FileParser<File, List<Book>> parser = bookListParserProvider.getParser(file.getClass());

        final List<Book> parsedBooks = parser.parse(file);

        try {
            final byte[] jsonBytes = new ObjectMapper().writeValueAsBytes(parsedBooks);

            return new ByteArrayInputStream(jsonBytes);
        }
        catch (JsonProcessingException e) {
            throw new FileProcessingException();
        }
    }
}
