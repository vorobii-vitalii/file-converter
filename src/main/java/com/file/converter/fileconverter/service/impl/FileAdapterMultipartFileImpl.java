package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.exception.FileFormatNotRecognizedException;
import com.file.converter.fileconverter.exception.FileFormatNotSupportedException;
import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.File;
import com.file.converter.fileconverter.service.FileAdapter;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileAdapterMultipartFileImpl implements FileAdapter<MultipartFile> {
    private static final String XML_TYPE = MediaType.APPLICATION_XML.toString();
    private static final String CSV_TYPE = "text/csv";
    private static final String TXT_TYPE = MediaType.TEXT_PLAIN_VALUE;

    @Override
    public File adapt(MultipartFile multipartFile) {
        try {
            final byte[] fileContent = multipartFile.getBytes();
            final String contentType = multipartFile.getContentType();

            if (contentType == null) {
                throw new FileFormatNotSupportedException();
            }

            if (contentType.equals(XML_TYPE)) {
                return new XmlFile(fileContent);
            }
            else if (contentType.equals(CSV_TYPE)) {
                return new CsvFile(fileContent);
            }
            else if (contentType.equals(TXT_TYPE)) {
                return new TxtFile(fileContent);
            }

            throw new FileFormatNotRecognizedException();
        }
        catch (IOException e) {
            throw new FileProcessingException();
        }
    }
}
