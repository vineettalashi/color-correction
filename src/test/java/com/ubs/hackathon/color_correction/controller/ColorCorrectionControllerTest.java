package com.ubs.hackathon.color_correction.controller;

import com.ubs.hackathon.color_correction.dto.CorrectedRGBDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ColorCorrectionControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private RestTemplate restTemplate;

    @SneakyThrows
    @Test
    void correctedRGB() {

        Map<String, Object> map = new HashMap<>();
        map.put("red", 26);
        map.put("green", 36);
        map.put("blue", 74);
        map.put("scalingFactor", 0.5);
        map.put("colorDeficiency", "Red");

        String url = "http://localhost:" + port + "/correctedRGB";
        CorrectedRGBDto response = restTemplate.getForObject(url, CorrectedRGBDto.class, map);
        assertThat(response).isNotNull();
        assertThat(response.getBlue()).isNotNull();
    }

    @SneakyThrows
    @Test
    void correctedRGBV2() {

    }

    @SneakyThrows
    @Test
    void convertToLMS() {

    }
}