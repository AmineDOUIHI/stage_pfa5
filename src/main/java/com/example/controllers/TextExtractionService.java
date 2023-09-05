package com.example.controllers;

import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;

@Service
public class TextExtractionService {
	
	public String extractTextFromPDF(InputStream pdfInputStream) throws IOException {
        PDDocument document = PDDocument.load(pdfInputStream);
        PDFTextStripper stripper = new PDFTextStripper();
        String text = stripper.getText(document);
        document.close();
        return text;
    }

}
