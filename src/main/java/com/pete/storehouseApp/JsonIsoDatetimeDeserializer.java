package com.pete.storehouseApp;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * Helps deserialization of {@link Date} to JSON string using
 * {@link DateUtil#DATETIME_FORMAT_ISO DATETIME_FORMAT_ISO}.
 *
 */
@Component
public class JsonIsoDatetimeDeserializer extends JsonDeserializer<Date> {

    /**
     * Parses a datetime string which is formatted in
     * {@link DateUtil#DATETIME_FORMAT_ISO DATETIME_FORMAT_ISO ISO 8601} format.
     *
     * @return a date object or null if the given {@link JsonParser#getText()
     *         string} is empty or null.
     * @throws RuntimeException
     *             if token string cannot be parsed.
     * @throws IOException
     *             if token string cannot be read
     */
    @Override
    public Date deserialize(JsonParser jsonParser,
                            DeserializationContext context) throws IOException {

        String date = jsonParser.getText();

        if (ObjectUtils.isEmpty(date)) {
            return null;
        }

        try {
            return DateUtil.DATE_FORMAT_EL.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}