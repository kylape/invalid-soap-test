package com.redhat.gss;

import javax.jws.WebService;
import javax.xml.ws.Service;
import java.net.URL;
import javax.xml.namespace.QName;
import java.net.MalformedURLException;
import javax.xml.ws.BindingProvider;
import org.jboss.logging.Logger;

@WebService
public class HelloClient {

  private static Logger log = Logger.getLogger(HelloClient.class);

  public String test() throws Exception {
    URL wsdl = new URL("http://localhost:8080/client-config-test/HelloEndpoint?wsdl");
    QName qname = new QName("http://gss.redhat.com/", "HelloEndpointService");
    Service service = Service.create(wsdl, qname);
    Hello port = (Hello)service.getPort(Hello.class);
    log.info("Good endpoint says: \"" + port.sayHello("Kyle") + "\"");
    ((BindingProvider)port).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, "http://localhost:8080/client-config-test/badEndpoint");
    return "Value from endpoint: \"" + port.sayHello("Kyle") + "\"";
  }
}
