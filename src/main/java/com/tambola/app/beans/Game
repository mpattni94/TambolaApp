package com.tambola.app.beans;

import org.springframework.data.annotations.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RedisHash("Game")
public class Game implements Serializable{
  
  @Id
  private String gameId;
  
  private List<Player> players;
  private Set<String> numbersDoneSoFar;
  
  public Set<String> getNumbersDoneSoFar(){
    if(numbersDoneSoFar == null){
      numbersDoneSoFar = new HashSet<>();
    }
    return numbersDoneSoFar
  }
  
  public void setNumbersDoneSoFar(Set<String> numbersDoneSoFar){
    this.numbersDoneSoFar = numbersDoneSoFar;
  }
  
  public String getGameId(){
    return gameId;
  }

  public void setGameId(String gameId){
    this.gameId = gameId;
  }
  
  public List<Player> getPlayers(){
    return players;
  }
  
  public void setPlayers(List<Player> players){
    this.players = players;
  }
  
  public void addPlayer(Player player){
    if(players == null){
      players = new ArrayList<>();
    }
    players.add(player)
  }

}
