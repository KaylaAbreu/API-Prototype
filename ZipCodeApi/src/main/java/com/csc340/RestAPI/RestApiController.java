package com.csc340.RestAPI;

import org.json.JSONObject;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author Kayla A
 */
@RestController
public class RestApiController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello, %s!", name);
    }

    /**
     * Get a random quote from quotable and make it available at this endpoint.
     *
     * @return
     */
    @GetMapping("/quote")
    public Object getQuote() {
        String url = "https://api.quotable.io/random";
        RestTemplate restTemplate = new RestTemplate();
        Object jSonQuote = restTemplate.getForObject(url, Object.class);

        //Print the whole response to console.
        String quote = restTemplate.getForObject(url, String.class);
        //Parse out the most important info from the response.
        JSONObject jo = new JSONObject(quote);
        System.out.println(jo.toString());
        String quoteAuthor = jo.getString("author");
        String quoteContent = jo.getString("content");
        System.out.println(quoteAuthor);
        System.out.println(quoteContent);

        return jSonQuote;
    }

    /**
     * Print response from the API and parse out specific information from it.
     *
     * @return
     */
    @GetMapping("/ZipCodeInfo")
    public Object getZipCodeInfo() {
        String url = "https://service.zipapi.us/zipcode/27407?X-API-KEY=js-a08bc4b540bc636b28297e84a64ebee1";
        RestTemplate restTemplate = new RestTemplate();
        Object jSonZipCodeInfo = restTemplate.getForObject(url, Object.class);

        //Print the whole response to console
        String ZipCodeInfo = restTemplate.getForObject(url, String.class);
        JSONObject jo = new JSONObject(ZipCodeInfo);
        System.out.println(jo.toString());

        //Parse out relevant info from each entry in the response
        String state = jo.getJSONObject("data").getString("state");
        String city = jo.getJSONObject("data").getString("city");

        System.out.println(city);
        System.out.println(state);

        return jSonZipCodeInfo;
    }

}
