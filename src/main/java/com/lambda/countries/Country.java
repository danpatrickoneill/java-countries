package com.lambda.countries;

import java.util.concurrent.atomic.AtomicLong;

public class Country {

    // fields
    private static final AtomicLong counter = new AtomicLong(); // thread safe
    private long id;
    private String name;
    private long population;
    private long landMass;
    private int medianAge;

    public Country(String name, long population, long landMass, int medianAge) {
        this.id =counter.incrementAndGet();
        this.name = name;
        this.population = population;
        this.landMass = landMass;
        this.medianAge = medianAge;
    }

    public Country(Country toClone)
    {
        id = toClone.getId();
        name = toClone.getName();
        population = toClone.getPopulation();
        landMass = toClone.getLandMass();
        medianAge = toClone.getMedianAge();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public long getPopulation() {
        return population;
    }

    public long getLandMass() {
        return landMass;
    }

    public int getMedianAge() {
        return medianAge;
    }
}
