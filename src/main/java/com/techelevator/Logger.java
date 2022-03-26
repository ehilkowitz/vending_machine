package com.techelevator;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Logger {

    private File log;

    public static final String FEED_MONEY = "FEED MONEY";
    public static final String CHANGE_GIVEN = "CHANGE GIVEN";
    //
    Inventory secretInventory = new Inventory();
    Map<String, Integer> secretSales = secretInventory.getSales();

    public Logger(String logFile){
        this.log = new File(logFile);

    }
    //LOGGER for user actions
    public void log(String action, BigDecimal balanceBeforeTransaction, BigDecimal balanceAfterTransaction) throws Exception{

        try( PrintWriter logWriter = new PrintWriter(new FileOutputStream(log, true))){
            LocalDateTime local = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm a");
            String formatDateTime = local.format(formatter);
            logWriter.println( formatDateTime + " " + action + " " + "$" + balanceBeforeTransaction + " " + "$" + balanceAfterTransaction);
            logWriter.flush();
        }
        catch (FileNotFoundException fnf){
            Exception x = new Exception("Log File Error");
            throw x;
        }
    }
    //Sales Report Logger - plus 4 on user main menu to have it printed
    public void customLog(String message){

        try(PrintWriter logWriter = new PrintWriter(new FileOutputStream(log, true))){
            logWriter.println();
            logWriter.println("*************  " + message + "  ******************");
            logWriter.println();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
