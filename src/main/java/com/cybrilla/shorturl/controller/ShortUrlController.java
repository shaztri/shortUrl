package com.cybrilla.shorturl.controller;

import com.cybrilla.shorturl.dto.DtoUrl;
import com.cybrilla.shorturl.dto.ErrorDto;
import com.cybrilla.shorturl.dto.responseDto;
import com.cybrilla.shorturl.entities.Url;
import com.cybrilla.shorturl.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;


@RestController
public class ShortUrlController
{
    @Autowired
    private DriverService driverService;


    @PostMapping(value = "/shorten")
    public ResponseEntity<?> linkGenerator(@RequestBody DtoUrl dtoUrl)
    {
        Url urlTobeReturned = driverService.linkGenerator(dtoUrl);

        if(urlTobeReturned!=null)
        {
            responseDto urlResponse = new responseDto();
            urlResponse.setLongUrl(urlTobeReturned.getLongUrl());
            urlResponse.setShortUrl(urlTobeReturned.getShortUrl());
            return new ResponseEntity<>(urlResponse, HttpStatus.OK);
        }

        ErrorDto errorResponse = new ErrorDto();
        errorResponse.setStatus("404");
        errorResponse.setError("error processing your request, try again");

        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<Void> redirect(@PathVariable String shortUrl) {
        Url returnLink = driverService.getLink(shortUrl);
//        if(returnLink==null || shortUrl.isEmpty())
//        {
//            ErrorDto errorResponse = new ErrorDto();
//            errorResponse.setError("Invalid url");
//            errorResponse.setStatus("400");
//            return new ResponseEntity<Void>(errorResponse, HttpStatus.OK);;
//        }

        System.out.println(returnLink.getLongUrl());

        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(returnLink.getLongUrl())).build();


    }

}


