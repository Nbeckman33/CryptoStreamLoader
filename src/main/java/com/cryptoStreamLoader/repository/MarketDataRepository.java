package com.cryptoStreamLoader.repository;

import com.cryptoStreamLoader.entity.MarketData;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MarketDataRepository extends ElasticsearchCrudRepository<MarketData, String>, MarketDataRepositoryCustom {


}
