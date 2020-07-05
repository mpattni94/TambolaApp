package com.tambola.app.utils..validation;

import com.tambola.app.beans.Game;
import com.tambola.app.utils.redis.RepositoryUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.NoSuchElementFoundException;

@RunWith(PowerMockRunner.class)
public class TicketIdValidationUtilsTest{
  
  @InjectMocks
  TicketIdValidationUtils ticketIdValidationUtils;
  
  @Mock
  RepositoryUtils repositoryUtils;

  @Test
  public void testTicketIdExists(){
    Mockito.when(repositoryUtils.retrieveTicketObjectFromRedis(Matchers.anyString())).thenReturn(new Ticket());
    
    Assert.assertTrue(ticketIdValidationUtils.ticketIdExists("12345678"));
  }
  
  @Test
  public void testWhenTicketIdDoesNotExist(){
    Mockito.when(repositoryUtils.retrieveTicketObjectFromRedis(Matchers.anyString())).thenThrow(NoSuchElementFoundException.class);
    
    Assert.assertFalse(gameIdValidationUtils.gameIdExists("12345678"));
  }
  
    @Test
    public void testWhenTicketIdIsValidAndTicketIdExists(){
      String gameId = "12345678";
      Mockito.when(repositoryUtils.retrieveTicketObjectFromRedis(Matchers.anyString())).thenReturn(new Ticket());
    
      Assert.assertTrue(ticketIdValidationUtils.isTicketValid("12345678"));
    }
    
    @Test
    public void testWhenTicketIdIsInvalidAndTicketIdExists(){
      String gameId = "1234567";
      Mockito.when(repositoryUtils.retrieveTicketObjectFromRedis(Matchers.anyString())).thenReturn(new Ticket());
    
      Assert.assertFalse(TicketIdValidationUtils.isTicketValid("12345678"));
    }
    
    @Test
    public void testWhenTicketIdIsValidAndTicketIdDoesNotExist(){
      String gameId = "12345678";
      Mockito.when(repositoryUtils.retrieveTicketObjectFromRedis(Matchers.anyString())).thenThrow(NoSuchElementFoundException.class);
    
      Assert.assertFalse(TicketIdValidationUtils.isTicketValid("12345678"));
    }
    
    @Test
    public void testWhenTicketIdIsInvalidAndTicketIdDoesNotExist(){
      String gameId = "1234567";
      Mockito.when(repositoryUtils.retrieveTicketObjectFromRedis(Matchers.anyString())).thenThrow(NoSuchElementFoundException.class);
    
      Assert.assertFalse(TicketIdValidationUtils.isTicketValid("12345678"));
    }
}
