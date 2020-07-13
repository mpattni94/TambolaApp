package com.tambola.app.beans;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("Ticket")
public class Ticket implements Serializable{

  @Id
  private String ticketId;
  
  //JSONArray as string
  private String ticket;
  
  public String getTicketId(){
    return ticketId;
  }
  
  public void setTicketId(String ticketId){
    this.ticketId = ticketId;
  }
  
  public String getTicketArray(){
    return ticket;
  }
  
  public void setTicketArray(String ticket){
    this.ticket = ticket;
  }

}
