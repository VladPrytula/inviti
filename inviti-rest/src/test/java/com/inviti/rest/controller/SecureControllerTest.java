package com.inviti.rest.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by vladyslavprytula on 9/3/14.
 */
public class SecureControllerTest {
    static HttpHeaders getHeaders(String auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        byte[] encodedAuthorisation = Base64.encode(auth.getBytes());
        headers.add("Authorization", "Basic " + new String(encodedAuthorisation));

        return headers;
    }

    @Ignore
    @Test // TODO this actually need running server
    public void thatOrdersCanBeAddedAndQueried() throws Exception{

        HttpEntity<String> requestEntityCorrect = new HttpEntity<String>(getHeaders("vlad" + ":" + "123"));

        RestTemplate template = new RestTemplate();
        assertThat(template.exchange("http://localhost:8080/rest/ping", HttpMethod.GET, requestEntityCorrect, String.class).getBody(), is("\"defaultName\""));

    }
}
