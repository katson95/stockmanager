package co.uk.stock;

import java.util.ArrayList;
import java.util.List;

import co.uk.stock.entity.Stock;
import co.uk.stock.manager.ViewManager;
import co.uk.stock.manager.TradeManager;

public class AppStarter {

	public static void main(String[] args) {
		
		List<Stock> activeStockList = new ArrayList<>();
		
		activeStockList = TradeManager.generateTestStocks();
		
		ViewManager.displayCurrentStocksAndAcceptUserInpute(activeStockList);
	}

}
