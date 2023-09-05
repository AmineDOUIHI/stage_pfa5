package com.example.controllers;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ExtractionService {
	
	 private final TextExtractionService textExtractionService;

	    public ExtractionService(TextExtractionService textExtractionService) {
	        this.textExtractionService = textExtractionService;
	    }

	    public String extractText(MultipartFile file) throws IOException {
	        InputStream inputStream = file.getInputStream();
	        return textExtractionService.extractTextFromPDF(inputStream);
	    }

	    public List<Map<String, String>> extractEntities(String text) throws IOException {
	        JythonObjectFactory factory = JythonObjectFactory.getInstance();
	        try (PythonInterpreter interpreter = new PythonInterpreter()) {
	            interpreter.execfile("path_to_your_python_script/ner_extractor.py");
	            PyObject pyObject = interpreter.get("NerExtractor");
	            PyObject extractedEntities = pyObject.__call__();
	            
	        }
	        
	        return null;
	    }

}

