package ru.otus.hw.ex17_front_game.metrics;

import java.util.function.Supplier;

public interface MetricsManager {
    void putValue(Meter meterName, long value);

    void incrementValue(Meter meterName);

    void registerGauge(Meter gaugeName, Supplier<Number> gaugeGetter);
}
