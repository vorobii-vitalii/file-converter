package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.FileContentConverter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TxtToJSONConverterTest {
    private final FileContentConverter converter = new TxtToJSONConverter();

    @Test
    public void testParseCorrectness() {
        final String inputText = String.join(System.lineSeparator(),
                "name\tage\tgender",
                "john\t25\tmale",
                "rebecca\t19\tfemale"
        );
        final String expectedJsonText =
                "[{\"gender\":\"male\",\"name\":\"john\",\"age\":\"25\"},{\"gender\":\"female\",\"name\":\"rebecca\",\"age\":\"19\"}]";

        final byte[] resultingBytes = converter.convert(inputText.getBytes());

        assertEquals(expectedJsonText, new String(resultingBytes));
    }

    @Test
    public void testParseThrowExceptionWhenInputTextFormatIsCorrupted() {
        final String inputText = String.join(System.lineSeparator(),
                "name\tage\tgender",
                "john\t25\tmale\tmanager",
                "rebecca\t19\tfemale"
        );

        assertThrows(FileProcessingException.class, () ->
                converter.convert(inputText.getBytes())
        );
    }

}