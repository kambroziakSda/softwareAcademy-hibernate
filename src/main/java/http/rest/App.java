package http.rest;

import io.undertow.Undertow;
import org.jboss.resteasy.plugins.server.undertow.UndertowJaxrsServer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        UndertowJaxrsServer ut = new UndertowJaxrsServer();

        AppEntry appEntry = new AppEntry();

        ut.deploy(appEntry);

        ut.start(
                Undertow.builder()
                        .addHttpListener(8080, "localhost")

        );
        System.out.println("Application is running");
    }
}
