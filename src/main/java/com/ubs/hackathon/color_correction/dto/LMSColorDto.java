package com.ubs.hackathon.color_correction.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LMSColorDto {

    private String l;
    private String m;
    private String s;
}
