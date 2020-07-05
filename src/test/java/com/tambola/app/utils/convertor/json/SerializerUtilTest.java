package com.tambola.app.utils.convertor.json;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.uti.List;

@RunWith(PowerMockRunner.class)
public class SerializerUtilTest{
  
  @Test
  public void getJsonString(){
    List<String> testList = new ArrayList<>();
    testList.add("test1");
    
    String result = null;
    Exception ex = null;
    
    try{
      result = SerializerUtil.getJsonString(testList);
    }catch(IOException ie){
      ex = ie;
    }
    
    Assert.assertTrue(result.getClass().equals(String.class));
    Assert.assertEquals(null, ex);
  }
}
