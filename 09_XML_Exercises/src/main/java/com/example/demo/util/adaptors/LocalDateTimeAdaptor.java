package com.example.demo.util.adaptors;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

public class LocalDateTimeAdaptor extends XmlAdapter <String, LocalDateTime> {
    @Override
    public LocalDateTime unmarshal(String string) throws Exception {
        return LocalDateTime.parse(string);
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        return localDateTime.toString();
    }
}
