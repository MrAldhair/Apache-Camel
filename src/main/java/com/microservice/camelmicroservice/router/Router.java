package com.microservice.camelmicroservice.router;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Router extends RouteBuilder {
    static final Logger log= LoggerFactory.getLogger(Router.class);

    @Override
    public void configure() throws Exception {
        //queue
        //transformation
        //db
        from("activemq.artemis:{{jms.queue}}").log("received message -${body}");

    }
}
