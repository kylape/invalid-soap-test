package com.redhat.gss;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jboss.logging.Logger;

@WebServlet({"/badEndpoint"})
public class MisbehavingHelloEndpoint extends HttpServlet {

  private static Logger log = Logger.getLogger(MisbehavingHelloEndpoint.class);
  private String responseXml = null;

  public void init() {
    InputStream is = null;
    try {
      is = getClass().getResource("/response.xml").openStream();
      StringBuilder builder = new StringBuilder();
      int i; 
      while((i = is.read()) != -1) {
        builder.append((char)i);
      }
      responseXml = builder.toString();
    } catch(Exception e) {
      log.error("Couldn't load response XML", e);
    } finally {
      try {
        is.close();
      } catch(Exception e) {
      }
    }
  }

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    Writer writer = null;
    try {
      response.setStatus(200);
      response.setContentType("application/soap+xml");
      writer = new OutputStreamWriter(response.getOutputStream());
      writer.write(responseXml);
    } catch(Exception e) {
      log.error("Couldn't complete response", e);
      response.setStatus(500);
    } finally {
      writer.close();
    }
  }
}
