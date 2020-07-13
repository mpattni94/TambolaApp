package com.tambola.app.utils.generator.ticket;

import com.tambola.app.constants.TicketConstants;
import com.tambola.app.utils.random.RandomNumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class TicketGenerator{

  @Autowired
  RandomNumberUtils randomNumberUtils;
  
  private Random random = new Random();
  
  private int ticketRows = TicketConstants.TICKET_ROWS;
  private int ticketColumns = TicketConstants.TICKET_COLUMNS;
  private int maxNumbersPerRow = TicketConstants.MAX_NUMBERS_PER_ROW;
  
  public int[][] generateNewTicket(){
  
    int numbersOnTicket = 0;
    int[][] ticketArray = new int[ticketRows][ticketColumns];
    
    for(int i=0; i<ticketRows; i++){
      int rowCount = 0;
      for(int j=0; j<ticketColumns; j++){
        if(!(rowCount == maxNumbersPerRow) &&
          !(numbersOnTicket == TicketConstants.MAX_NUMBERS_ON_TICKET)){
            int numberOfColumnsRemaining = ticketColumns -j;
            if(numberOfColumnsRemaining > (maxNumbersPerRow - rowCount)){
              if(randomlyDecideIfThisCellShouldHaveANumber()){
                ticketArray[i][j] = getNumberForCell(i,j);
                rowCount++;
                numbersOnTicket++;
              }
            }else{
              ticketArray[i][j] = getNumberForCell(i,j);
              rowCount++;
              numbersOnTicket++;
            }
          }else{
            break;
          }
      }
    }
    return ticketArray;
  
  }
  
  private boolean randomlyDecideIfThisCellShouldHaveANumber(){
    boolean result = true;
    
    int decision = random.nextInt(2);
    if(decision == 0){
      result = false;
    }
    return result;
  }
  
  private int getNumberForCell(int i, int j){
    int numberForCell;
    if(i == 0){
      numberForCell = TicketConstants.COLUMN_RANGE[j] + random.nextInt(TicketConstants.ROW_BOUND[i] + 1);
      if(numberForCell % 10 == 0){
        numberForCell += 1;
      }
    }else{
      numberForCell = TicketConstants.COLUMN_RANGE[j] + random.nextInt(TicketConstants.ROW_BOUND[i] - 1) + random.nextInt(TicketConstants.ROW_BOUND[0] + 1);
    }
    return numberForCell;
  }

}
