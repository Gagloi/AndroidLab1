package com.example.lab1.temperature.temp;

public enum  Weights {

    GRAM(0.001), KILOGRAM(1.0), TONE(1000), CARAT(0.0002), POUND(0.4535), PUD(16.3806);

    private final double k;
    private Weights(double k){
        this.k = k;
    };

    double toKilo(double in) {
        return in * k;
    };
    double fromKilo(double kilo) {
        return kilo / k;
    };
    public double convert(double in, Weights to) {
        double inKilo = this.toKilo(in);
        return to.fromKilo(inKilo);
    }
}
