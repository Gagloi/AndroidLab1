package com.example.lab1.temperature.temp;

public enum Temperatures {
    KELVIN {
        public double toKelvin(double in) {
            return in;
        }

        public double fromKelvin(double inKelvin) {
            return inKelvin;
        }
    },
    CELSIUS {
        public double toKelvin(double in) {
            return in + 273.15;
        }

        public double fromKelvin(double inKelvin) {
            return inKelvin - 273.15;
        }
    },
    FAHRENHEIT {
        public double toKelvin(double in) {
            return (in + 459.67) * (5.0 / 9.0);
        }

        public double fromKelvin(double inKelvin) {
            return (inKelvin * (9.0 / 5.0)) - 459.67;
        }
    };

    abstract double toKelvin(double in);

    abstract double fromKelvin(double kelvin);

    public double convert(double in, Temperatures to) {
        double inKelvin = this.toKelvin(in);

        return to.fromKelvin(inKelvin);
    }
}
