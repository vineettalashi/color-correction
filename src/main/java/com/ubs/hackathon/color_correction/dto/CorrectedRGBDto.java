package com.ubs.hackathon.color_correction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrectedRGBDto {
    private String red;
    private String green;
    private String blue;
}
