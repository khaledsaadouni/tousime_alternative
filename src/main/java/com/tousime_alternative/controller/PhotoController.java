package com.tousime_alternative.controller;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.service.strategy.StrategyPhotoContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/photo")
public class PhotoController {
    private final StrategyPhotoContext strategyPhotoContext;
    @Autowired
    public PhotoController(StrategyPhotoContext strategyPhotoContext) {
        this.strategyPhotoContext = strategyPhotoContext;
    }

    @PostMapping("/{id}/{title}/{context}")
    Object savePhoto(@PathVariable("context") String context, @PathVariable("id") Integer id, @RequestPart("file") MultipartFile photo, @PathVariable(
            "title") String title) throws IOException,
            FlickrException {
        return strategyPhotoContext.savePhoto(context, id, photo.getInputStream(), title);
    }
}
