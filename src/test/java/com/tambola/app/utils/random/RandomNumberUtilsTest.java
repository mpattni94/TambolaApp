package com.tambola.app.utils.random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class RandomNumberUtilsTest{

  private RandomNumberUtils randomUtils = new RandomNumberUtils();
  
  @Test
  public void testGenerateRandomStringOfLengthFive(){
    int expectedResult = 5;
    String randomString = randomUtils.generateRandomString(expectedResult);
    int result = randomString.length();
    
    Assert.assertEquals(expectedResult, result);
  }
  
  @Test
  public void testStringLengthWhenNoLengthSpecified(){
    int exepectedResult = 8;
    String randomString = randomUtils.generateRandomString(0);
    int result = randomString.length();
    
    Assert.assertEquals(expectedResult, result);
  }
}
