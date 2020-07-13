package com.tambola.app.utils.convertor.json;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SerializerUtil{

  private static ObjectMapper mapper;
  
  static{
    mapper = new ObjectMapper();
    mapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
  }
  
  public static String getJsonString(Object bean) throws IOException{
    String result = null;
    if(bean != null){
      result = mapper.writeValueAsString(bean);
    }
    return result;
  }

}
