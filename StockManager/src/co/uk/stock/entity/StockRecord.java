package co.uk.stock.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

import co.uk.stock.constant.TradeIndicator;

/**
 * Entity for managing Stock Record
 * 
 * @author Nissi Tafie
 *
 */
public class StockRecord {

	private Long Id;

	private LocalDateTime tradedDate;

	private Integer sharesQuantity;

	private TradeIndicator tradeIndicator;

	private BigDecimal tradePrice;

	public StockRecord() {
		setId(new Random().nextLong());
	}

	public StockRecord(LocalDateTime tradedDate, Integer sharesQuantity, TradeIndicator tradeIndicator,
			BigDecimal tradePrice) {
		super();
		this.tradedDate = tradedDate;
		this.sharesQuantity = sharesQuantity;
		this.tradeIndicator = tradeIndicator;
		this.tradePrice = tradePrice;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public LocalDateTime getTradedDate() {
		return tradedDate;
	}

	public void setTradedDate(LocalDateTime tradedDate) {
		this.tradedDate = tradedDate;
	}

	public Integer getSharesQuantity() {
		return sharesQuantity;
	}

	public void setSharesQuantity(Integer sharesQuantity) {
		this.sharesQuantity = sharesQuantity;
	}

	public TradeIndicator getTradeIndicator() {
		return tradeIndicator;
	}

	public void setTradeIndicator(TradeIndicator tradeIndicator) {
		this.tradeIndicator = tradeIndicator;
	}

	public BigDecimal getTradePrice() {
		return tradePrice;
	}

	public void setTradePrice(BigDecimal tradePrice) {
		this.tradePrice = tradePrice;
	}
}
