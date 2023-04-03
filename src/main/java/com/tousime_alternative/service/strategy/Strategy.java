package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;

import java.io.InputStream;

public interface Strategy<T> {
    T savePhoto(long id, InputStream photo, String title) throws FlickrException;
}
