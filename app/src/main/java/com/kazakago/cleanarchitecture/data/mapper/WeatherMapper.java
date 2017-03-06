package com.kazakago.cleanarchitecture.data.mapper;

import android.support.annotation.Nullable;

import com.kazakago.cleanarchitecture.data.entity.weather.WeatherEntity;
import com.kazakago.cleanarchitecture.domain.model.weather.CopyrightModel;
import com.kazakago.cleanarchitecture.domain.model.weather.DescriptionModel;
import com.kazakago.cleanarchitecture.domain.model.weather.ForecastModel;
import com.kazakago.cleanarchitecture.domain.model.weather.ImageModel;
import com.kazakago.cleanarchitecture.domain.model.weather.LinkModel;
import com.kazakago.cleanarchitecture.domain.model.weather.LocationModel;
import com.kazakago.cleanarchitecture.domain.model.weather.WeatherModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by tamura_k on 2016/12/08.
 */
public class WeatherMapper {

    public static WeatherModel map(@Nullable WeatherEntity entity) {
        if (entity != null) {
            WeatherModel weatherModel = new WeatherModel();
            weatherModel.setCityId(entity.getCityId());
            if (entity.getLocation() != null) {
                LocationModel locationModel = new LocationModel();
                locationModel.setArea(entity.getLocation().getArea());
                locationModel.setPrefecture(entity.getLocation().getPrefecture());
                locationModel.setCity(entity.getLocation().getCity());
                weatherModel.setLocation(locationModel);
            }
            weatherModel.setTitle(entity.getTitle());
            weatherModel.setLink(entity.getLink());
            weatherModel.setPublicTime(entity.getPublicTime());
            if (entity.getDescription() != null) {
                DescriptionModel descriptionModel = new DescriptionModel();
                descriptionModel.setText(entity.getDescription().getText());
                descriptionModel.setPublicTime(entity.getDescription().getPublicTime());
                weatherModel.setDescription(descriptionModel);
            }
            if (entity.getForecasts() != null) {
                List<ForecastModel> forecastModels = Observable.fromIterable(entity.getForecasts())
                        .map(forecastEntity -> {
                            ForecastModel forecastModel = new ForecastModel();
                            forecastModel.setTelop(forecastEntity.getTelop());
                            forecastModel.setDate(forecastEntity.getDate());
                            forecastModel.setDateLabel(forecastEntity.getDateLabel());
                            if (forecastEntity.getImage() != null) {
                                ImageModel imageModel = new ImageModel();
                                imageModel.setTitle(forecastEntity.getImage().getTitle());
                                imageModel.setHeight(forecastEntity.getImage().getHeight());
                                imageModel.setLink(forecastEntity.getImage().getLink());
                                imageModel.setUrl(forecastEntity.getImage().getUrl());
                                imageModel.setWidth(forecastEntity.getImage().getWidth());
                                forecastModel.setImage(imageModel);
                            }
                            return forecastModel;
                        })
                        .toList()
                        .blockingGet();
                weatherModel.setForecasts(forecastModels);
            }
            if (entity.getPinpointLocations() != null) {
                List<LinkModel> pinpointLocations = Observable.fromIterable(entity.getPinpointLocations())
                        .map(pinpointLocationEntity -> {
                            LinkModel pinpointLocationModel = new LinkModel();
                            pinpointLocationModel.setName(pinpointLocationEntity.getName());
                            pinpointLocationModel.setLink(pinpointLocationEntity.getLink());
                            return pinpointLocationModel;
                        })
                        .toList()
                        .blockingGet();
                weatherModel.setPinpointLocations(pinpointLocations);
            }
            if (entity.getCopyright() != null) {
                CopyrightModel copyrightModel = new CopyrightModel();
                copyrightModel.setTitle(entity.getCopyright().getTitle());
                copyrightModel.setLink(entity.getCopyright().getLink());
                if (entity.getCopyright().getImage() != null) {
                    ImageModel imageModel = new ImageModel();
                    imageModel.setTitle(entity.getCopyright().getImage().getTitle());
                    imageModel.setHeight(entity.getCopyright().getImage().getHeight());
                    imageModel.setLink(entity.getCopyright().getImage().getLink());
                    imageModel.setUrl(entity.getCopyright().getImage().getUrl());
                    imageModel.setWidth(entity.getCopyright().getImage().getWidth());
                    copyrightModel.setImage(imageModel);
                }
                if (entity.getCopyright().getProvider() != null) {
                    List<LinkModel> providers = Observable.fromIterable(entity.getCopyright().getProvider())
                            .map(providerEntity -> {
                                LinkModel providerModel = new LinkModel();
                                providerModel.setName(providerEntity.getName());
                                providerModel.setLink(providerEntity.getLink());
                                return providerModel;
                            })
                            .toList()
                            .blockingGet();
                    copyrightModel.setProvider(providers);
                }
                weatherModel.setCopyright(copyrightModel);
            }
            return weatherModel;
        } else {
            return null;
        }
    }

}
