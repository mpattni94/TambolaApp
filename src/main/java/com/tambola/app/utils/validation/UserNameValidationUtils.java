package com.tambola.app.utils.validation;

import com.tambola.app.beans.Game;
import com.tambola.app.beans.Ticket;
import com.tambola.app.beans.Player;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserNameValidationUtils{
  
  @Autowired
  RepositoryUtils repositoryUtils;
  
  public boolean ifUserAlreadyExistsForGame(String gameId, String userName, String ticketId){
    boolean result = false;
    Game game;
    if(null != userName && userName.length() > 0){
      game = repositoryUtils.retrieveGameFromRedis(gameId);
      List<Player> players = game.getPlayers();
      if(players != null){
        for(Player player: players){
          if(player.getUserName() == userName){
            result = true;
            break;
          }
        }
      }
      if(!result){
        Player player = new Player();
        player.setUserName(userName);
        
        Ticket ticket = new Ticket();
        ticket.setTicketId(ticketId);
        player.setTicket(ticket);
        
        game.addPlayer(player);
        
        repositoryUtils.saveToRedis(game);
      }
    }
    return result;
  }
}
