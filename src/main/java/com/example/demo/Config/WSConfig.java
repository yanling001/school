package com.example.demo.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;
//@Configuration
public class WSConfig {

   // @Bean

    public ServerEndpointExporter serverEndpointExporter(){

        return new ServerEndpointExporter();

    }

}
