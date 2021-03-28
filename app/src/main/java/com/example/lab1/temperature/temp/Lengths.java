package com.example.lab1.temperature.temp;

public enum Lengths {
    CENTIMETER(0.01), METER(1), KILOMETER(1000), INCH(0.0254), FOOT(0.3048), YARD(0.9144);

    private final double k;
    private Lengths(double k){
        this.k = k;
    };

    double toMeter(double in) {
        return in * k;
    };

    double fromMeter(double meter) {
        return meter / k;
    };

    public double convert(double in, Lengths to) {
        double inMeter = this.toMeter(in);

        return to.fromMeter(inMeter);
    }
}
