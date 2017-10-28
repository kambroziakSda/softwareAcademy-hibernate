package http.rest;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void testShowStudentsWithApacheHttpGET() throws IOException {

    }



    @Test
    public void testShowStudentsWithHTTPConnection() throws IOException {
        URLConnection url = new URL("http://localhost:8080/students").openConnection();
        System.out.println(url.getContentLength());
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[100];
        InputStream inputStream = url.getInputStream();
        while ((nRead = inputStream.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        inputStream.close();
        buffer.close();


        System.out.println(new String(buffer.toByteArray()));

    }

    @Test
    public void testAddStudentWithUrlConnection() throws IOException {
        String postData = "{\"firstName\":\"Jan\", \"lastName\":\"Ko\"}";


        URL url = new URL("http://localhost:8080/students");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setDoOutput(true);

        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setRequestProperty("Content-Length", String.valueOf(postData.length()));
        urlConnection.setRequestMethod("POST");

        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(postData.getBytes());

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }


        bufferedReader.close();
        outputStream.close();


        System.out.println(stringBuilder.toString());


    }

    @Test
    public void testAddStudentWithApacheHttpClient() throws IOException {
        HttpClient httpClient = HttpClientBuilder.create().build();

        HttpPost post = new HttpPost("http://localhost:8080/students");
        post.setEntity(new StringEntity("{\"firstName\":\"Krzy\", \"lastName\":\"Amb\"}"));
        post.setHeader(new BasicHeader("content-type", "application/json"));

        HttpResponse execute = httpClient.execute(post);

        BasicResponseHandler basicResponseHandler = new BasicResponseHandler();
        String response = basicResponseHandler.handleResponse(execute);

        System.out.println(response);

    }
}