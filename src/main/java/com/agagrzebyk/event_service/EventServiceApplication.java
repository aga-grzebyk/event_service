package com.agagrzebyk.event_service;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EventServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventServiceApplication.class, args);
    }

    @Bean
    public ServletWebServerFactory servletContainer(){

        //  umożliwia SSL
        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory(){
            protected void postProcessContext(Context context){
            SecurityConstraint securityConstraint = new SecurityConstraint();
            securityConstraint.setUserConstraint("CONFIDENTIAL");

            SecurityCollection collection = new SecurityCollection();
            collection.addPattern("/*");

            securityConstraint.addCollection(collection);
            context.addConstraint(securityConstraint);
            }
        };

        //  Przekierowanie HTTP do HTTPS
        tomcat.addAdditionalTomcatConnectors(httpToHttpsRedirectConnector());

        return tomcat;
    }

    //  Bez SSL aplikacja korzysta z portu 8082. SSL korzysta z portu 8443.
    //  Każde zapytanie do 8082 będzie przekierowane do portu 8443

    private Connector httpToHttpsRedirectConnector(){
        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
        connector.setScheme("http");
        connector.setPort(8082);
        connector.setSecure(false);
        connector.setRedirectPort(8443);
        return connector;
    }
}
