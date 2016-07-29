package co.uk.stock.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import co.uk.stock.constant.StockType.Type;

/**
 * Entity for managing Stocks
 * 
 * @author Nissi Tafie
 *
 */
public class Stock {

	private Long Id;

	private String symbol;

	private Type type;

	private Integer lastDividend;

	private Integer fixedDividend;

	private Integer dividendYield;

	private Integer parValue;

	private BigDecimal stockPrice;

	private Integer epRatio;

	private BigDecimal volumeWeightedStockPrice;

	private List<StockRecord> recordList = new ArrayList<>();

	public Stock() {
		setId(new Random().nextLong());
	}

	public Stock(String symbol, Type type, Integer lastDividend, Integer fixedDividend, Integer dividendYield,
			Integer parValue, BigDecimal stockPrice, Integer epRatio, BigDecimal volumeWeightedStockPrice,
			List<StockRecord> recordList) {
		super();
		this.symbol = symbol;
		this.type = type;
		this.lastDividend = lastDividend;
		this.fixedDividend = fixedDividend;
		this.dividendYield = dividendYield;
		this.parValue = parValue;
		this.stockPrice = stockPrice;
		this.epRatio = epRatio;
		this.volumeWeightedStockPrice = volumeWeightedStockPrice;
		this.recordList = recordList;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public Integer getLastDividend() {
		return lastDividend;
	}

	public void setLastDividend(Integer lastDividend) {
		this.lastDividend = lastDividend;
	}

	public Integer getFixedDividend() {
		return fixedDividend;
	}

	public String getFixedDividendString() {
		if (fixedDividend != null) {
			return new Integer(fixedDividend).toString() + "%";
		} else {
			return "";
		}

	}
	
	public String getYieldDividendString() {
		if (dividendYield != null) {
			return new Integer(dividendYield).toString() + "%";
		} else {
			return "";
		}

	}
	
	public String getParValueString() {
		if (parValue != null) {
			return new Integer(parValue).toString();
		} else {
			return "";
		}

	}
	

	public void setFixedDividend(Integer fixedDividend) {
		this.fixedDividend = fixedDividend;
	}

	public Integer getDividendYield() {
		return dividendYield;
	}

	public void setDividendYield(Integer dividendYield) {
		this.dividendYield = dividendYield;
	}

	public Integer getParValue() {
		return parValue;
	}

	public void setParValue(Integer parValue) {
		this.parValue = parValue;
	}

	public BigDecimal getStockPrice() {
		return stockPrice;
	}

	public void setStockPrice(BigDecimal stockPrice) {
		this.stockPrice = stockPrice;
	}

	public Integer getEpRatio() {
		return epRatio;
	}

	public void setEpRatio(Integer epRatio) {
		this.epRatio = epRatio;
	}

	public BigDecimal getVolumeWeightedStockPrice() {
		return volumeWeightedStockPrice;
	}

	public void setVolumeWeightedStockPrice(BigDecimal volumeWeightedStockPrice) {
		this.volumeWeightedStockPrice = volumeWeightedStockPrice;
	}

	public List<StockRecord> getRecordList() {
		return recordList;
	}

	public void setRecordList(List<StockRecord> recordList) {
		this.recordList = recordList;
	}

	@Override
	public String toString() {
		return "  " + symbol + " " + type +" " + lastDividend + " " + getYieldDividendString() + " "+ getFixedDividendString() + " "+ getParValueString();
	}

}
