package com.example.controllers;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;

public class ImagePreprocessingService {
	static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public Mat preprocessImage(Mat inputImage) {
        Mat grayImage = new Mat();
        Mat blurredImage = new Mat();
        Mat thresholdImage = new Mat();

        Imgproc.cvtColor(inputImage, grayImage, Imgproc.COLOR_BGR2GRAY);
        Imgproc.GaussianBlur(grayImage, blurredImage, new Size(5, 5), 0);
        Imgproc.threshold(blurredImage, thresholdImage, 0, 255, Imgproc.THRESH_BINARY + Imgproc.THRESH_OTSU);

        return thresholdImage;
    }

}
