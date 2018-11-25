package com.cryptoStreamLoader.service;


import com.cryptoStreamLoader.entity.MarketData;
import com.cryptoStreamLoader.repository.MarketDataRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import java.util.function.Function;


/**
 *
 * Service to process File Data:
 *
 * Step-1: parse Json-Data to entity MarketData-Object
 * Step-2: enrich MarketData with auditTime and id fields (generated)
 * Step-3: save to elastic-search engine
 *
 *
 */
@Service
public class MarketDataProcessingService {

    Logger log = LoggerFactory.getLogger(MarketDataProcessingService.class);

    @Autowired
    private MarketDataRepository marketDataRepository;


    @Autowired
    private ObjectMapper elasticObjectMapper;

    /**
     *
     * parse JSON extract as MarketData entity(s)
     *
     * @return MarketData
     */
    public List<MarketData> getFileData(String jsonFilePath) {

        TypeReference<List<MarketData>> typeReference = new TypeReference<List<MarketData>>(){};

        InputStream inputStream = TypeReference.class.getResourceAsStream(jsonFilePath);

        List<MarketData> marketDataEntities = null;

        try {
            marketDataEntities = elasticObjectMapper.readValue(inputStream, typeReference);

            marketDataEntities.parallelStream().map(new Function<MarketData, MarketData>() {
                /**
                 * Applies this function to the given argument.
                 *
                 * @param marketData the function argument
                 * @return the function result
                 */
                @Override
                public MarketData apply(MarketData marketData) {
                    marketData.setId(getNewUUID());
                    return marketData;
                }
            });
        }catch (Exception exc){
            log.error("exception while reading marketData entities",exc);
            throw new RuntimeException(exc);
        }

      return marketDataEntities;

    }



    public String getNewUUID(){
        return UUID.randomUUID().toString();
    }


}
