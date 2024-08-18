package com.vt.hackathon.color_correction.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CorrectedRGBDto {
    @JsonProperty("red")
    private String red;
    @JsonProperty("green")
    private String green;
    @JsonProperty("blue")
    private String blue;
}
