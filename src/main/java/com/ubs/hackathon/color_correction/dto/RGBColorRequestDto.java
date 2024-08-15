package com.ubs.hackathon.color_correction.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RGBColorRequestDto {

    @NotNull
    private int red;
    @NotNull
    private int green;
    @NotNull
    private int blue;
    @NotNull
    private String colorDeficiency;
    @NotNull
    private double scalingFactor;
}
