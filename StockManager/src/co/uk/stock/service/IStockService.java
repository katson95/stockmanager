package co.uk.stock.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import co.uk.stock.constant.TradeIndicator;
import co.uk.stock.entity.Stock;

public interface IStockService {

	/**
	 * Computes PE Dividend Yield for per market price input.
	 * 
	 * @param Stock
	 *            and Market Price.
	 * 
	 * @return Stock.
	 */
	public Integer computeDividendYield(Stock stock, BigDecimal marketPrice);

	/**
	 * Computes PE Ratio.
	 * 
	 * @param Stock
	 *            and Market Price.
	 * 
	 * @return Stock.
	 */
	public Integer computePeRatio(Stock stock, BigDecimal marketPrice);

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
	public Stock recordStockTrade(Stock stock, BigDecimal tradePrice, LocalDateTime tradeDate, Integer quatityOfShares,
			TradeIndicator indicator);

	/**
	 * Computes Volume Weighted Stock Price.
	 * 
	 * @param Stock.
	 * 
	 * @return Volume Weighted Stock Price.
	 */
	public BigDecimal computeVolumeWeightedStockPrice(Stock stock);

}
