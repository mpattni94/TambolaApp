package com.tambola.app.utils.redis;

import cm.tambola.app.beans.Game;
import cm.tambola.app.beans.Ticket;
import cm.tambola.app.repository.GameRepository;
import cm.tambola.app.repository.TicketRepository;
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
