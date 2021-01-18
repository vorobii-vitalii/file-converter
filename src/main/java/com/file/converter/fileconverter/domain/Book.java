package com.file.converter.fileconverter.domain;

import lombok.Data;

@Data
public class Book {
    private String title;
    private String author;
    private String genre;
    private int numPages;
}
