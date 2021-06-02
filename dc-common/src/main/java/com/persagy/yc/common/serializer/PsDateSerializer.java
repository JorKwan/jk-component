package com.persagy.yc.common.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.persagy.yc.common.lang.PsDate;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @Description 日期序列化
 * @author Charlie Yu
 * @version 1.0 2021-04-08
 */
@JsonComponent
public class PsDateSerializer extends JsonSerializer<PsDate> {
    @Override
    public void serialize(PsDate value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeNumber(value.getMillis());
    }
}
