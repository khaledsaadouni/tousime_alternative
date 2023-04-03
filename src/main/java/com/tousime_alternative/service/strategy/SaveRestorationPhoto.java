package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.dto.RestorationDto;
import com.tousime_alternative.exception.InvalidOperationException;
import com.tousime_alternative.service.FlickrService;
import com.tousime_alternative.service.RestorationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

@Service("restorationStrategy")
@Slf4j
public class SaveRestorationPhoto implements Strategy<RestorationDto> {
    private final RestorationService restorationService;
    private final FlickrService flickrService;
    @Autowired
    public SaveRestorationPhoto(RestorationService restorationService, FlickrService flickrService) {
        this.restorationService = restorationService;
        this.flickrService = flickrService;
    }

    @Override
    public RestorationDto savePhoto(long id, InputStream photo, String title) throws FlickrException {
        RestorationDto restorationDto= restorationService.findById(id).orElseThrow();
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article");
        }
        List<String> list=restorationDto.getPhoto();
        list.add(urlPhoto);
        restorationDto.setPhoto(list);
        return restorationService.update(restorationDto);
    }
}
