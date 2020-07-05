package com.tambola.app.utils.generator.ticket;

import com.tambola.app.constants.TicketConstants;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
public class TicketGeneratorTest{
  
  @Test
  public void testGenerateNewTicket(){
    TicketGenerator ticketGenerator = new TicketGenerator();
    
    int[][] ticket = ticketGenerator.generateNewTicket();
    
    int numberCounter = 0;
    
    Assert.assertFalse(null == ticket);
    
    for(int i=0; i<TicketConstants.TICKET_ROWS; i++){
      int rowCount = 0;
      for(int j=0; j<TicketConstants.TICKET_COLUMNS; j++){
        if(ticket[i][j] > 0){
          rowCount ++;
          numberCounter ++;
        }
      }
      Assert.assertEquals(TicketConstants.MAX_NUMBERS_PER_ROW, rowCount);
    }
    Assert.assertEquals(TicketConstants.MAX_NUMBERS_ON_TICKET, numberCounter);
  }
}
