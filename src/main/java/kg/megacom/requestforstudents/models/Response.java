package kg.megacom.requestforstudents.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response {

    private String message;

    public static Response getResponse(){

        return Response.builder()
                .message("")
                .build();
    }

}