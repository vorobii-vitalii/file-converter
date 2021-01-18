package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.domain.Book;
import com.file.converter.fileconverter.exception.FileFormatNotSupportedException;
import com.file.converter.fileconverter.service.File;
import com.file.converter.fileconverter.service.FileParser;
import com.file.converter.fileconverter.service.ParserProvider;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookListParserProvider implements ParserProvider<List<Book>> {
    private final Map<Class<? extends File>, FileParser<? extends File, List<Book>>> parserMap = new HashMap<>();

    @PostConstruct
    public void populateMap() {
        parserMap.put(CsvFile.class, new CsvBookListParser());
        parserMap.put(TxtFile.class, new TxtBookListParser());
        parserMap.put(XmlFile.class, new XmlBookListParser());
    }

    @Override
    public FileParser<File, List<Book>> getParser(Class<? extends File> fileClass) {
        final FileParser<File, List<Book>> appropriateParser =
                (FileParser<File, List<Book>>) parserMap.get(fileClass);

        if (appropriateParser == null) {
            throw new FileFormatNotSupportedException(fileClass.getSimpleName());
        }
        return appropriateParser;
    }

}
