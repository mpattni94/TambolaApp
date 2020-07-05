package com.tambola.app;

import com.tambola.app.beans.Ticket;
import com.tambola.app.constants.ResultStrings;
import com.tambola.app.utils.convertor.json.SerializerUtil;
import com.tambola.app.utils.game.GameUtils;
import com.tambola.app.utils.generator.id.IdGenerator;
import com.tambola.app.utils.generator.ticket.TicketGenerator;
import com.tambola.app.utils.generator.ticket.TicketNumberGenerator;
import com.tambola.app.utils.redis.RepositoryUtils;
import com.tambola.app.utils.validation.GameIdValidationUtils;
import com.tambola.app.utils.validation.TicketIdValidationUtils;
import com.tambola.app.utils.validation.UserNameValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootAplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.Exception;

@SpringBootApplication
@Controller
public class TambolaApplication{
  @Autowired
  IdGenerator idGenerator;
  
  @Autowired
  TicketGenerator ticketGenerator;
  
  @Autowired
  RepositoryUtils repositoryUtils;
  
  @Autowired
  GameIdValidationUtils gameIdValidationUtils;
  
  @Autowired
  UserNameValidationUtils userNameValidationUtils;
  
  @Autowired
  TicketValidationUtils ticketValidationUtils;
  
  @Autowired
  TicketNumberGenerator ticketNumberGenerator;
  
  @Autowired
  GameUtils gameUtils;
  
  @RequestMapping(value="/api/game/create", method=RequestMethod.GET)
  @ResponseBody
  public String createGame(){
    String gameId = idGenerator.generateGameId();
    return gameId;
  }
  
  
  @RequestMapping(value="/api/game/{gameId}/ticket/{userName}/generate", method=RequestMethod.GET)
  @ResponseBody
  public String generateTicketId(@PathVariable String gameId, @PathVariable String userName){
    String result = ResultStrings.INVALID_USER_ID;
    
    String ticketId = idGenerator.generateTicketId();
    if(gameIdValidationUtils.isGameIdValid(gameId)){
      if(!userNameValidationUtils.ifUserAlreadyExistsForGame(gameId, userName, ticketId){
        result = ticketId;
      }
    }
    return result;
  }
  
  
  @RequestMapping(value="/ticket/{ticketId}", method=RequestMethod.GET)
  @ResponseBody
  public ModelAndView generateTicket(@PathVariable String ticketId){
    ModelAndView model = new ModelAndView("error");
    if(ticketIdValidationUtils.isTicketIdValid(ticketId){
      Ticket ticket1 = repositoryUtils.retrieveTicketObjectFromRedis(ticketId);
      String ticket = ticket1.getTicketArray();
      if(null == ticket || ticket.length() == 0){
        try{
          ticket = SerializerUtil.getJsonString(ticketGenerator.generateNewTicket());
          ticket1.setTicketArray(ticket);
          model = new ModelAndView("ticket");
          model.addObject("tambolaTicket", ticket);
        }catch(IOException e){
          model.addObject("errorMsg", ResultStrings.SERVER_ERROR);
        }
      }
    }else{
      model.addObject("errorMsg", ResultStrings.INVALID_TICKET_ID);
    }
    return model;
  }
  
  
  @RequestMapping(value="/api/game/{gameId}/random", method=RequestMethod.GET)
  @ResponseBody
  public String generateRandomNumberForGame(@PathVariable String gameId){
    String result;
    if(gameIdValidationUtils.isGameIdValid(gameId)){
      int number = ticketNumberGenerator.generateRandomNumberForGameAndStore(gameId);
      if(number > 0){
        result = number + "";
      }else{
        number = ResultStrings.ALL_NUMBERS_DRAWN;
       }
    }else{
      result = ResultStrings.INVALID_GAME_ID;
    }
    return result;
  }
  
  
  @RequestMapping(value="/api/game/{gameId}/numbers", method=RequestMethod.GET)
  @ResponseBody
  public String getNumbersGeneratedInGame(@athVariable String gameId){
    String result;
    if(gameIdValidationUtils.isGameIdValid(gameId)){
      try{
        result = gameUtils.getNumbersDrawnSoFar(gameId);
      }catch(IOException e){
        result = ResultStrings.SERVER_ERROR;
      }
    }else{
      result = ResultStrings.INVALID_GAME_ID;
    }
    return result;
  }
  
  
  @RequestMapping(value="/api/game/{gameId}/stats", method=RequestMethod.GET)
  @ResponseBody
  public String getGameStats(@PathVariable String gameId){
    String result;
    if(gameIdValidationUtils.isGameIdValid(gameId)){
      try{
        result = gameUtils.getGameStats(gameId);
      }catch(IOException e){
        result = ResultStrings.SERVER_ERROR;
      }
    }else{
      result = ResultStrings.INVALID_GAME_ID;
    }
    return result;
  }
  
  
  public static void main(String args[]){
    SpringApplication.run(TambolaApplication.class, args);
  }
  
  
}
