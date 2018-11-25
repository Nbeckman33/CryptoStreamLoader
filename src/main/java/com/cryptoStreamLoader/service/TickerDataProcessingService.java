package com.cryptoStreamLoader.service;

import com.cryptoStreamLoader.common.DateTimeUtil;
import com.cryptoStreamLoader.entity.TickerData;
import com.cryptoStreamLoader.model.HistoricDataModelWrapper;
import com.cryptoStreamLoader.model.PriceModel;
import com.cryptoStreamLoader.repository.TickerDataRepository;
import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;


/**
 *
 * Service to process File Data:
 *
 * Step-1: parse Json-Data to entity TickerData-Object
 * Step-2: enrich TickerData with auditTime and id fields (generated)
 * Step-3: save to elastic-search engine
 *
 *
 */
@Service
public class TickerDataProcessingService {

    Logger log = LoggerFactory.getLogger(TickerDataProcessingService.class);

    @Autowired
    TickerDataQueryService tickerDataQueryService;


    @Autowired
    private TickerDataRepository tickerDataRepository;

    @PostConstruct
    public void init(){

        HistoricDataModelWrapper response =  tickerDataQueryService.getTickerDataFromGDAX();

        List<TickerData> iterable = generateTickerDataCollection(response);

        if(iterable!=null && iterable.size()>0) {

            tickerDataRepository.deleteAll();

            log.info("cleared historical data before reloading fresh historical-data-extract from GDAX");

            List<TickerData> tickerData_Saved = Lists.newArrayList(tickerDataRepository.saveAll(iterable));

            log.info("loaded historical data of size: {}", tickerData_Saved.size());
        }else{
            log.warn("failed to extract historical-data from GDAX. retaining the existing historical-data (if any)");

            throw new RuntimeException("failed to extract historical-data from GDAX. retaining the existing historical-data (if any)");
        }

    }

    /**
     *
     * @param historicDataModelWrapper
     * @return Collection of TickerData entities
     */
    public List<TickerData> generateTickerDataCollection(HistoricDataModelWrapper historicDataModelWrapper){

        assertNotNull(historicDataModelWrapper);

        String baseCrypto = historicDataModelWrapper.getData().getBase();
        String currency = historicDataModelWrapper.getData().getCurrency();

        return historicDataModelWrapper.getData().getPrices().parallelStream().map(new Function<PriceModel,TickerData >() {
            /**
             * Applies this function to the given argument.
             *
             * @param priceModel the function argument
             * @return the function result
             */
            @Override
            public TickerData apply(PriceModel priceModel) {

                return TickerData.builder().base(baseCrypto).id(UUID.randomUUID().toString())
                                            .auditTimeStamp(DateTimeUtil.getCurrentTimeStamp())
                                            .currency(currency).price(priceModel.getPrice())
                                            .tickerTime(priceModel.getTime())
                                            .build();

            }
        }).collect(Collectors.toList());

    }




}
