package com.ubs.hackathon.color_correction.controller;

import com.ubs.hackathon.color_correction.dto.CorrectedRGBDto;
import com.ubs.hackathon.color_correction.dto.LMSColorDto;
import com.ubs.hackathon.color_correction.dto.RGBColorRequestDto;
import com.ubs.hackathon.color_correction.service.ColorCorrectionService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
public class ColorCorrectionController {

    private final ColorCorrectionService colorCorrectionService;

    public ColorCorrectionController(ColorCorrectionService colorCorrectionService) {
        this.colorCorrectionService = colorCorrectionService;
    }

    @GetMapping("/correctedRGB")
    public CorrectedRGBDto correctedRGB(@RequestParam int red,
                                        @RequestParam int green,
                                        @RequestParam int blue,
                                        @RequestParam double scalingFactor,
                                        @RequestParam String colorDeficiency
    ) {
        return colorCorrectionService.correctedRGB(red, green, blue, scalingFactor, colorDeficiency);
    }

    @PostMapping("/v2/correctedRGB")
    public CorrectedRGBDto correctedRGBV2(@Valid @RequestBody RGBColorRequestDto rgbColorRequestDto) {
        return colorCorrectionService.correctedRGB(rgbColorRequestDto);
    }

    @GetMapping("/convertToLMS")
    public LMSColorDto convertToLMS(@RequestParam int red, @RequestParam int green, @RequestParam int blue) {
        return colorCorrectionService.convertToLMS(red, green, blue);
    }

}
