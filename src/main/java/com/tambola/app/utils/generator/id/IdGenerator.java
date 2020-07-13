package com.tambola.app.utils.generator.id;

import com.tambola.app.beans.Game;
import com.tambola.app.beans.Ticket;
import com.tambola.app.constants.GameConstants;
import com.tambola.app.utils.random.RandomNumberUtils;
import com.tambola.app.utils.redis.RepositoryUtils;
import com.tambola.app.utils.validation.GameIdValidationUtils;
import com.tambola.app.utils.validation.TicketIdValidationUtils;
import com.tambola.app.constants.TicketConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator{

  @Autowired
  RandomNumberUtils randomNumberUtils;
  
  @Autowired
  GameIdValidationUtils gameIdValidationUtils;
  
  @Autowired
  TicketIdValidationUtils ticketIdValidationUtils;
  
  @Autowired
  RepositoryUtils repositoryUtils;
  
  public String generateGameId(){
      String gameId;
      do{
        gameId = randomNumberUtils.generateRandomString(GameConstants.GAME_ID_LENGTH);
      }while(gameIdValidationUtils.gameIdExists(gameId));
      Game game = new Game();
      game.setGameId(gameId);
      repositoryUtils.saveToRedis(game);
      return gameId;
  }
  
  public String generateTicketId(){
    String ticketId;
      do{
        ticketId = randomNumberUtils.generateRandomString(TicketConstants.TICKET_ID_LENGTH);
      }while(ticketIdValidationUtils.ticketIdExists(ticketId));
      Ticket ticket = new Ticket();
      ticket.setTicketId(ticketId);
      repositoryUtils.saveToRedis(ticket);
      return ticketId;
  }
}
