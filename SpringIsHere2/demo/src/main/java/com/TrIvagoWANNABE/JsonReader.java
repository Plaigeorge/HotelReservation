package com.TrIvagoWANNABE;

import com.TrIvagoWANNABE.model.Hotel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class JsonReader {
    private static final String JSON_FILE_PATH = "hotels.json";
    public static List<Hotel> loadHotels() {
        ObjectMapper mapper = new ObjectMapper();
        try (InputStream inputStream = new ClassPathResource(JSON_FILE_PATH).getInputStream()) {
            return mapper.readValue(inputStream, mapper.getTypeFactory().constructCollectionType(List.class, Hotel.class));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void saveHotels(List<Hotel> hotels) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            File file = new ClassPathResource(JSON_FILE_PATH).getFile();
            mapper.writeValue(file, hotels);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
