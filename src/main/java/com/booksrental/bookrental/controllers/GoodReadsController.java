package com.booksrental.bookrental.controllers;

import com.booksrental.bookrental.model.goodreads.GoodreadsResponse;
import java.io.StringReader;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/v1/goodreads/")
@Slf4j
public class GoodReadsController {
    public static final String BASE_GOODREADS_URL = "https://www.goodreads.com/search/index.xml";
    public static final String TOKEN_SERVER_URL = BASE_GOODREADS_URL + "/oauth/request_token";
    public static final String AUTHENTICATE_URL = BASE_GOODREADS_URL + "/oauth/authorize";
    public static final String ACCESS_TOKEN_URL = BASE_GOODREADS_URL + "/oauth/access_token";

    //TODO: Dont forget to add your key and secret key which you received after
    //signing up with goodreads
    public static final String GOODREADS_KEY = "";
    public static final String GOODREADS_SECRET = "";

    @GetMapping(value = "/bookname/{name}", produces = "application/json")
    public ResponseEntity<?> searchBookByName(@PathVariable("name") String name) {
        GoodreadsResponse response = new GoodreadsResponse();
        try {
            String localUrl = BASE_GOODREADS_URL + "?key=" + GOODREADS_KEY + "&q=" + name;
            //localUrl = "https://www.goodreads.com/search/index.xml?key=k0r1vjqG6WLfVejxUZsoxw&q=" + name;
            RestTemplate restTemplate = new RestTemplate();

            ResponseEntity<String> stringResponseEntity = restTemplate
                    .getForEntity(localUrl,String.class);

            JAXBContext jaxbContext = JAXBContext.newInstance(GoodreadsResponse.class);
            String myresonse = stringResponseEntity.getBody().toString();

            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            response = (GoodreadsResponse) unmarshaller
                    .unmarshal(new StringReader(myresonse));

            log.info("Good reads response is \n {}", response);
        } catch (JAXBException e) {
            log.error("Exception : {} ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }


      return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
