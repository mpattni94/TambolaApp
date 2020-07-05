package com.tambola.app.utils.game;

import com.tambola.app.beans.Game;
import com.tambola.app.beans.Player;
import com.tambola.app.constants.ResultStrings;
import com.tambola.app.utils.convertor.json.SerializerUtil;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.io.Exception;
import java.util.HashSet;
import java.util.Set;

@RunWith(PowerMockRunner.class)
public class GameUtilsTest{
  @InjectMocks
  GameUtils gameUtils;
  
  @Mock
  RepositoryUtils repositoryUtils;
  
  @Test
  public void testNoNumbersDrawn(){
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createEmptyGame());
    
    String expectedResult = ResultStrings.NO_NUMBERS_DRAWN_YET;
    String result = gameUtils.getNumbersDrawnSoFar("12345678");
    
    Assert.assertEquals(expectedResult, result);
  }
  
  @Test
  public void testGetNumbersDrawn(){
     Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGame());
     
     Set<String> numbersDoneSoFar = new HashSet<>();
     numbersDoneSoFar.add("1");
     numbersDoneSoFar.add("2");
     
     String expectedResult = SerializerUtil.getJsonString(numbersDoneSoFar);
     String result = gameUtils.getNumbersDrawnSoFar("12345678");
     
     Assert.assertEquals(expectedResult. result);
  }
  
  @Test
  public void getGameStatsWhenNoPlayers(){
     Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGame());
     
     String expectedResult = "Numbers drawn in this game: [\"1\",\"2\"]<br>No players in the game yet";
     String result = gameUtils.getGameStats("12345678");
     
     Assert.assertEquals(expectedResult, result);
  }
  
  @Test
  public void testGameStatsWhenPlayersAdded(){
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGameWithPlayers());
    
    String expectedResult = "Numbers drawn in this game: [\"1\",\"2\"]<br>Number of players/tickets in this game: 1";
    String result = gameUtils.getGameStats("12345678");
    
    Assert.assertEquals(expectedResult, result);
  }
  
  private Game createEmptyGame(){
    Game game = new Game();
    game.setGameId("12345678");
    
    Set<String> numbers = new HashSet<>();
    game.setNumbersDoneSoFar(numbers);
    
    return game;
  }
  
  private Game createGame(){
     Game game = createEmptyGame();
     
     Set<String> numbers = new HashSet<>();
     numbers.add("1");
     numbers.add("2");
    game.setNumbersDoneSoFar(numbers);
    
    return game;
  }
  
  private Game createGameWithPlayers(){
    Game game = createGame();
    
    Player player = new Player();
    player.setUserName("testName");
    
    game.addPlayer(player);
    
    return game;
  }
  
  
}
