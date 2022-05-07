package com.pete.storehouseApp;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * Helps serialization of {@link Date} to JSON string using
 * {@link DateUtil#DATETIME_FORMAT_ISO DATETIME_FORMAT_ISO}.
 *
 */
@Component
public class JsonIsoDatetimeSerializer extends JsonSerializer<Date> {

    /**
     * Serialises a {@link Date} object to an
     * {@link DateUtil#DATETIME_FORMAT_ISO DATETIME_FORMAT_ISO ISO 8601}
     * formatted string .
     *
     *
     * @throws IOException
     *             if formatted string cannot be written to the given
     *             {@link JsonGenerator}
     */
    @Override
    public void serialize(Date date, JsonGenerator gen,
                          SerializerProvider provider) throws IOException {

        String formattedDate = DateUtil.DATE_FORMAT_EL.format(date);
        gen.writeString(formattedDate);
    }

}