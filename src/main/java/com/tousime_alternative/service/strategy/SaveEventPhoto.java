package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.dto.EventDto;
import com.tousime_alternative.exception.InvalidOperationException;
import com.tousime_alternative.service.EventService;
import com.tousime_alternative.service.FlickrService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

@Service("eventStrategy")
@Slf4j
public class SaveEventPhoto implements Strategy<EventDto>{
    private final FlickrService flickrService;
    private final EventService eventService;
    @Autowired
    public SaveEventPhoto(FlickrService flickrService, EventService eventService) {
        this.flickrService = flickrService;
        this.eventService = eventService;
    }

    @Override
    public EventDto savePhoto(long id, InputStream photo, String title) throws FlickrException {
        EventDto eventDto= eventService.findById(id).orElseThrow();
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article");
        }
        List<String> list=eventDto.getPhoto();
        list.add(urlPhoto);
        eventDto.setPhoto(list);
        return eventService.update(eventDto);
    }
}
