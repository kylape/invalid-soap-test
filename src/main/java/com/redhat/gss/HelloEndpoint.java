package com.redhat.gss;

import javax.jws.WebService;
import org.jboss.logging.Logger;

@WebService(endpointInterface="com.redhat.gss.Hello")
public class HelloEndpoint implements Hello {
  private static Logger log = Logger.getLogger(Hello.class);

  public String sayHello(String name) {
    String greeting = "Hello, " + name + "!";
    log.info(greeting);
    return greeting;
  }
}
