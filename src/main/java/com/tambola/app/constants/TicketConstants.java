package com.tambola.app.constants;

public interface TicketConstants {
    int TICKET_ID_LENGTH = 8;
    int MAX_NUMBERS_ON_TICKET = 15;
    int MAX_NUMBERS_PER_ROW = 5;
    int TICKET_ROWS = 3;
    int TICKET_COLUMNS = 9;
    int HIGHEST_VALUE_ALLOWED = 90;

    int[] COLUMN_RANGE = {0, 10, 20, 30, 40, 50, 60, 70, 80};
    int[] ROW_BOUND = {3, 6, 9};
}
