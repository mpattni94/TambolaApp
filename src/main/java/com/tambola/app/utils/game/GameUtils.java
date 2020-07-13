package com.tambola.app.utils.game;

import com.tambola.app.beans.Game;
import com.tambola.app.beans.Player;
import com.tambola.app.constants.ResultStrings;
import com.tambola.app.utils.convertor.json.SerializerUtil;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Set;

@Component
public class GameUtils{

  @Autowired
  RepositoryUtils repositoryUtils;
  
  public String getNumbersDrawnSoFar(String gameId) throws IOException{
    String result;
    Game game = repositoryUtils.retrieveGameFromRedis(gameId);
    Set<String> numbers = game.getNumbersDoneSoFar();
    if(!(numbers.size() > 0)){
      result = ResultStrings.NO_NUMBERS_DRAWN_YET;
    }else{
      result = SerializerUtil.getJsonString(numbers);
    }
    return result;
  }
  
  public String getGameStats(String gameId) throws IOException{
    StringBuilder result = new StringBuilder();
    Game game = repositoryUtils.retrieveGameFromRedis(gameId);
    
    String numbersDrawnSoFar = getNumbersDrawnSoFar(gameId);
    result.append("Numbers drawn in this game: " + numbersDrawnSoFar + "<br>");
    
    List<Player> players = game.getPlayers();
    if(players != null){
      result.append("Number of players/tickets in the game: " + players.size());
    }else{
      result.append("No players in the game yet");
    }
    
    return result.toString();
  }

}
