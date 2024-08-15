package com.ubs.hackathon.color_correction.util;

import java.text.DecimalFormat;

public class Constant {

    public final static double[][] LMS_MATRIX = {
            {0.3811, 0.5783, 0.0402},
            {0.1967, 0.7244, 0.0782},
            {0.0241, 0.1288, 0.8444}
    };

    public final static double[][] INVERSE_LMS_MATRIX = {
            {4.4679, -3.5873, 0.1193},
            {-1.2186, 2.3809, -0.1624},
            {0.0497, -0.2439, 1.2045}
    };

    //Red color blind
    public final static double[][] PROTANOPIA_MATRIX = {
            {0, 1, 0},
            {0, 1, 0},
            {0, 0, 1}
    };

    //Green color blind
    public final static double[][] DEUTERANOPIA_MATRIX = {
            {1, 0, 0},
            {1, 0, 0},
            {0, 0, 1}
    };

    public static int clamp(int value) {
        return Math.max(0, Math.min(255, value));
    }

    public static final DecimalFormat df = new DecimalFormat("0.000");
}
