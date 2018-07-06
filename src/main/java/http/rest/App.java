package http.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.undertow.Undertow;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
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
                        .addHttpListener(8080, "localhost")

        );
        System.out.println("Application is running");
    }

}
