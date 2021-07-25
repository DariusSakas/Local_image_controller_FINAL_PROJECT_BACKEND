package com.example.local_image_controller_final_project_backend.serializer;

import com.example.local_image_controller_final_project_backend.model.AlbumModel;
import com.example.local_image_controller_final_project_backend.model.ImageModel;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CustomImageModelJSONDeserializer extends StdDeserializer<ImageModel> {


    protected CustomImageModelJSONDeserializer(Class<?> vc) {
        super(vc);
    }

    /**
     * Deserialization of JSON when is given only album id, not whole object.
     * Method brakes JSON into nodes by values.
     * albumModel node is deserialized from id into new AlbumModel object with set id
     */
    @Override
    public ImageModel deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = codec.readTree(jsonParser);
        //TODO: Set all node values for ImageModel object
        JsonNode albumModelNode = node.get("albumModel");
        JsonNode
        Long albumModelID = albumModelNode.asLong();

        return new ImageModel(new AlbumModel(albumModelID, null ));
    }
}
