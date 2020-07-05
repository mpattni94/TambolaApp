package com.tambola.app.beans;

import java.io.Serializable;

public class Player implements Serializable{
  
  private String userName;
  private Ticket ticket;
  
  public String getUserName(){
    return userName;
  }
  
  public void setUserName(String userName){
    this.userName = userName;
  }
  
  public Ticket getTicket(){
    return ticket;
  }
  
  public void setTicket(Ticket ticket){
    this.ticket = ticket
  }
}
