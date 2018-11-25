package com.cryptoStreamLoader.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.*;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(indexName = "marketdatasnapshot", type = "marketdata")
public class MarketData {

    // Elasticsearch object internal id. Look at field "_id"
    @Id
    @Field(type= FieldType.Text)
    private String id;

    @Field(type= FieldType.Text)
    private String type;

    @Field(type= FieldType.Text)
    private String side;

    @Field(type= FieldType.Text)
    private String order_id;

    @Field(type= FieldType.Text)
    private String reason;

    @Field(type= FieldType.Text)
    private String product_id;

    @Field(type= FieldType.Float)
    private BigDecimal price;

    @Field(type= FieldType.Float)
    private BigDecimal remaining_size;

    @Field(type= FieldType.Long)
    private Long sequence;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd'T'HH:mm:ssZ")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @Field(type = FieldType.Date,  store = true,
            format = DateFormat.custom, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private LocalDateTime time;
}
