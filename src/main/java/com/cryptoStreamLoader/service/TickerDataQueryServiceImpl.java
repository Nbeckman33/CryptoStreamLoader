package com.cryptoStreamLoader.service;

import com.cryptoStreamLoader.model.HistoricDataModelWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TickerDataQueryServiceImpl implements TickerDataQueryService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public HistoricDataModelWrapper getTickerDataFromGDAX() {
        ResponseEntity<HistoricDataModelWrapper> responseEntity = restTemplate.exchange(GDAX_BTC_TICKER_URL, HttpMethod.GET, null, HistoricDataModelWrapper.class);

        return !responseEntity.getStatusCode().isError() && responseEntity.hasBody() ? responseEntity.getBody() : null;
    }
}
