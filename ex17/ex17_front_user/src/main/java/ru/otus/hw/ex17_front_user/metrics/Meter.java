package ru.otus.hw.ex17_front_user.metrics;

public enum Meter {
    REQUEST_COUNTER("request_counter"),
    REQUEST_DURATION("request_duration");

    private final String meterName;

    Meter(String meterName) {
        this.meterName = meterName;
    }

    public String getMeterName() {
        return meterName;
    }
}
