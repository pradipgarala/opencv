package com.example.opencv.capture;

import org.bytedeco.javacv.FrameGrabber;
import org.bytedeco.javacv.Java2DFrameUtils;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;
import org.bytedeco.opencv.opencv_core.Mat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RestController
class CaptureController {

    @GetMapping(value = "/capture", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] live() throws IOException {
        Mat grabbedImage;

        try (FrameGrabber grabber = new OpenCVFrameGrabber(0)) {
            grabber.start();
            try (OpenCVFrameConverter.ToMat converter = new OpenCVFrameConverter.ToMat()) {
                grabbedImage = converter.convert(grabber.grab());
            }
            grabber.stop();
        }

        BufferedImage image = Java2DFrameUtils.toBufferedImage(grabbedImage);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        javax.imageio.ImageIO.write(image, "jpg", os);
        return os.toByteArray();
    }

}
