package com.cybrilla.shorturl.repo;

import com.cybrilla.shorturl.entities.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UrlJpaRepo extends JpaRepository<Url, Long>
{
    Url findByShortUrl(String shortUrl);
}
