package com.file.converter.fileconverter.service;

public interface ParserProvider<T> {
    FileParser<File, T> getParser(Class<? extends File> fileClass);
}
