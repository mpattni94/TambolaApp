package com.tambola.app.utils.validation;

import com.tambola.app.beans.Ticket;
import com.tambola.app.constants.TicketConstants;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
public class TicketIdValidationUtils{
  @Autowired
  RepositoryUtils repositoryUtils;
  
  public boolean isTicketValid(final String ticketId){
    return (isLengthValid(ticketId) && ticketIdExists(ticketId));
  }
  
  public boolean isLengthValid(String ticketId){
    boolean result = false;
    if(ticketId.length() == TicketConstants.TICKET_ID_LENGTH){
      result = true;
    }
    return result;
  }
  
  public boolean ticketIdExists(String ticketId){
      boolean result = true;
      try{
        Ticket ticket = repositoryUtils.retrieveTicketObjectFromRedis(ticketId);
      }catch(NoSuchElementException e){
        result = false;
      }
      return result;
  }
}
