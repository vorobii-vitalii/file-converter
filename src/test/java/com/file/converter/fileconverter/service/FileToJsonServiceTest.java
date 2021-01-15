package com.file.converter.fileconverter.service;

import com.file.converter.fileconverter.exception.FileFormatNotSupportedException;
import com.file.converter.fileconverter.exception.FileProcessingException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class FileToJsonServiceTest {

    @MockBean
    private FileContentConverterProvider contentConverterProvider;

    @Autowired
    private FileToJsonService fileToJsonService;

    @Test
    public void testIfUnsupportedFormatThrowsException() {
        final String UNSUPPORTED_FORMAT = "abc";
        final MultipartFile mockMultipartFile =
                new MockMultipartFile("test", "test", UNSUPPORTED_FORMAT, new byte[0]);

        when(contentConverterProvider.getByContentType(UNSUPPORTED_FORMAT))
                .thenThrow(FileFormatNotSupportedException.class);

        assertThrows(FileFormatNotSupportedException.class, () ->
                fileToJsonService.processFile(mockMultipartFile));
    }

    @Test
    public void testIfFilesInputStreamIsCorruptedThrowsException() throws IOException {
        final String FORMAT = "abc";
        final MultipartFile mockMultipartFile =
                new MockMultipartFile("test", "test", FORMAT, new byte[0]);

        final MultipartFile spyMultipartFile = Mockito.spy(mockMultipartFile);

        doThrow(IOException.class).when(spyMultipartFile).getInputStream();

        when(contentConverterProvider.getByContentType(FORMAT))
                .thenReturn(new FileContentConverterStub());

        assertThrows(FileProcessingException.class, () ->
                fileToJsonService.processFile(spyMultipartFile));
    }

    @Test
    public void testCorrectScenario() {
        final String FORMAT = "abc";
        final MultipartFile mockMultipartFile =
                new MockMultipartFile("test", "test", FORMAT, new byte[0]);

        when(contentConverterProvider.getByContentType(FORMAT))
                .thenReturn(new FileContentConverterStub());

        fileToJsonService.processFile(mockMultipartFile);
    }

}