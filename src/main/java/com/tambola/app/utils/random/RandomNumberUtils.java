package com.tambola.app.utils.random;

import com.tambola.app.constants.GenericConstants;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class RandomNumberUtils{
  Random random = new Random();
  
  public String generateRandomString(int stringLength){
    if(stringLength == 0){
      stringLength = 8;
    }
    
    StringBuilder sb = new StringBuilder();
    
    for(int i=0; i<stringLength; i++){
       sb.append(GenericConstants.charArray[random.nextInt(GenericConstants.charArray.length)]);
    }
    return sb.toString();
  }
}
