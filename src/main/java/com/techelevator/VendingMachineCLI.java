package com.techelevator;

import com.techelevator.view.Menu;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {


	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_DISPLAY_EXIT = "Exit";



	private static final String PURCHASE_MENU_OPTIONS_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTIONS_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION = "Finish Transaction";

	private static final String FEED_MENU_OPTIONS_1 = "$1.00";
	private static final String FEED_MENU_OPTIONS_5 = "$5.00";
	private static final String FEED_MENU_OPTIONS_10 = "$10.00";
	private static final String FEED_MENU_OPTIONS_EXIT ="EXIT";

	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_DISPLAY_EXIT };
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTIONS_FEED_MONEY, PURCHASE_MENU_OPTIONS_SELECT_PRODUCT, PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION};
	private static final String[] FEED_MENU_OPTIONS = {FEED_MENU_OPTIONS_1, FEED_MENU_OPTIONS_5, FEED_MENU_OPTIONS_10, FEED_MENU_OPTIONS_EXIT};


	private static String CSV_FILE_PATH = "vendingmachine.csv";	//LOCATION OF VENDING MACHINE INVENTORY LOAD FILE


	private Coinbox coinbox = new Coinbox();

	private Menu menu;

	private Inventory inventory;

	String filePath = "log.txt";
	private Logger logger = new Logger(filePath);

	//////////////////////////////////////////////////////////////////////////////////////////

	String secretFilePath = "salesreport.txt";
	private Logger secretLogger = new Logger(secretFilePath);

	//////////////////////////////////////////////////////////////////////////////////////////



	public VendingMachineCLI(Menu menu, Inventory inventory) throws FileNotFoundException {
		this.menu = menu;
		this.inventory = inventory;

	}
	/////////////////////////////////////////////////////////////////////////////

	Scanner newScanner = new Scanner(System.in);

	////////////////////////////////////////////////////////////////////////////
	public void run() throws IOException {

		while (true) {

			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);	/// MAIN MENU
			if(choice.equals("secretOption")){
				System.out.println();
				System.out.println("********** Secret Option selected: Printing Sales Report **********");
				LocalDateTime local = LocalDateTime.now();
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yy hh-mm a");
				String formatDateTime = local.format(formatter);
				String fileNameWithDateTime = "salesreport-" + formatDateTime + ".txt";
				File salesReport = new File(fileNameWithDateTime);
				if(!salesReport.exists()){
					salesReport.createNewFile();
				}

				try(PrintWriter salesWriter = new PrintWriter(new FileOutputStream(salesReport, false))){
					salesWriter.println();
					salesWriter.println("********** Sales Report for " + formatDateTime + " ************");
					salesWriter.println();
					for(Map.Entry<String, Integer> entry: inventory.getSales().entrySet()){
						salesWriter.println(entry.getKey() + " | " + entry.getValue());
					}
					salesWriter.println();
					salesWriter.println("***************************************************************");

				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}

			}
			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {	///DISPLAY ITEMS

				inventory.displayItemsFromMap();
				// display vending machine items

			}
			else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {		///PURCHASE MENU
				while (choice.equals(MAIN_MENU_OPTION_PURCHASE)){

					//choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					String choice2 = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);
					if (choice2.equals(PURCHASE_MENU_OPTIONS_FEED_MONEY)) {			///FEED MONEY MENU

						System.out.println("Current Balance: $" + coinbox.getBalance());
						//1) FEED MONEY
						System.out.println();
						System.out.println("Select Money To Add");
						String choice3 = (String) menu.getChoiceFromOptions(FEED_MENU_OPTIONS);
						//PROMPT USER FOR DOLLAR AMOUNT ($1, $5, $10)

						if (choice3.equals(FEED_MENU_OPTIONS_1)) {
							//add $1 to balance
							coinbox.addOneToBalance();
							System.out.println("Current Balance: $" + coinbox.getBalance());

							try{
								logger.log(Logger.FEED_MONEY, coinbox.getBalance().subtract(Coinbox.ONE), coinbox.getBalance());
							}
							catch(Exception e){
								System.out.println(e.getMessage());
							}


						}
						else if (choice3.equals(FEED_MENU_OPTIONS_5)) {
							//add $5 to balance
							coinbox.addFiveToBalance();
							System.out.println("Current Balance: $" + coinbox.getBalance());
							try{
								logger.log(Logger.FEED_MONEY, coinbox.getBalance().subtract(Coinbox.FIVE), coinbox.getBalance());
							}
							catch(Exception e){
								System.out.println(e.getMessage());
							}
							//LOG

						}
						else if (choice3.equals(FEED_MENU_OPTIONS_10)) {
							//add $10 to balance
							coinbox.addTenToBalance();
							System.out.println("Current Balance: $" + coinbox.getBalance());
							try{
								logger.log(Logger.FEED_MONEY, coinbox.getBalance().subtract(Coinbox.TEN), coinbox.getBalance());
							}
							catch(Exception e){
								System.out.println(e.getMessage());
							}
							//LOG

						}
						else if (choice3.equals(FEED_MENU_OPTIONS_EXIT)) {
							//exit

						}
						//xUPDATE USER BALANCE
						//xSHOW PURCHASE SUB MENU AGAIN
					}
					else if (choice2.equals(PURCHASE_MENU_OPTIONS_SELECT_PRODUCT)) {		///SELECT PRODUCT
						System.out.println();
						inventory.displayItemsFromMap();
						//DISPLAYS ITEMS ONLY ONCE WHEN ENTERING PURCHASE MENU
						System.out.println();
						System.out.println("Balance: $" + coinbox.getBalance());
						while(choice2.equals(PURCHASE_MENU_OPTIONS_SELECT_PRODUCT)){

							//2) SELECT PRODUCT
							//conditional to make sure quantity can't get below zero
							//IF SOLD OUT (QUANTITY = 0) , ALERT USER THAT IT IS SOLD OUT
							//PROMPT USER TO SELECT A SLOT

							System.out.println();
							System.out.print("Select slot (A1 - D4): ");
							String userSlotSelected = newScanner.nextLine().toUpperCase();
							// force toUpper on userSelectedItem, to help with invalid error

							Item userSelectedItem = inventory.getInventoryMap().get(userSlotSelected);

							//DISPENSE BY PRINTING TO SCREEN: ITEM NAME, PRICE, MONEY REMAINING, VEND MESSAGE
							/////////////////////////////////////////////////////////////////////////////////

							//Outside if block checks valid slot entry
							if(inventory.getInventoryMap().containsKey(userSlotSelected)){

								//IF VALID SELECTION, DISPENSE TO CUSTOMER. IF NOT, RETURN TO PREVIOUS MENU
								//xVALIDATE ENOUGH FUNDS
								///////////////////////////////////////////////////////////////////////////////
								BigDecimal priceBD = new BigDecimal(userSelectedItem.getPrice());

								if(coinbox.getBalance().compareTo(priceBD) <= -1){
									BigDecimal amountNeeded = new BigDecimal("0.00");
									amountNeeded = priceBD.subtract(coinbox.getBalance());
									System.out.println();
									System.out.println("Current Balance of " + coinbox.getBalance() + " is not enough" );
									System.out.println("Add " + amountNeeded + " More To Continue Purchase");
									System.out.println("------ Returning To Previous Menu ------");
									break;
								}
								//////////////////////////////////////////////////////////////////////////////

								//Inside if block checks enough item quantity
								if(userSelectedItem.getQuantity() > 0){
									System.out.println("Dispensing:");
									System.out.println("Item: " + userSelectedItem.getName());
									System.out.println("Cost: $" + userSelectedItem.getPrice());
									System.out.println("Balance: $" + coinbox.doTransaction(userSelectedItem.getPrice()));
									userSelectedItem.setQuantity(userSelectedItem.getQuantity() -1);
									System.out.println(userSelectedItem.vendMessage());

									///////////////////////////////////////////////////////////////////////////////////////////
									//SALES MAP
									inventory.addToSalesCountMap(userSelectedItem.getName());


									//LOG
									/////////////////////////////////////////////////////////////////////////////////////////////////////
									String logDispense = userSelectedItem.getName() + " " + userSlotSelected;
									BigDecimal beforeTransaction = coinbox.getBalance().add(new BigDecimal(userSelectedItem.getPrice()));
									try{
										logger.log(logDispense, beforeTransaction, coinbox.getBalance());
									}
									catch(Exception e){
										System.out.println(e.getMessage());
									}
									/////////////////////////////////////////////////////////////////////////////////////////////////////
								}
								else{
									System.out.println();
									System.out.println("ITEM SOLD OUT. SELECT A DIFFERENT ITEM.");
									//previous menu if SOLD OUT

									break;
								}

								break;	//previous menu if VALID entry
							}
							else{
								System.out.println();
								System.out.println("*** Invalid Entry ***");
								System.out.println();
							}
							/////////////////////////////////////////////////////////////////////////////////

						}

					}
					else if (choice2.equals(PURCHASE_MENU_OPTIONS_FINISH_TRANSACTION)) {		///FINISH TRANSACTION
						//LOG
						//////////////////////////////////////////////////////////////////////////////////////////////////
						try{
							BigDecimal formatZero = new BigDecimal("0.00");
							logger.log(Logger.CHANGE_GIVEN, coinbox.getBalance(), formatZero);
						}
						catch (Exception e){
							System.out.println(e.getMessage());
						}

						//////////////////////////////////////////////////////////////////////////////////////////////////
						//
						//UPDATE USER BALANCE TO ZERO
						coinbox.makeChange();


						//GO BACK TO MAIN MENU
						break;
					}

				}

			}
			else if (choice.equals(MAIN_MENU_OPTION_DISPLAY_EXIT)) {
				System.out.println();
				System.out.println("Thank you for using the VendoMatic 800. Goodbye");
				//RESET LOG
				logger.customLog(" End of Transaction ");
				System.exit(1);
			}
		}
	}


	public static void main(String[] args) throws FileNotFoundException {
		Menu menu = new Menu(System.in, System.out);
		Inventory inventory = new Inventory();
		File inputFile = new File(VendingMachineCLI.CSV_FILE_PATH);
		Scanner loadFile = new Scanner(inputFile);
		Logger secretLogger = new Logger("salesreport.txt");

		VendingMachineCLI cli = new VendingMachineCLI(menu, inventory);

		inventory.splitCSV(loadFile);		//LOAD THE VENDING MACHINE INITIALLY

		try {
			cli.run();
		} catch (IOException e) {
			e.printStackTrace();
		}
		loadFile.close();
	}
}
