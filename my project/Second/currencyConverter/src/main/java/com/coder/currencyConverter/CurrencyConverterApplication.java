package com.coder.currencyConverter;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CurrencyConverterApplication {
	public static void main(String[] args) {
		Application.launch(ChartApplication.class,args);
	}

}
