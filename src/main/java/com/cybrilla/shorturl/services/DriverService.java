package com.cybrilla.shorturl.services;

import com.cybrilla.shorturl.dto.DtoUrl;
import com.cybrilla.shorturl.entities.Url;


public interface DriverService {
     Url linkGenerator(DtoUrl dtoUrl);
     Url saveShortLink(Url url);
     Url getLink(String url);
}
