package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.dto.AccomodationDto;
import com.tousime_alternative.exception.InvalidOperationException;
import com.tousime_alternative.service.AccomodationService;
import com.tousime_alternative.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

@Service("accomodationStrategy")
@Slf4j
public class SaveAccomodationPhoto implements Strategy<AccomodationDto> {
    private final AccomodationService accomodationService;
    private final FlickrService flickrService;

    @Autowired
    public SaveAccomodationPhoto(AccomodationService accomodationService, FlickrService flickrService) {
        this.accomodationService = accomodationService;
        this.flickrService = flickrService;
    }

    @Override
    public AccomodationDto savePhoto(long id, InputStream photo, String title) throws FlickrException {
        AccomodationDto accomodationDto= accomodationService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article");
        }
        List<String> list=accomodationDto.getPhoto();
        list.add(urlPhoto);
        accomodationDto.setPhoto(list);
        return accomodationService.update(accomodationDto);
    }
}
