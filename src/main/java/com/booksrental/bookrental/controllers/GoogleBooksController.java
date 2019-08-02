package com.booksrental.bookrental.controllers;

import com.booksrental.bookrental.model.googlebook.GoogleBook;
import io.micrometer.core.annotation.Timed;

import java.io.StringReader;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.json.*;

@RestController
@RequestMapping("/api/v1/googlebooks")
@Slf4j
public class GoogleBooksController {

    private final String googleBooksApiUrl = "https://www.googleapis.com/books/v1/";
    @GetMapping(value = "/bookname/{name}", produces = "application/json")
    public ResponseEntity<GoogleBook> getBooks(@PathVariable("name") String name) {
        name = name.replaceAll(" ", "%20");
        String url = googleBooksApiUrl + "/volumes?q=" + name + "&maxResults=40";
        log.info("url is {}", url);// flowers+inauthor:keyes";
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<GoogleBook> responseEntity = restTemplate
                .getForEntity(url, GoogleBook.class );

        GoogleBook book = new GoogleBook();
        book = responseEntity.getBody();
        log.info("google books api response is {}", book);
        return new ResponseEntity<GoogleBook>(book, HttpStatus.OK);
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<String> getBook(@PathVariable("name") String name) {
        name = name.replaceAll(" ", "%20");
        String url = googleBooksApiUrl + "/volumes?q=" + name + "&maxResults=10";
        log.info("url is {}", url);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate
                .getForEntity(url, String.class);

        JsonObject googleBook;
        JsonReader reader = Json.createReader(new StringReader(responseEntity.getBody()));
        googleBook = reader.readObject();

        JsonArray items = googleBook.getJsonArray("items");
        log.info("Number of items are {}", items.size());

        for (JsonObject item : items.getValuesAs(JsonObject.class)) {
            JsonObject volumeInfo = item.getJsonObject("volumeInfo");
            processVolumeInfo(volumeInfo);
        }
        log.info("=====================================");
        //log.info("Response is {}", responseEntity.getBody());
        return new ResponseEntity<String>(responseEntity.getBody(), HttpStatus.OK);
    }

    private void processVolumeInfo(JsonObject volumeInfo) {
        String title = volumeInfo.getString("title");
        log.info("title is {}", title);

        JsonArray authors = volumeInfo.getJsonArray("authors");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < authors.size(); i++) {
            sb.append(authors.getString(i));
        }

        log.info("authors are {}", sb.toString());
    }
}
