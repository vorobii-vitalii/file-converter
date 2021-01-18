package com.file.converter.fileconverter.service;

public interface FileAdapter<T> {
    File adapt(T t);
}
