package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.dto.ArticalDto;
import com.tousime_alternative.dto.UserDto;
import com.tousime_alternative.exception.InvalidOperationException;
import com.tousime_alternative.service.FlickrService;
import com.tousime_alternative.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.InputStream;
@Service("profilStrategy")
@Slf4j
public class SaveProfilPhoto implements Strategy<UserDto> {
    private final UserService userService;
    private final FlickrService flickrService;
    @Autowired
    public SaveProfilPhoto(UserService userService, FlickrService flickrService) {
        this.userService = userService;
        this.flickrService = flickrService;
    }

    @Override
    public UserDto savePhoto(long id, InputStream photo, String title) throws FlickrException {
        UserDto userDto = userService.findById(id).orElseThrow();
        String urlPhoto = flickrService.savePhoto(photo, title);
        if (!StringUtils.hasLength(urlPhoto)) {
            throw new InvalidOperationException("Erreur lors de l'enregistrement de photo de l'article");
        }
        userDto.setPhoto(urlPhoto);
        return userService.update(userDto);
    }
}
