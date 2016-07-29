package co.uk.stock.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
//import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import co.uk.stock.constant.StockType;
import co.uk.stock.constant.TradeIndicator;
import co.uk.stock.entity.Stock;
import co.uk.stock.entity.StockRecord;
import co.uk.stock.service.IStockService;

/**
 * 
 * Provides implementation for all Stock calculations.
 * 
 * @author Nissi Tafie.
 *
 *
 */
public class StockServiceImpl implements IStockService {

	/**
	 * Computes PE Dividend Yield for per market price input.
	 * 
	 * @param Stock
	 *            and Market Price.
	 * 
	 * @return Stock.
	 */
	@Override
	public Integer computeDividendYield(Stock stock, BigDecimal marketPrice) {
		Integer dividendYield = 0;
		if (stock.getType().equals(StockType.Type.Common)) {
			Integer lastDiv = stock.getLastDividend();
			BigDecimal lastDividend = new BigDecimal(String.valueOf(lastDiv));
			dividendYield = ((lastDividend.divide(marketPrice, 2, RoundingMode.CEILING)).remainder(BigDecimal.ONE)
					.setScale(2, BigDecimal.ROUND_CEILING).movePointRight(2)).intValueExact();

		}

		if (stock.getType().equals(StockType.Type.Preferred)) {
			Integer fixedDividend = stock.getLastDividend();
			BigDecimal fixedDividendeBD = new BigDecimal(String.valueOf(fixedDividend));

			Integer parValue = stock.getParValue();
			BigDecimal parValueBD = new BigDecimal(String.valueOf(parValue));

			dividendYield = ((fixedDividendeBD.multiply(parValueBD)).divide(marketPrice, 2, RoundingMode.CEILING)
					.remainder(BigDecimal.ONE).setScale(2, BigDecimal.ROUND_CEILING).movePointRight(2)).intValueExact();
		}
		return dividendYield;
	}

	/**
	 * Computes PE Ratio.
	 * 
	 * @param Stock
	 *            and Market Price.
	 * 
	 * @return Stock.
	 */
	@Override
	public Integer computePeRatio(Stock stock, BigDecimal marketPrice) {
		BigDecimal dividendYieldBD = BigDecimal.ONE;
		if (null != stock.getDividendYield()) {
			dividendYieldBD = new BigDecimal(String.valueOf(stock.getDividendYield()));
		}
		Integer pERatio = (marketPrice.divide(dividendYieldBD, 2, RoundingMode.CEILING)).remainder(BigDecimal.ONE)
				.setScale(2, BigDecimal.ROUND_CEILING).movePointRight(2).intValueExact();
		return pERatio;
	}

	/**
	 * Records Stock trade for the last 15 Minutes and adds it to the list of
	 * Stock Record.
	 * 
	 * @param Stock,
	 *            Trade Price, Trade Date, Quality of shares and Trade
	 *            Indicator.
	 * 
	 * @return Stock.
	 */
	@Override
	public Stock recordStockTrade(Stock stock, BigDecimal tradePrice, LocalDateTime tradeDate, Integer quatityOfShares,
			TradeIndicator indicator) {
		StockRecord stockRecord = new StockRecord(tradeDate, quatityOfShares, indicator, tradePrice);
		stock.getRecordList().add(stockRecord);
		return stock;

	}

	/**
	 * Computes Volume Weighted Stock Price.
	 * 
	 * @param Stock.
	 * 
	 * @return Volume Weighted Stock Price.
	 */
	@Override
	public BigDecimal computeVolumeWeightedStockPrice(Stock stock) {
		List<StockRecord> recordList = stock.getRecordList();
		BigDecimal sumOfTradePrice = BigDecimal.ZERO;
		Integer sumOfSharesQuantity = 0;
		BigDecimal sumOfSharesQuantityBD = BigDecimal.ZERO;

		for (StockRecord sR : recordList) {
			BigDecimal sharesQuantityBD = new BigDecimal(String.valueOf(sR.getSharesQuantity()));
			sumOfTradePrice.add(sR.getTradePrice().multiply(sharesQuantityBD));
			sumOfSharesQuantity += sR.getSharesQuantity();
		}
		if (sumOfSharesQuantity == 0) {
			sumOfSharesQuantityBD = BigDecimal.ONE;
		}
		if (sumOfTradePrice == BigDecimal.ZERO) {
			sumOfTradePrice = BigDecimal.ONE;
		}
		return sumOfTradePrice.divide(sumOfSharesQuantityBD);
	}

}
