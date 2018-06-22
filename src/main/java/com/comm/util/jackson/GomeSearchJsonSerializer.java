package com.comm.util.jackson;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class GomeSearchJsonSerializer extends JsonSerializer<Object>{

	@Override
	public void serialize(Object obj, JsonGenerator jgen, SerializerProvider provider) throws IOException {
		jgen.writeString("");
	}

}
