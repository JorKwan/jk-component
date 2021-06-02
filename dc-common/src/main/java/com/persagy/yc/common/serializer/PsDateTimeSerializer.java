package com.persagy.yc.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.persagy.yc.common.lang.PsDateTime;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @Description 时间序列化
 * @author Charlie Yu
 * @version 1.0 2021-04-08
 */
@JsonComponent
public class PsDateTimeSerializer extends JsonSerializer<PsDateTime> {
    @Override
    public void serialize(PsDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getMillis());
    }
}
