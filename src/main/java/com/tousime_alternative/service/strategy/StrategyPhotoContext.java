package com.tousime_alternative.service.strategy;

import com.flickr4java.flickr.FlickrException;
import com.tousime_alternative.exception.InvalidOperationException;
import lombok.Setter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

@Service
public class StrategyPhotoContext {
    private BeanFactory beanFactory;
    private Strategy strategy;
    @Setter
    private String context;
    @Autowired
    public StrategyPhotoContext(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
    public Object savePhoto(String context, Integer id, InputStream photo, String title) throws FlickrException {
        determinContext(context);
        return strategy.savePhoto(id, photo, title);
    }

    private void determinContext(String context) {
        final String beanName = context + "Strategy";
        switch (context) {
            case "accomodation":
                strategy = beanFactory.getBean(beanName, SaveAccomodationPhoto.class);
                break;
            case "artical" :
                strategy = beanFactory.getBean(beanName, SaveArticalPhoto.class);
                break;
            case "event" :
                strategy = beanFactory.getBean(beanName, SaveEventPhoto.class);
                break;
            case "profil":
                strategy = beanFactory.getBean(beanName, SaveProfilPhoto.class);
                break;
            case "restoration":
                strategy = beanFactory.getBean(beanName, SaveRestorationPhoto.class);
                break;
            case "shop":
                strategy = beanFactory.getBean(beanName, SaveShopPhoto.class);
                break;
            case "program":
                strategy = beanFactory.getBean(beanName, SaveProgramPhoto.class);
                break;
            default:
                throw new InvalidOperationException("Contexte inconnue pour l'enregistrement de la photo");
        }
    }
}
