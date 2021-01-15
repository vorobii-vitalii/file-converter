package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.FileContentConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CSVtoJSONConverterTest {
    final FileContentConverter converter = new CSVtoJSONConverter();

    @Test
    public void testParseCorrectness1() {
        final String inputText = String.join(System.lineSeparator(),
                "name,age,gender",
                "john,25,male",
                "rebecca,19,female"
        );
        final String expectedJsonText =
                "[{\"name\":\"john\",\"age\":\"25\",\"gender\":\"male\"},{\"name\":\"rebecca\",\"age\":\"19\",\"gender\":\"female\"}]";

        final byte[] resultingBytes = converter.convert(inputText.getBytes());

        assertEquals(expectedJsonText, new String(resultingBytes));
    }

    @Test
    public void testParseThrowExceptionWhenInputTextFormatIsCorrupted() {
        final String corruptedInputText = String.join(System.lineSeparator(),
                "name,age,gender",
                "john,25,male,manager", // Excessive field
                "rebecca,19,female"
        );

        assertThrows(FileProcessingException.class, () ->
                converter.convert(corruptedInputText.getBytes()));
    }

}