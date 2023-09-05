package com.example.controllers;

import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.imgcodecs.Imgcodecs;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api")
public class ImageProcessingController {
	 private final ImagePreprocessingService preprocessingService;

	    public ImageProcessingController(ImagePreprocessingService preprocessingService) {
	        this.preprocessingService = preprocessingService;
	    }

	    @PostMapping("/process-image")
	    public ResponseEntity<byte[]> processImage(@RequestParam("image") MultipartFile imageFile) {
	        try {
	            // Convert MultipartFile to Mat (OpenCV image format)
	            byte[] imageBytes = imageFile.getBytes();
	            Mat inputImage = Imgcodecs.imdecode(new MatOfByte(imageBytes), Imgcodecs.IMREAD_COLOR);

	            // Apply preprocessing using preprocessingService.preprocessImage()
	            Mat processedImage = preprocessingService.preprocessImage(inputImage);

	            // Convert the processed Mat back to a byte array
	            MatOfByte processedImageBytes = new MatOfByte();
	            Imgcodecs.imencode(".jpg", processedImage, processedImageBytes);
	            byte[] processedBytes = processedImageBytes.toArray();

	            // Return the processed image bytes
	            HttpHeaders headers = new HttpHeaders();
	            headers.setContentType(MediaType.IMAGE_JPEG);
	            return new ResponseEntity<>(processedBytes, headers, HttpStatus.OK);
	        } catch (Exception e) {
	            e.printStackTrace();
	            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }

}
