package com.persagy.yc.common.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.persagy.yc.common.lang.PsDate;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;

/**
 * @Description 日期反序列化
 * @author Charlie Yu
 * @version 1.0 2021-04-08
 */
@JsonComponent
public class PsDateDeserializer extends JsonDeserializer<PsDate> {
    @Override
    public PsDate deserialize(JsonParser p, DeserializationContext context) throws IOException, JsonProcessingException {
        Long millis = p.getValueAsLong();
        if(millis <= 0) {
            return null;
        }
        return PsDate.parsePsDate(millis);
    }
}
