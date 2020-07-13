package com.tambola.app.utils.redis;

import com.tambola.app.beans.Game;
import com.tambola.app.beans.Ticket;
import com.tambola.app.repository.GameRepository;
import com.tambola.app.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepositoryUtils{
  @Autowired
  GameRepository gameRepository;
  
  @Autowired
  TicketRepository ticketRepository;
  
  public Game retrieveGameFromRedis(String gameId){
    Game game;
    game = gameRepository.findById(gameId).get();
    return game;
  }
  
  public void saveToRedis(Game game){
    gameRepository.save(game);
  }
  
  public Ticket retrieveTicketObjectFromRedis(String ticketId){
     Ticket ticket;
     ticket = ticketRepository.findById(ticketId).get();
     return ticket;
  }
  
  public void saveToRedis(Ticket ticket){
    ticketRepository.save(ticket);
  }
}
