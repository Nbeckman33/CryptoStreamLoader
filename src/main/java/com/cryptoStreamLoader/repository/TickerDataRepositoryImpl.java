package com.cryptoStreamLoader.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TickerDataRepositoryImpl implements TickerDataRepositoryCustom {

    Logger log = LoggerFactory.getLogger(TickerDataRepositoryImpl.class);

    @Autowired
    public TickerDataRepositoryImpl(ElasticsearchTemplate elasticsearchTemplate) {
        this.elasticsearchTemplate = elasticsearchTemplate;
    }

    private ElasticsearchTemplate elasticsearchTemplate;




}