package com.mavicdevtech.ms_generate_qr.utils;

import com.google.zxing.WriterException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@ControllerAdvice
public class MsGenerateQrExceptionHandler {

    @ExceptionHandler(IOException.class)
    public ResponseEntity<MsGenerateQrErrorResponse> handleIOException(IOException ex) {

        long timestamp = System.currentTimeMillis() - (5 * 3600000);

        MsGenerateQrErrorResponse msGenerateQrErrorResponse = new MsGenerateQrErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "IO_EXCEPTION",
                "Error de E/S: " + ex.getMessage(),
                timestamp
        );
        return new ResponseEntity<>(msGenerateQrErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(WriterException.class)
    public ResponseEntity<MsGenerateQrErrorResponse> handleWriterException(WriterException ex) {
        long timestamp = System.currentTimeMillis() - (5 * 3600000);

        MsGenerateQrErrorResponse msGenerateQrErrorResponse = new MsGenerateQrErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "IO_EXCEPTION",
                "Error de escritura: " + ex.getMessage(),
                timestamp
        );
        return new ResponseEntity<>(msGenerateQrErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<MsGenerateQrErrorResponse> handlerNumberFormatException(NumberFormatException ex) {
        long timestamp = System.currentTimeMillis() - (5 * 3600000);
        MsGenerateQrErrorResponse msGenerateQrErrorResponse = new MsGenerateQrErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "IO_EXCEPTION",
                "Error de formato hexadecimal para el color : " + ex.getMessage(),
                timestamp
        );
        return new ResponseEntity<>(msGenerateQrErrorResponse, HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<MsGenerateQrErrorResponse> handlerGenerateQrCodeDTO(BindingResult bindingResult) {
        long timestamp = System.currentTimeMillis() - (5 * 3600000);
        StringBuilder message = new StringBuilder();
        bindingResult.getAllErrors().forEach(error -> message.append(error.getDefaultMessage()).append("; "));

        MsGenerateQrErrorResponse msGenerateQrErrorResponse = new MsGenerateQrErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                message.toString(),
                timestamp
        );
        return new ResponseEntity<>(msGenerateQrErrorResponse, HttpStatus.BAD_REQUEST);
    }
}