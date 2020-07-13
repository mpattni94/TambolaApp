package com.tambola.app.utils.validation;

import com.tambola.app.beans.Game;
import com.tambola.app.constants.GameConstants;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class GameIdValidationUtils{
  @Autowired
  RepositoryUtils repositoryUtils;
  
  public boolean isGameValid(final String gameId){
    return ((isLengthValid(gameId) && gameIdExists(gameId)));
  }
  
  public boolean isLengthValid(String gameId){
    boolean result = false;
    if(gameId.length() == GameConstants.GAME_ID_LENGTH){
      result = true;
    }
    return result;
  }
  
  public boolean gameIdExists(String gameId){
    boolean result = true;
    try{
      Game game = repositoryUtils.retrieveGameFromRedis(gameId);
    }catch(NoSuchElementException e){
        result = false;
    }
    return result;
  }
}
