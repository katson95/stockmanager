package co.uk.stock.manager;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

import co.uk.stock.entity.Stock;

/**
 * Navigator for displaying stocks and processing user input.
 * 
 * @author Nissi Tafie.
 *
 */
public class ViewManager {

	private static final Scanner SCANNER = new Scanner(System.in);

	private static BigDecimal marketPrice = BigDecimal.ONE;

	private static String errorMessage;

	private static String stockSymbol = "";

	private static String inputPrice;

	/**
	 * Display all stocks and processes user input.
	 * 
	 * @param stocks
	 */
	public static void displayCurrentStocksAndAcceptUserInpute(List<Stock> stocks) {
		stocks.forEach(s -> {
			System.out.println(s);
		});

		collectInputAndValidate();

		if (null != errorMessage) {
			System.out.println(errorMessage);

		} else {
			TradeManager.performTradeOperations(stockSymbol, marketPrice);
		}
	}

	public static void collectInputAndValidate() {
		/**
		 * Accept user Input to edit stock
		 */

		System.out.println("Please enter Symbol of Stock to process");
		stockSymbol = SCANNER.nextLine();

		if (TradeManager.isStockCodeValid(stockSymbol)) {

			if (TradeManager.doesStockExit(stockSymbol)) {

				System.out.println("Please enter market price value.");
				inputPrice = SCANNER.nextLine();

				if (TradeManager.isMarketPriceValid(inputPrice)) {
					marketPrice = new BigDecimal(inputPrice);

				} else {
					errorMessage = "Market price invalid";
				}
			} else {
				errorMessage = "Stock does not exist.";
			}
		} else {
			errorMessage = "Please enter a valid Stock code.";
		}

	}

}
