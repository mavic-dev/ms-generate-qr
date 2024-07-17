package com.mavicdevtech.ms_generate_qr.dto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class generateQRCodeDTO {
    @NotBlank(message = "El campo 'text' no puede estar vacío")
    private String text;

    @Min(value = 100, message = "El ancho debe ser como mínimo 100")
    @Max(value = 1000, message = "El ancho debe ser como máximo 1000")
    private int width = 350; // Valor por defecto para width

    @Min(value = 100, message = "El ancho debe ser como mínimo 100")
    @Max(value = 1000, message = "El ancho debe ser como máximo 1000")
    private int height = 350; // Valor por defecto para height

    @Pattern(regexp = "(#)?([0-9a-fA-F]{6,8})", message = "El campo 'colorQR' debe ser un código hexadecimal de 6 caracteres")
    private String colorQR = "000000"; // Valor por defecto para colorQR

    @Pattern(regexp = "(#)?([0-9a-fA-F]{6,8})", message = "El campo 'backgroundColor' ser un código hexadecimal de 6 caracteres")
    private String backgroundColor = "ffffff"; // Valor por defecto para backgroundColor
}
