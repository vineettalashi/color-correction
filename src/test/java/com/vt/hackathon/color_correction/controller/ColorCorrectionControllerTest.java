package com.vt.hackathon.color_correction.controller;

import com.vt.hackathon.color_correction.dto.CorrectedRGBDto;
import com.vt.hackathon.color_correction.dto.RGBColorRequestDto;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class ColorCorrectionControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @SneakyThrows
    @Test
    void correctedRGB() {

        String urlTemplate = UriComponentsBuilder.fromUriString("/correctedRGB")
                .queryParam("red", "{red}")
                .queryParam("green", "{green}")
                .queryParam("blue", "{blue}")
                .queryParam("scalingFactor", "{scalingFactor}")
                .queryParam("colorDeficiency", "{colorDeficiency}")
                .encode()
                .toUriString();

        Map<String, Object> map = new HashMap<>();
        map.put("red", 26);
        map.put("green", 36);
        map.put("blue", 74);
        map.put("scalingFactor", 0.5);
        map.put("colorDeficiency", "Red");

        ResponseEntity<CorrectedRGBDto> response = testRestTemplate.getForEntity(urlTemplate, CorrectedRGBDto.class, map);
        assertThat(response).isNotNull();
        Assertions.assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(200));
        assertThat(response.getBody().getBlue()).isNotNull();
        assertThat(response.getBody().getRed()).isNotNull();
        assertThat(response.getBody().getGreen()).isNotNull();
    }

    @SneakyThrows
    @Test
    void correctedRGBV2() {
        RGBColorRequestDto rgbColorRequestDto = RGBColorRequestDto.builder()
                .red(26)
                .green(38)
                .blue(74)
                .scalingFactor(0.5)
                .colorDeficiency("Red")
                .build();

        ResponseEntity<CorrectedRGBDto> response = testRestTemplate.postForEntity("/v2/correctedRGB", rgbColorRequestDto, CorrectedRGBDto.class);
        assertThat(response).isNotNull();
        Assertions.assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode());
        assertThat(response.getBody().getBlue()).isNotNull();
        assertThat(response.getBody().getRed()).isNotNull();
        assertThat(response.getBody().getGreen()).isNotNull();

    }
}