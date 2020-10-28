package com.example;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class Example {

    public static void main(String[] args) {
        String URL = "http://91.241.64.178:7081/api/users";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String>entity = new HttpEntity<>(httpHeaders);

        ResponseEntity<String>responseEntity = restTemplate.exchange(URL, HttpMethod.GET, entity, String.class);

        String result = responseEntity.getBody();

        String sessionID  = responseEntity.getHeaders().getFirst(HttpHeaders.SET_COOKIE);


        System.out.println("--------------------------------------------");
        System.out.println("метод GET:");
        System.out.println();
        System.out.println("header:");
        System.out.println();
        System.out.println(sessionID);
        System.out.println();
        System.out.println("body:");
        System.out.println();
        System.out.println(result);
        System.out.println("--------------------------------------------");

        Map<String, Object>map = new LinkedHashMap<>();
        map.put("id", 3);
        map.put("name", "James");
        map.put("lastName", "Brown");
        map.put("age", (byte) 27);

        HttpHeaders httpHeadersPost = new HttpHeaders();
        httpHeadersPost.setContentType(MediaType.APPLICATION_JSON);
        httpHeadersPost.set(HttpHeaders.COOKIE, sessionID);
        httpHeadersPost.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        HttpEntity<Map<String, String>> httpEntityPost = new HttpEntity(map, httpHeadersPost);
        ResponseEntity<String>responseEntityPost = restTemplate.exchange(URL, HttpMethod.POST, httpEntityPost, String.class);

        String resultPost = responseEntityPost.getBody();

        System.out.println("--------------------------------------------");
        System.out.println("метод POST:");
        System.out.println();
        System.out.println("body:");
        System.out.println();
        System.out.println(resultPost);
        System.out.println(responseEntityPost.toString());
        System.out.println("--------------------------------------------");

        map.put("name", "Thomas");
        map.put("lastName", "Shelby");
        
        HttpEntity<Map<String, String>>httpEntityPut = new HttpEntity(map,httpHeadersPost);
        ResponseEntity<String>responseEntityPut = restTemplate.exchange(URL, HttpMethod.PUT, httpEntityPut, String.class);

        String resultPut = responseEntityPut.getBody();

        System.out.println("--------------------------------------------");
        System.out.println("метод PUT:");
        System.out.println();
        System.out.println("body:");
        System.out.println();
        System.out.println(resultPut);
        System.out.println(responseEntityPut.toString());
        System.out.println("--------------------------------------------");

        HttpEntity httpEntityDelete = new HttpEntity(httpHeadersPost);
        ResponseEntity<String>responseEntityDelete = restTemplate.exchange(URL+"/3", HttpMethod.DELETE, httpEntityDelete, String.class);

        String resultDelete = responseEntityDelete.getBody();

        System.out.println("--------------------------------------------");
        System.out.println("метод DELETE:");
        System.out.println();
        System.out.println("body:");
        System.out.println();
        System.out.println(resultDelete);
        System.out.println(responseEntityDelete.toString());
        System.out.println("--------------------------------------------");



        String resultTask = resultPost+resultPut+resultDelete;
        char[]chars = resultTask.toCharArray();
        int size = chars.length;


        System.out.println("--------------------------------------------");
        System.out.println();
        System.out.println("решенеие задачи:");
        System.out.println();
        System.out.println(resultTask);
        System.out.println("колличество символов: "+size);
//        5ebfeb + d9d771 + c1ba60

    }
}
