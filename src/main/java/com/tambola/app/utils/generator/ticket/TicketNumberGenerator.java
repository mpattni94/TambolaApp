package com.tambola.app.utils.generator.ticket;

import com.tambola.app.beans.Game;
import com.tambola.app.constants.TicketConstants;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.Set;

@Component
public class TicketNumberGenerator{
  
  @Autowired
  RepositoryUtils repositoryUtils;
  
  Random random = new Random();
  
  public int generateRandomNumberForGameAndStore(String gameId){
    int number;
    Game game = repositoryUtils.retrieveGameFromRedis(gameId);
    Set<String> numbersAlreadyDone = game.getNumbersDoneSoFar();
    if(numbersAlreadyDone.size() < TicketConstants.HIGHEST_VALUE_ALLOWED){
      do{
        number = random.nextInt(TicketConstants.HIGHEST_VALUE_ALLOWED);
      }while(numbersAlreadyDone.contains(number + "") || number == 0);
      numbersAlreadyDone.add(number + "");
      game.setNumbersDoneSoFar(numbersAlreadyDone);
      repositoryUtils.saveToRedis(game);
    }else{
      number = -1;
    }
    return number;
  }
}
