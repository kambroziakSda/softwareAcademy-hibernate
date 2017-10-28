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



    private void handleResponse(HttpResponse response) throws IOException {
        int statusCode = getStatus(response);
        if(statusCode>=200 && statusCode<300){
            String polandInfo = EntityUtils.toString(response.getEntity());
            printInfo(polandInfo);
            return;
        }
        throw new RuntimeException("Bad response code: "+statusCode);
    }


    private static int getStatus(HttpResponse response) {
        return response
                .getStatusLine()
                .getStatusCode();
    }

    private static HttpResponse executeRequest() throws IOException {
        String url = "https://restcountries.eu/rest/v2/name/POLAND";
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet get = new HttpGet(url);
        return httpClient.execute(get);
    }

    private void printInfo(String polandInfo) {
        JsonParser jsonParser = new JsonParser();
        JsonElement parse = jsonParser.parse(polandInfo);
        JsonArray asJsonArray = parse.getAsJsonArray();
        JsonElement jsonElement = asJsonArray.get(0);
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        int population = asJsonObject.get("population")
                .getAsInt();

        JsonArray borders = asJsonObject.getAsJsonArray("borders")
                .getAsJsonArray();

        StringBuilder bordersBuilder = new StringBuilder();

        for (int i = 0; i < borders.size(); i++) {
            bordersBuilder.append(borders.get(i)).append(", ");
        }

        bordersBuilder
                .delete(bordersBuilder.length()-2, bordersBuilder
                        .length());

        String italiano=asJsonObject.get("translations")
                .getAsJsonObject()
                .get("it").getAsString();

        StringBuilder result = new StringBuilder("Polska ma: ")
                .append(population)
                .append(" ludnosci").append(", ")
                .append("graniczy z: ")
                .append(bordersBuilder)
                .append(", ")
                .append("a po włosku to: ")
                .append(italiano);

        System.out.println(result.toString());
    }
}
