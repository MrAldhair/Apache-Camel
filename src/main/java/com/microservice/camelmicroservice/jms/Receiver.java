package com.microservice.camelmicroservice.jms;

import com.github.underscore.lodash.U;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class Receiver {
    Logger log = LoggerFactory.getLogger(Receiver.class);
    @JmsListener(destination="${jms.queue}")
    public void onReceive(String msg){
        
        
        //Conversion de JSON a XML
        try {
            log.info("venta recibida: "+ msg);
            String xml = U.jsonToXml(msg);  
            System.out.println(xml);

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }
        
    }
}
