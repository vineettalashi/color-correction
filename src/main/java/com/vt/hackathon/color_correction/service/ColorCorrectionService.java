package com.vt.hackathon.color_correction.service;

import com.vt.hackathon.color_correction.dto.CorrectedRGBDto;
import com.vt.hackathon.color_correction.dto.LMSColorDto;
import com.vt.hackathon.color_correction.dto.RGBColorRequestDto;
import org.springframework.stereotype.Component;

import static com.vt.hackathon.color_correction.util.Constant.*;
import static java.lang.String.valueOf;

@Component
public class ColorCorrectionService {

    public LMSColorDto convertToLMS(int r, int g, int b) {
        double[] rgb = {r, g, b};
        double[] lms = multiplyMatrix(LMS_MATRIX, rgb);
        return new LMSColorDto(df.format(lms[0]), df.format(lms[1]), df.format(lms[2]));
    }

    public CorrectedRGBDto correctedRGB(RGBColorRequestDto rgbColorRequestDto) {
        return correctedRGB(rgbColorRequestDto.getRed(),
                rgbColorRequestDto.getGreen(),
                rgbColorRequestDto.getBlue(),
                rgbColorRequestDto.getScalingFactor(),
                rgbColorRequestDto.getColorDeficiency());
    }

    public CorrectedRGBDto correctedRGB(int r, int g, int b, double scalingFactor, String colorDeficiency) {
        //Step 1: Conversion from RGB to LMS Color Space:
        double[] rgb = {r, g, b};
        double[] lms = multiplyMatrix(LMS_MATRIX, rgb);

        //Step 2: Simulation of Color Vision Deficiency:
        double[] simulatedLMS = new double[3];
        if ("Red".equalsIgnoreCase(colorDeficiency)) {
            simulatedLMS = simulateProtanopia(lms);
        } else if ("Green".equalsIgnoreCase(colorDeficiency)) {
            simulatedLMS = simulateDeuteranopia(lms);
        }

        //Step 3: Calculate the error between the original and simulated values:
        double[] error = new double[3];
        for (int i = 0; i < 3; i++) {
            error[i] = lms[i] - simulatedLMS[i];
        }

        //Step 4: Daltonization: Adjust the LMS values to redistribute the lost information.
        //        Scaling factor  adjusts the intensity of the correction.
        double[] correctedLMS = new double[3];
        for (int i = 0; i < 3; i++) {
            correctedLMS[i] = lms[i] + scalingFactor * error[i];
        }

        //Step 5: convert the adjusted LMS values back to the RGB color space
        //        using the inverse of the initial transformation matrix

        double[] correctedRGB = multiplyMatrix(INVERSE_LMS_MATRIX, correctedLMS);

        int red = clamp((int) correctedRGB[0]);
        int green = clamp((int) correctedRGB[1]);
        int blue = clamp((int) correctedRGB[2]);
        return new CorrectedRGBDto(valueOf(red), valueOf(green), valueOf(blue));
    }

    private double[] multiplyMatrix(double[][] matrix, double[] vector) {
        double[] result = new double[3];
        for (int i = 0; i < 3; i++) {
            result[i] = 0;
            for (int j = 0; j < 3; j++) {
                result[i] += matrix[i][j] * vector[j];
            }
        }
        return result;
    }

    private double[] simulateProtanopia(double[] lms) {
        return multiplyMatrix(PROTANOPIA_MATRIX, lms);
    }

    private double[] simulateDeuteranopia(double[] lms) {
        return multiplyMatrix(DEUTERANOPIA_MATRIX, lms);
    }
}
