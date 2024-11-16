package com.ikea.assignment.product_information_system.producers;

import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.model.ProductPriceUpdateEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProductPriceUpdateProducer {
    @Autowired
    private KafkaTemplate<String, ProductPriceUpdateEventData> kafkaTemplate;

    @Value("${kafka.price.update.topic.suffix}")
    private String priceUpdateTopicSuffix;

    public void send(Product oldProduct, Product newProduct) {
        String topicName = newProduct.getCountry().toString() + "-" + priceUpdateTopicSuffix;
        String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ProductPriceUpdateEventData productPriceUpdateEventData = new ProductPriceUpdateEventData(newProduct.getCountry().toString(), oldProduct.getPrice().toString(), newProduct.getPrice().toString(), newProduct.getSku(), date);
        kafkaTemplate.send(topicName, newProduct.getSku(), productPriceUpdateEventData);
    }
}
