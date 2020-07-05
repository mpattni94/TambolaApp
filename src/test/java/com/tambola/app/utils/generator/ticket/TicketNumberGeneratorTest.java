package com.tambola.app.utils.generator.ticket;

imort com.tambola.app.beans.Game;
import com.tambola.app.constants.TicketConstants;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashSet;
import java.util.Set;

@RunWith(PowerMockRunner.class)
public class TicketNumberGeneratorTest{
   @InjectMocks
  TicketNumberGenerator ticketNumberGenerator;
  
  @Mock
  RepositoryUtils repositoryUtils;
  
  @Mock HashSet<String> numbersDone;
  
  @Test
  public void testWhenAllNumbersHaveBeenGenerated(){
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGameWhenNumbersExhausted());
    Mockito.when(numbersDone.size()).thenReturn(TicketConstants.HIGHEST_VALUE_ALLOWED);
    
    int expectedResult = -1;
    int result = ticketNumberGenerator.generateRandomNumberForGameAndStore("12345678");
    
    Assert.assertEquals(expectedResult, result);
  }
  
  @Test
  public void testGenerateRandomNumberForGameAndStore(){
    Mockito.when(repositoryUtils.retrieveGameObjectFromRedis(Matchers.anyString())).thenReturn(createGame());
    
    int expectedResult = -1;
    int result = ticketNumberGenerator.generateRandomNumberForGameAndStore("12345678");
    
    ssert.assertEquals(expectedResult, result);
  }
  
  private Game createGameWhenNumbersExhausted(){
     Game game = createEmptyGame();
    game.setNumbersDoneSoFar(numbersDone);
    
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
}
