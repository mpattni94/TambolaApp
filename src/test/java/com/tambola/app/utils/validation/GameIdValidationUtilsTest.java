package com.tambola.app.utils..validation;

import com.tambola.app.beans.Game;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.NoSuchElementFoundException;

@RunWith(PowerMockRunner.class)
public class GameUtilsTest{
  @InjectMocks
  GameIdValidationUtils gameIdValidationUtils;
  
  @Mock
  RepositoryUtils repositoryUtils;

  @Test
  public void testGameIdExists(){
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGame());
    
    Assert.assertTrue(gameIdValidationUtils.gameIdExists("12345678"));
  }
  
  @Test
  public void testWhenGameIdDoesNotExist(){
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenThrow(NoSuchElementFoundException.class);
    
    Assert.assertFalse(gameIdValidationUtils.gameIdExists("12345678"));
  }

  @Test
  public void testWhenGameIdIsValidAndGameIdExists(){
    String gameId = "12345678";
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGame());
    
    Assert.assertTrue(gameIdValidationUtils.gameIdExists("12345678"));
  }
  
  @Test
  public void testWhenGameIdIsInvalidAndGameIdExists(){
    String gameId = "1234567";
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGame());
    
    Assert.assertFalse(gameIdValidationUtils.gameIdExists("12345678"));
  }
  
  @Test
  public void testWhenGameIdIsValidAndGameIdNotExists(){
    String gameId = "12345678";
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenThrow(NoSuchElementFoundException.class);
    
    Assert.assertFalse(gameIdValidationUtils.gameIdExists("12345678"));
  }
  
  @Test
  public void testWhenGameIdIsInvalidAndGameIdNotExists(){
    String gameId = "12345677";
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenThrow(NoSuchElementFoundException.class);
    
    Assert.assertFalse(gameIdValidationUtils.gameIdExists("12345678"));
  }
  
  private Game createGame(){
     Game game = new Game();    
    return game;
  }
}
