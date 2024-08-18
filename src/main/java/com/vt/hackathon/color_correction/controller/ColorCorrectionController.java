package com.vt.hackathon.color_correction.controller;

import com.vt.hackathon.color_correction.dto.CorrectedRGBDto;
import com.vt.hackathon.color_correction.dto.LMSColorDto;
import com.vt.hackathon.color_correction.dto.RGBColorRequestDto;
import com.vt.hackathon.color_correction.service.ColorCorrectionService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController("/color-correction")
@Slf4j
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
                                        @RequestParam String colorDeficiency) {
        return colorCorrectionService.correctedRGB(red, green, blue, scalingFactor, colorDeficiency);
    }

    @PostMapping("/v2/correctedRGB")
    public CorrectedRGBDto correctedRGBV2(@Valid @RequestBody RGBColorRequestDto rgbColorRequestDto) {
        log.info("Received Request for Correction :: {}", rgbColorRequestDto);
        return colorCorrectionService.correctedRGB(rgbColorRequestDto);
    }

    @GetMapping("/convertToLMS")
    public LMSColorDto convertToLMS(@RequestParam int red, @RequestParam int green, @RequestParam int blue) {
        return colorCorrectionService.convertToLMS(red, green, blue);
    }

}
