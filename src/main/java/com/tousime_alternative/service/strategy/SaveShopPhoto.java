package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.dto.ArtisanDto;
import com.tousime_alternative.dto.RestorationDto;
import com.tousime_alternative.exception.InvalidOperationException;
import com.tousime_alternative.service.ArtisanService;
import com.tousime_alternative.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

@Service("shopStrategy")
@Slf4j
public class SaveShopPhoto implements Strategy<ArtisanDto> {
    private final ArtisanService artisanService;
    private final FlickrService flickrService;
    @Autowired
    public SaveShopPhoto(ArtisanService artisanService, FlickrService flickrService) {
        this.artisanService = artisanService;
        this.flickrService = flickrService;
    }

    @Override
    public ArtisanDto savePhoto(long id, InputStream photo, String title) throws FlickrException {
        ArtisanDto artisanDto= artisanService.findById(id).orElseThrow();
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article");
        }
        List<String> list=artisanDto.getPhoto();
        list.add(urlPhoto);
        artisanDto.setPhoto(list);
        return artisanService.update(artisanDto);
    }
}
