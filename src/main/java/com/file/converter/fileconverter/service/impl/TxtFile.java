package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.service.AFile;

import java.io.IOException;
import java.io.InputStream;

public class TxtFile extends AFile {

    public TxtFile(byte[] bytes) {
        super(bytes);
    }

    public TxtFile(InputStream inputStream) throws IOException {
        super(inputStream);
    }

}
