package com.file.converter.fileconverter.service.impl;

import com.file.converter.fileconverter.exception.FileProcessingException;
import com.file.converter.fileconverter.service.FileContentConverter;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import org.springframework.stereotype.Service;


@Service
public class XMLtoJSONConverter implements FileContentConverter {
    private static final int INDENT_LEVEL = 4;

    @Override
    public byte[] convert(byte[] inputBytes) {
        try {
            final JSONObject xmlJsonObject = XML.toJSONObject(new String(inputBytes));
            final String jsonText = xmlJsonObject.toString(INDENT_LEVEL);

            return jsonText.getBytes();
        }
        catch (JSONException e) {
            throw new FileProcessingException();
        }
    }

}
