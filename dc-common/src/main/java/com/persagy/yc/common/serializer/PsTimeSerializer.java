package com.persagy.yc.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.persagy.yc.common.lang.PsTime;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @Description 时间序列化
 * @author Charlie Yu
 * @version 1.0 2021-04-08
 */
@JsonComponent
public class PsTimeSerializer extends JsonSerializer<PsTime> {
    @Override
    public void serialize(PsTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getMillis());
    }
}