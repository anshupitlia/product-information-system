package com.ikea.assignment.product_information_system.producers;

import com.ikea.assignment.product_information_system.model.Product;
import com.ikea.assignment.product_information_system.model.ProductCountryUpdateEventData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ProductCountryUpdateProducer {

    @Autowired
    private KafkaTemplate<String, ProductCountryUpdateEventData> kafkaTemplate;

    @Value("${kafka.country.update.topic.suffix}")
    private String countryUpdateTopicSuffix;

    public void send(Product oldProduct, Product newProduct) {
        String topicName = newProduct.getCountry().toString() + "-" + countryUpdateTopicSuffix;
        String date = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        ProductCountryUpdateEventData productPriceUpdateEventData = new ProductCountryUpdateEventData(newProduct.getPrice().toString(), oldProduct.getCountry().toString(), newProduct.getCountry().toString(), newProduct.getSku(), date);
        kafkaTemplate.send(topicName, newProduct.getSku(), productPriceUpdateEventData);
    }
}
