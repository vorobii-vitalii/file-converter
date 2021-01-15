package com.file.converter.fileconverter.controller;

import com.file.converter.fileconverter.service.FileToJsonService;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

@RestController
@RequestMapping
@AllArgsConstructor
public class FileUploadController {
    private final FileToJsonService fileToJsonService;

    @PostMapping
    public void handleFileUpload(@RequestParam("file") MultipartFile inputFile, HttpServletResponse response) throws IOException {
        final InputStream resultStream = fileToJsonService.processFile(inputFile);

        response.addHeader("Content-disposition", "attachment;filename=result.json");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        IOUtils.copy(resultStream, response.getOutputStream());
        response.flushBuffer();
    }

}
