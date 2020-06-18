package com.example.SpringExamples;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Component
public class ExportMetricsTask {

    Logger logger = LoggerFactory.getLogger(ExportMetricsTask.class);

    private MeterRegistry meterRegistry;

    public ExportMetricsTask(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Scheduled(fixedRate = 10000)
    public void reportMetrics() {
        Stream<String> metrics = meterRegistry.getMeters().stream().map(meter -> {
            String meterName = meter.getId().getName();
            String meterTags = meter.getId().getTags().stream().map(tag -> "{"+tag.getKey()+":"+tag.getValue()+"}").collect(Collectors.joining(","));
            Stream<Measurement> measurementStream = StreamSupport.stream(meter.measure().spliterator(), false);
            String measurements = measurementStream.map(measurement -> "{"+measurement.getStatistic().getTagValueRepresentation()+":"+measurement.getValue()+"}").collect(Collectors.joining(", "));
            return String.format("{name:%s, tags:[%s], measurements: [%s]}", meterName, meterTags, measurements);
        });
        String allMetrics = metrics.collect(Collectors.joining(", "));
        logger.info(String.format("[%s]",allMetrics));
    }
}
