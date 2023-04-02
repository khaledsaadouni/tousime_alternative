package com.tousime_alternative.service;

import java.io.InputStream;

public interface FlickrService {
    String savePhoto(InputStream photo, String title);
}
