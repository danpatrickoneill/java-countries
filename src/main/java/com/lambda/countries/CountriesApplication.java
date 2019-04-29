package com.lambda.countries;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CountriesApplication {

    public static CountriesList ourCountries;
    public static void main(String[] args) {
        ourCountries = new CountriesList();
        SpringApplication.run(CountriesApplication.class, args);
    }

}
