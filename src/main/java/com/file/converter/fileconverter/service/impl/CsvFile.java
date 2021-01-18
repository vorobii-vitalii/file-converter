package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.service.AFile;

import java.io.IOException;
import java.io.InputStream;

public class CsvFile extends AFile {

    public CsvFile(byte[] bytes) {
        super(bytes);
    }

    public CsvFile(InputStream inputStream) throws IOException {
        super(inputStream);
    }

}
