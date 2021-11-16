package com.cybrilla.shorturl.services;

import com.cybrilla.shorturl.dto.DtoUrl;
import com.cybrilla.shorturl.entities.Url;
import com.cybrilla.shorturl.repo.UrlJpaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
@Component
public class DriverServiceImpl implements DriverService {

    @Autowired
    private UrlJpaRepo urlJpaRepo;

    @Override
    public Url linkGenerator(DtoUrl dtoUrl) {
        if(!(dtoUrl.getUrl().isEmpty())){
            String encodedUrl = encodeUrl(dtoUrl.getUrl());
            Url urlToSave = new Url();
            urlToSave.setCreatedOn(LocalDateTime.now());
            urlToSave.setLongUrl(dtoUrl.getUrl());
            urlToSave.setShortUrl(encodedUrl);

            Url urlTobeReturned = saveShortLink(urlToSave);

            if(urlTobeReturned!=null) {
                return urlTobeReturned;
            }
        }

        return null;
    }

    private String encodeUrl(String url) {

        /* using MD5 hashing on URL using LocalDateTime.now() to make every call unique and using substring from hash for shortened url (customizable length)*/

        String linkToHash = url;
        String encodedUrl = null;
        LocalDateTime timeNow = LocalDateTime.now();
        String salt = timeNow.toString();
        linkToHash = linkToHash.concat(salt);

        //getHash using MD5
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(linkToHash.getBytes());

            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            encodedUrl = sb.toString();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }

        //pick substring 4 to 11 from hash -- this can be improved by further encoding or using random length
        encodedUrl = encodedUrl.substring(4,11);

        return encodedUrl;

    }

    @Override
    public Url saveShortLink(Url url) {
        return urlJpaRepo.save(url);
    }

    @Override
    public Url getLink(String url) {
        return urlJpaRepo.findByShortUrl(url);
    }
}
