package com.microservice.camelmicroservice.jms;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.underscore.lodash.U;
import com.microservice.camelmicroservice.model.Sale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Receiver {
    Logger log = LoggerFactory.getLogger(Receiver.class);
    @JmsListener(destination="${jms.queue}")
    public void jsonMsg(String msg){

        ObjectMapper mapper = new ObjectMapper();
        //Conversion de JSON a XML
        try {
            log.info("venta recibida: "+ msg);
            String xml = U.jsonToXml(msg);  
            Sale sale = mapper.readValue(msg, Sale.class);
            System.out.println("Sales ="+sale.toString());

        } catch (Exception e) {

            System.out.println(e.getMessage());
        }
    }

}
