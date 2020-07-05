package com.tambola.app.utils..validation;

import com.tambola.app.beans.Game;
import com.tambola.app.beans.Player;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class UserNameValidationUtilsTest{

  @InjectMocks
  UserNameValidationUtils userNameValidationUtils;
  
  @Mock
  RepositoryUtils repositoryUtils;
  
  @Test
  public void testUserAlreadyExistsForGame(){
    String userName = "testName";
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGame());
    
    Assert.assertTrue(userNameValidationUtils.ifUserAlreadyExistsForGame("12345678", userName, "87654321");
  }
  
  @Test
  public void testWhenNewUser(){
    String userName = "uniqueName";
    Game game = createGame();
    
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(game);
    
    Assert.assertFalse(userNameValidationUtils.ifUserAlreadyExistsForGame("12345678", userName, "87654321");
    Mockito.verify(repositoryUtils, Mockito.times(1)).saveToRedis(game);
  }
  
  private Game createGame(){
    Game game = createGame();
    
    Player player = new Player();
    player.setUserName("testName");
    
    game.addPlayer(player);
    
    return game;
  }

}
