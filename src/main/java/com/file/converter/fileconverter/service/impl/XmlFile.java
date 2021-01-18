package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.service.AFile;

import java.io.IOException;
import java.io.InputStream;

public class XmlFile extends AFile {

    public XmlFile(byte[] bytes) {
        super(bytes);
    }

    public XmlFile(InputStream inputStream) throws IOException {
        super(inputStream);
    }

}
