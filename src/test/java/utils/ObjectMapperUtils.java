package utils;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class ObjectMapperUtils {


    public static <T> T convertJsonToJavaObject(String json, Class<T> tClass){


        try {
            return new ObjectMapper().readValue(json,tClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }










}
