package com.mavicdevtech.ms_generate_qr.controller;

import com.google.zxing.WriterException;
import com.mavicdevtech.ms_generate_qr.utils.MsGenerateQrExceptionHandler;
import com.mavicdevtech.ms_generate_qr.service.MsGenerateQrService;
import com.mavicdevtech.ms_generate_qr.dto.generateQRCodeDTO;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Tag(name = "qr-codes", description = "Management QR codes")
@RestController("qr-codes")
public class MsGenerateQrController {
    @Autowired
    private MsGenerateQrService msGenerateQrService;

    @GetMapping("/generate")
    public ResponseEntity generateQRCode(HttpServletResponse response, @Valid @ModelAttribute generateQRCodeDTO requestGenerateQR, BindingResult bindingResult
    ) throws IOException, WriterException, NumberFormatException {
        if(bindingResult.hasErrors()){
            return MsGenerateQrExceptionHandler.handlerGenerateQrCodeDTO(bindingResult);
        }
        BufferedImage image = msGenerateQrService.generateQRCode(
                requestGenerateQR.getText(),
                requestGenerateQR.getWidth(),
                requestGenerateQR.getHeight(),
                requestGenerateQR.getColorQR(),
                requestGenerateQR.getBackgroundColor()
        );
        ServletOutputStream outputStream = response.getOutputStream();
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        ImageIO.write(image,"png",outputStream);
        outputStream.flush();
        outputStream.close();
        return null;
    }
}
