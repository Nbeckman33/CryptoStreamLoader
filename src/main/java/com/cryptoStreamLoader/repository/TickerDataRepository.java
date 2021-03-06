package com.cryptoStreamLoader.repository;

import com.cryptoStreamLoader.entity.TickerData;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TickerDataRepository extends ElasticsearchCrudRepository<TickerData, String>, TickerDataRepositoryCustom {


}
