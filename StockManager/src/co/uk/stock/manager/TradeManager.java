package co.uk.stock.manager;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import co.uk.stock.constant.StockType;
import co.uk.stock.entity.Stock;
import co.uk.stock.service.IStockService;
import co.uk.stock.service.impl.StockServiceImpl;

/**
 * Trade Hub for managing Market activities.
 * 
 * @author Nissi Tafie.
 *
 */
public class TradeManager {

	/**
	 * Holds all generated Test Stock Data.
	 */
	static Map<String, Stock> stockMap = new HashMap<String, Stock>();

	/**
	 * Generates Test Stock Data
	 * 
	 * @return List of market stocks.
	 * 
	 * @author Nissi Tafie.
	 *
	 */
	public static List<Stock> generateTestStocks() {

		Stock s1 = new Stock();
		s1.setSymbol("TEA");
		s1.setType(StockType.Type.Common);
		s1.setLastDividend(0);
		s1.setParValue(100);

		stockMap.put(s1.getSymbol(), s1);

		Stock s2 = new Stock();
		s2.setSymbol("POP");
		s2.setType(StockType.Type.Common);
		s2.setLastDividend(8);
		s2.setParValue(100);
		s2.setFixedDividend(2);

		stockMap.put(s2.getSymbol(), s2);

		Stock s3 = new Stock();
		s3.setSymbol("ALE");
		s3.setType(StockType.Type.Common);
		s3.setLastDividend(23);
		s3.setParValue(60);

		stockMap.put(s3.getSymbol(), s3);

		Stock s4 = new Stock();
		s4.setSymbol("GIN");
		s4.setType(StockType.Type.Preferred);
		s4.setLastDividend(8);
		s4.setParValue(100);

		stockMap.put(s4.getSymbol(), s4);

		Stock s5 = new Stock();
		s5.setSymbol("JOE");
		s5.setType(StockType.Type.Common);
		s5.setLastDividend(13);
		s5.setParValue(250);

		stockMap.put(s5.getSymbol(), s5);

		return new ArrayList<>(stockMap.values());

	}

	/**
	 * Computes GBCE Index for all stocks.
	 * 
	 * @param stockList.
	 * 
	 * @return GBCE Share Index.
	 */
	public static Double calculateGBCeShareIndex(List<Stock> stockList) {
		BigDecimal totalStockPrice = BigDecimal.ZERO;
		Double geometricMean;
		for (Stock s : stockList) {
			if (null != s.getStockPrice()) {
				totalStockPrice = totalStockPrice.add(s.getStockPrice());
			}
		}
		geometricMean = Math.pow(totalStockPrice.doubleValue(), (1 / stockList.size()));
		return geometricMean;
	}

	/**
	 * Performs all trade computations.
	 * 
	 * @param Stock
	 *            Symbol and Market Price.
	 */
	public static void performTradeOperations(String symbol, BigDecimal marketPrice) {

		IStockService stockService = new StockServiceImpl();

		Stock selectedStock = stockMap.get(symbol);

		Integer dividendYield = stockService.computeDividendYield(selectedStock, marketPrice);
		Integer pERatio = stockService.computePeRatio(selectedStock, marketPrice);
		BigDecimal volumeWeightedStockPrice = stockService.computeVolumeWeightedStockPrice(selectedStock);
		Double gBCeShareIndex = calculateGBCeShareIndex(new ArrayList<>(stockMap.values()));

		selectedStock.setDividendYield(dividendYield);
		selectedStock.setEpRatio(pERatio);
		selectedStock.setVolumeWeightedStockPrice(volumeWeightedStockPrice);
		stockMap.put(selectedStock.getSymbol(), selectedStock);

		System.out.println(selectedStock.toString());
		System.out.println("GBCE Share Index: " + gBCeShareIndex);
	}

	/**
	 * Checks if market price input is of the right type (BigDecimal) and
	 * returns true thats the case otherwise false is returned.
	 * 
	 * @param Market
	 *            price as Input.
	 * @return Validation results.
	 */
	public static boolean isMarketPriceValid(String value) {
		return value.matches("^[-+]?\\d+(\\.\\d+)?$");
	}

	/**
	 * Checks if Stock code input is of the right type (String) and returns true
	 * if thats the case otherwise false is returned.
	 * 
	 * @param Stock
	 *            code as Input.
	 * @return Validation results.
	 */
	public static boolean isStockCodeValid(Object o) {
		if (o instanceof String) {
			return true;
		}
		return false;

	}

	/**
	 * Checks if Stock with code and returns true if thats the case otherwise
	 * false is returned.
	 * 
	 * @param Stock
	 *            code as Input.
	 * @return Validation results.
	 */
	public static boolean doesStockExit(String stockCode) {
		Stock stock = stockMap.get(stockCode);
		if (null != stock) {
			return true;
		}
		return false;

	}

}
