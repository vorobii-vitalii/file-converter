package com.file.converter.fileconverter.service;

import com.file.converter.fileconverter.exception.FileFormatNotSupportedException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class FileContentConverterProviderTest {

    @Autowired
    private FileContentConverterProvider contentConverterProvider;

    @Test
    public void testWhenContentIsNotSupportedThrowsException() {
        final String UNSUPPORTED_FORMAT = "unsupported format";

        assertThrows(FileFormatNotSupportedException.class, () ->
                contentConverterProvider.getByContentType(UNSUPPORTED_FORMAT)
        );
    }

}