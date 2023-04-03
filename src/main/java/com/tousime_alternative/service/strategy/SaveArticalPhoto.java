package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.dto.ArticalDto;
import com.tousime_alternative.exception.InvalidOperationException;
import com.tousime_alternative.service.ArticalService;
import com.tousime_alternative.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("articalStrategy")
@Slf4j
public class SaveArticalPhoto implements Strategy<ArticalDto>{
    private ArticalService articalService;
    private FlickrService flickrService;
    @Autowired
    public SaveArticalPhoto(ArticalService articalService, FlickrService flickrService) {
        this.articalService = articalService;
        this.flickrService = flickrService;
    }

    @Override
    public ArticalDto savePhoto(long id, InputStream photo, String title) throws FlickrException {
        ArticalDto articalDto = articalService.findById(id).orElseThrow();
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article");
        }
        articalDto.setPhoto(urlPhoto);
        return articalService.update(articalDto);
    }
}
