package com.cryptoStreamLoader.service;

import com.cryptoStreamLoader.model.HistoricDataModelWrapper;

public interface TickerDataQueryService {

    public static final String GDAX_BTC_TICKER_URL = "https://www.coinbase.com/api/v2/prices/BTC-USD/historic?period=all";

    public HistoricDataModelWrapper getTickerDataFromGDAX();
}
