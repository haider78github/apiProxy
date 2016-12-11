package com.softech.ls360.lms.proxy.json.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DoubleJsonSerializer extends JsonSerializer<Double> {

	 @Override
	  public void serialize(Double value, JsonGenerator jgen, SerializerProvider provider) 
			  throws IOException, JsonProcessingException {
	      //jgen.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toString());
	      jgen.writeString(value.toString());
	  }
	
}
