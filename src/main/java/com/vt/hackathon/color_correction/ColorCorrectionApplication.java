package com.vt.hackathon.color_correction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(value = "com.vt.hackathon.color_correction")
public class ColorCorrectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ColorCorrectionApplication.class, args);
	}

}
