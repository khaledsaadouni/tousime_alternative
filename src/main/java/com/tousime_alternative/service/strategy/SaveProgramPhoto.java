package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.dto.ProgramDto;
import com.tousime_alternative.exception.InvalidOperationException;
import com.tousime_alternative.service.FlickrService;
import com.tousime_alternative.service.ProgramService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
import java.util.List;

@Service("programStrategy")
@Slf4j
public class SaveProgramPhoto implements Strategy<ProgramDto> {
    private final FlickrService flickrService;
    private final ProgramService programService;

    @Autowired
    public SaveProgramPhoto(FlickrService flickrService, ProgramService programService) {
        this.flickrService = flickrService;
        this.programService = programService;
    }

    @Override
    public ProgramDto savePhoto(long id, InputStream photo, String title) throws FlickrException {
        ProgramDto programDto = programService.findById(id);
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article");
        }
        List<String> list = programDto.getPhoto();
        list.add(urlPhoto);
        programDto.setPhoto(list);
        return programService.update(programDto);
    }
}
