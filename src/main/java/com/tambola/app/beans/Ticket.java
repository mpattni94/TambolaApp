package com.tambola.app.beans;

import org.springframework.data.annotations.Id;
import org.springframework.dataredis.core.RedisHash;

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
