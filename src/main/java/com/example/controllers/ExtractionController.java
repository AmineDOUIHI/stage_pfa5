package com.example.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ExtractionController {
	
	private final ExtractionService extractionService;

    public ExtractionController(ExtractionService extractionService) {
        this.extractionService = extractionService;
    }

    @PostMapping("/extract")
    public ResponseEntity<Map<String, Object>> extractTextAndNER(@RequestParam("file") MultipartFile file) {
        try {
            String extractedText = extractionService.extractText(file);
            List<Map<String, String>> extractedEntities = extractionService.extractEntities(extractedText);
            
            Map<String, Object> result = Map.of(
                "text", extractedText,
                "entities", extractedEntities
            );
            
            return ResponseEntity.ok(result);
        } catch (IOException e) {
        	return null;
        }
		
    }

}
