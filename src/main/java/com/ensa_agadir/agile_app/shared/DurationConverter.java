package com.ensa_agadir.agile_app.shared;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import java.time.Duration;

@Converter(autoApply = true)
public class DurationConverter implements AttributeConverter<Duration, Long> {

    @Override
    public Long convertToDatabaseColumn(Duration duration) {
        // Stocke null si null, sinon le nombre total de minutes
        return (duration == null) ? null : duration.toMinutes();
    }

    @Override
    public Duration convertToEntityAttribute(Long minutes) {
        // Reconvertit les minutes en Duration Java
        return (minutes == null) ? null : Duration.ofMinutes(minutes);
    }
}