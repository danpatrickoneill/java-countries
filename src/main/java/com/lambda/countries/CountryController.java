package com.lambda.countries;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/data")
public class CountryController
{
    // Name-based endpoints
    // localhost:8080/data/names/***
    @RequestMapping(value = "/names/all")
    public ResponseEntity<?> getAllCountries()
    {
        CountriesApplication.ourCountries.countriesList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(CountriesApplication.ourCountries.countriesList, HttpStatus.OK);
    }
    @GetMapping(value = "/names/start/{letter}")
    public ResponseEntity<?> getCountriesByLetter(@PathVariable char letter)
    {
        ArrayList<Country> resultList = CountriesApplication.ourCountries.findCountries(c -> c.getName().toLowerCase().charAt(0) == Character.toLowerCase(letter));
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
    @GetMapping(value = "/names/size/{length}")
    public ResponseEntity<?> getCountriesByNameLength(@PathVariable int length)
    {
        ArrayList<Country> resultList = CountriesApplication.ourCountries.findCountries(c -> c.getName().length() > length);
        resultList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }

    // Population-based endpoints
    // localhost:8080/data/population/***
    @GetMapping(value = "/population/size/{population}")
    public ResponseEntity<?> getCountriesByMinimumPopulation(@PathVariable long population)
    {
        ArrayList<Country> resultList = CountriesApplication.ourCountries.findCountries(c -> c.getPopulation() >= population);
        resultList.sort((c1, c2) -> c1.getName().compareToIgnoreCase(c2.getName()));
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
    @GetMapping(value = "/population/min")
    public ResponseEntity<?> getCountryWithSmallestPopulation()
    {
        CountriesApplication.ourCountries.countriesList.sort((c1, c2) -> (int)(c1.getPopulation() - c2.getPopulation()));
        return new ResponseEntity<>(CountriesApplication.ourCountries.countriesList.get(0), HttpStatus.OK);
    }

    @GetMapping(value = "/population/max")
    public ResponseEntity<?> getCountryWithLargestPopulation()
    {
        CountriesApplication.ourCountries.countriesList.sort((c1, c2) -> (int)(c2.getPopulation() - c1.getPopulation()));
        return new ResponseEntity<>(CountriesApplication.ourCountries.countriesList.get(0), HttpStatus.OK);
    }

    // Median age-based endpoints
    // localhost:8080/data/age/***
    @GetMapping(value = "/age/age/{age}")
    public ResponseEntity<?> getCountriesByMinimumMedianAge(@PathVariable int age)
    {
        ArrayList<Country> resultList = CountriesApplication.ourCountries.findCountries(c -> c.getMedianAge() >= age);
        return new ResponseEntity<>(resultList, HttpStatus.OK);
    }
    @GetMapping(value = "/age/min")
    public ResponseEntity<?> getCountryWithSmallestMedianAge()
    {
        CountriesApplication.ourCountries.countriesList.sort((c1, c2) -> (c1.getMedianAge() - c2.getMedianAge()));
        return new ResponseEntity<>(CountriesApplication.ourCountries.countriesList.get(0), HttpStatus.OK);
    }

    @GetMapping(value = "/age/max")
    public ResponseEntity<?> getCountryWithLargestMedianAge()
    {
        CountriesApplication.ourCountries.countriesList.sort((c1, c2) -> (int)(c2.getMedianAge() - c1.getMedianAge()));
        return new ResponseEntity<>(CountriesApplication.ourCountries.countriesList.get(0), HttpStatus.OK);
    }
}
