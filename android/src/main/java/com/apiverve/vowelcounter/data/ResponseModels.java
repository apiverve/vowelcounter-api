// Converter.java

// To use this code, add the following Maven dependency to your project:
//
//
//     com.fasterxml.jackson.core     : jackson-databind          : 2.9.0
//     com.fasterxml.jackson.datatype : jackson-datatype-jsr310   : 2.9.0
//
// Import this package:
//
//     import com.apiverve.data.Converter;
//
// Then you can deserialize a JSON string with
//
//     VowelCounterData data = Converter.fromJsonString(jsonString);

package com.apiverve.vowelcounter.data;

import java.io.IOException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.*;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.OffsetTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;

public class Converter {
    // Date-time helpers

    private static final DateTimeFormatter DATE_TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_DATE_TIME)
            .appendOptional(DateTimeFormatter.ISO_INSTANT)
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ssX"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetDateTime parseDateTimeString(String str) {
        return ZonedDateTime.from(Converter.DATE_TIME_FORMATTER.parse(str)).toOffsetDateTime();
    }

    private static final DateTimeFormatter TIME_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ISO_TIME)
            .appendOptional(DateTimeFormatter.ISO_OFFSET_TIME)
            .parseDefaulting(ChronoField.YEAR, 2020)
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter()
            .withZone(ZoneOffset.UTC);

    public static OffsetTime parseTimeString(String str) {
        return ZonedDateTime.from(Converter.TIME_FORMATTER.parse(str)).toOffsetDateTime().toOffsetTime();
    }
    // Serialize/deserialize helpers

    public static VowelCounterData fromJsonString(String json) throws IOException {
        return getObjectReader().readValue(json);
    }

    public static String toJsonString(VowelCounterData obj) throws JsonProcessingException {
        return getObjectWriter().writeValueAsString(obj);
    }

    private static ObjectReader reader;
    private static ObjectWriter writer;

    private static void instantiateMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleModule module = new SimpleModule();
        module.addDeserializer(OffsetDateTime.class, new JsonDeserializer<OffsetDateTime>() {
            @Override
            public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
                String value = jsonParser.getText();
                return Converter.parseDateTimeString(value);
            }
        });
        mapper.registerModule(module);
        reader = mapper.readerFor(VowelCounterData.class);
        writer = mapper.writerFor(VowelCounterData.class);
    }

    private static ObjectReader getObjectReader() {
        if (reader == null) instantiateMapper();
        return reader;
    }

    private static ObjectWriter getObjectWriter() {
        if (writer == null) instantiateMapper();
        return writer;
    }
}

// VowelCounterData.java

package com.apiverve.vowelcounter.data;

import com.fasterxml.jackson.annotation.*;

public class VowelCounterData {
    private long vowels;
    private long consonants;
    private long totalLetters;
    private double vowelPercentage;
    private double consonantPercentage;
    private VowelBreakdown vowelBreakdown;
    private long textLength;

    @JsonProperty("vowels")
    public long getVowels() { return vowels; }
    @JsonProperty("vowels")
    public void setVowels(long value) { this.vowels = value; }

    @JsonProperty("consonants")
    public long getConsonants() { return consonants; }
    @JsonProperty("consonants")
    public void setConsonants(long value) { this.consonants = value; }

    @JsonProperty("totalLetters")
    public long getTotalLetters() { return totalLetters; }
    @JsonProperty("totalLetters")
    public void setTotalLetters(long value) { this.totalLetters = value; }

    @JsonProperty("vowelPercentage")
    public double getVowelPercentage() { return vowelPercentage; }
    @JsonProperty("vowelPercentage")
    public void setVowelPercentage(double value) { this.vowelPercentage = value; }

    @JsonProperty("consonantPercentage")
    public double getConsonantPercentage() { return consonantPercentage; }
    @JsonProperty("consonantPercentage")
    public void setConsonantPercentage(double value) { this.consonantPercentage = value; }

    @JsonProperty("vowelBreakdown")
    public VowelBreakdown getVowelBreakdown() { return vowelBreakdown; }
    @JsonProperty("vowelBreakdown")
    public void setVowelBreakdown(VowelBreakdown value) { this.vowelBreakdown = value; }

    @JsonProperty("textLength")
    public long getTextLength() { return textLength; }
    @JsonProperty("textLength")
    public void setTextLength(long value) { this.textLength = value; }
}

// VowelBreakdown.java

package com.apiverve.vowelcounter.data;

import com.fasterxml.jackson.annotation.*;

public class VowelBreakdown {
    private long a;
    private long e;
    private long i;
    private long o;
    private long u;

    @JsonProperty("a")
    public long getA() { return a; }
    @JsonProperty("a")
    public void setA(long value) { this.a = value; }

    @JsonProperty("e")
    public long getE() { return e; }
    @JsonProperty("e")
    public void setE(long value) { this.e = value; }

    @JsonProperty("i")
    public long getI() { return i; }
    @JsonProperty("i")
    public void setI(long value) { this.i = value; }

    @JsonProperty("o")
    public long getO() { return o; }
    @JsonProperty("o")
    public void setO(long value) { this.o = value; }

    @JsonProperty("u")
    public long getU() { return u; }
    @JsonProperty("u")
    public void setU(long value) { this.u = value; }
}