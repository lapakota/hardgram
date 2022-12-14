package hardsign.server.utils;

import org.hibernate.mapping.Array;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class Helper {
    public String  decodeBase64ToString(byte[] array){
        try {
            return new String(Base64.getDecoder().decode(array));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public byte[] encodeStringToBase64(String str){
        try {

            return Base64.getEncoder().encode(str.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new byte[] {};
        }
    }
}
