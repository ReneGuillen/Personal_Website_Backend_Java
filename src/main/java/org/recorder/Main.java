package org.recorder;

import org.apache.hc.client5.http.HttpResponseException;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpUriRequestBase;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpStatus;
import org.apache.hc.core5.http.ParseException;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {

        try {
            // Create an HttpClient instance
            try (CloseableHttpClient httpClient = HttpClients.createDefault()) {

                final String SOCCER_API_URL = "https://v3.football.api-sports.io/leagues";
                final String API_KEY = "fake-api-key";
                final String API_HOST = "v3.football.api-sports.io";

                // Create an HTTP GET request
                HttpUriRequestBase request = new HttpGet(SOCCER_API_URL);
                request.setHeader("x-rapidapi-key", API_KEY);
                request.setHeader("x-rapidapi-host", API_HOST);

                // Execute the request and get the response
                CloseableHttpResponse response = httpClient.execute(request);

                // Check if the response status code is OK (200)
                if (response.getCode() == HttpStatus.SC_OK) {
                    // Read and print the response content
                    String responseBody = EntityUtils.toString(response.getEntity());
                    System.out.println("Response:\n" + responseBody);
                } else {
                    // Handle non-OK response
                    throw new HttpResponseException(response.getCode(), response.getReasonPhrase());
                }
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }
}