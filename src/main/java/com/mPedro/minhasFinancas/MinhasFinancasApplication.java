package com.mPedro.minhasFinancas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MinhasFinancasApplication {

	public void testandoLiveReload() {
		System.out.println("Live Reload funcionandoO");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MinhasFinancasApplication.class, args);
	}

}
