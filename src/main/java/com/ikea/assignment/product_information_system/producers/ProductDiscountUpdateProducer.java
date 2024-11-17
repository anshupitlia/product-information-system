package com.ikea.assignment.product_information_system.producers;

import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.model.ProductDiscountUpdateEventData;
import com.ikea.assignment.product_information_system.model.ProductPriceUpdateEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ProductDiscountUpdateProducer {
    @Autowired
    private KafkaTemplate<String, ProductDiscountUpdateEventData> kafkaTemplate;

    @Value("${kafka.price.update.topic.suffix}")
    private String priceUpdateTopicSuffix;

    public void send(Product oldProduct, Product newProduct) {
        String topicName = newProduct.getCountry().toString() + "-" + priceUpdateTopicSuffix;
        String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ProductDiscountUpdateEventData productDiscountUpdateEventData = new ProductDiscountUpdateEventData(newProduct.getCountry().toString(), oldProduct.getDiscountPercentage().toString(), newProduct.getDiscountPercentage().toString(), newProduct.getSku(), date);
        kafkaTemplate.send(topicName, newProduct.getSku(), productDiscountUpdateEventData);
    }
}
