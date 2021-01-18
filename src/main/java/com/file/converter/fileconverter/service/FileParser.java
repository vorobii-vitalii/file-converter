package com.file.converter.fileconverter.service;

public interface FileParser<T extends File, U> {
    U parse(T file);
}
