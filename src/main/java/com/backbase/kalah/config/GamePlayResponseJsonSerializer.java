package com.backbase.kalah.config;

import com.backbase.kalah.dto.GamePlayResponse;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.jackson.JsonComponent;

import java.io.IOException;
import java.util.StringJoiner;

@JsonComponent
public class GamePlayResponseJsonSerializer extends JsonSerializer<GamePlayResponse> {

    @Override
    public void serialize(GamePlayResponse gamePlayResponse, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(
                "status",
                getStatusAsKeyValue(gamePlayResponse.getStatus()));
        jsonGenerator.writeEndObject();
    }

    private String getStatusAsKeyValue(int[] status) {
        StringJoiner sj = new StringJoiner(",", "{", "}");
        for (int i = 0; i < status.length; i++) {
            sj.add("\""+(i+1)+"\":\""+status[i]+"\"");
        }
        System.out.println(sj.toString());
        return sj.toString();
    }
}
