package http.rest;

import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
       // HttpResponse response = executeRequest();
       // handleResponse(response);


        buildServer();
    }

    private static void buildServer() {
        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        AppEntry appEntry = new AppEntry();

        ut.deploy(appEntry);

        ut.start(
                Undertow.builder()
                        .addHttpListener(80, "localhost")

        );
        System.out.println("Application is running");
    }

}
