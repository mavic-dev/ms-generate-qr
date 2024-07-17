package com.mavicdevtech.ms_generate_qr.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import java.awt.image.BufferedImage;


@Service
public class MsGenerateQrService {
    public BufferedImage generateQRCode(String text, int width, int height, String colorQR, String backgroundColor) throws WriterException,NumberFormatException {

        int colorOn = getHexColor(colorQR);
        int colorOff = getHexColor(backgroundColor);

        QRCodeWriter qrCodeWriter =  new QRCodeWriter();
        BitMatrix bitMatrix =  qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        MatrixToImageConfig imageConfig = new MatrixToImageConfig(colorOn,colorOff);
        return MatrixToImageWriter.toBufferedImage(bitMatrix,imageConfig);
    }

    public int getHexColor(String color){

        // Remove "0x" "#" prefixes
        if(color.startsWith("0x")){
            color = color.substring(2);
        }
        else if(color.startsWith("#")){
            color = color.substring(1);
        }

        // Add transparency 100% with hex value
        if(color.length() == 6){
            color = "FF"+ color;
        }
        return (int) Long.parseLong(color, 16);
    }

}
