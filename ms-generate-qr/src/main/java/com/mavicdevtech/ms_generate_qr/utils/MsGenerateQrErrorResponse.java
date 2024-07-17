package com.mavicdevtech.ms_generate_qr.utils;

public record MsGenerateQrErrorResponse(int status, String error, String message, long timestamp) {
}
