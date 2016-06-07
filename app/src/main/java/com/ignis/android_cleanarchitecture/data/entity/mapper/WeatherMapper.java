package com.ignis.android_cleanarchitecture.data.entity.mapper;

import com.ignis.android_cleanarchitecture.data.entity.CopyrightEntity;
import com.ignis.android_cleanarchitecture.data.entity.DescriptionEntity;
import com.ignis.android_cleanarchitecture.data.entity.ForecastEntity;
import com.ignis.android_cleanarchitecture.data.entity.ImageEntity;
import com.ignis.android_cleanarchitecture.data.entity.LinkEntity;
import com.ignis.android_cleanarchitecture.data.entity.LocationEntity;
import com.ignis.android_cleanarchitecture.data.entity.TemperatureEntity;
import com.ignis.android_cleanarchitecture.data.entity.TemperatureUnitEntity;
import com.ignis.android_cleanarchitecture.data.entity.WeatherEntity;
import com.ignis.android_cleanarchitecture.domain.model.CopyrightModel;
import com.ignis.android_cleanarchitecture.domain.model.DescriptionModel;
import com.ignis.android_cleanarchitecture.domain.model.ForecastModel;
import com.ignis.android_cleanarchitecture.domain.model.ImageModel;
import com.ignis.android_cleanarchitecture.domain.model.LinkModel;
import com.ignis.android_cleanarchitecture.domain.model.LocationModel;
import com.ignis.android_cleanarchitecture.domain.model.TemperatureModel;
import com.ignis.android_cleanarchitecture.domain.model.TemperatureUnitModel;
import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;

/**
 * Weather Model/Entity Mapper.
 * <p>
 * Created by tamura_k on 2016/06/07.
 */
public class WeatherMapper {

    public static WeatherModel convert(WeatherEntity entity) {
        WeatherModel model = null;
        if (entity != null) {
            model = new WeatherModel();
            model.setCityId(entity.getCityId());
            model.setLocation(convert(entity.getLocation()));
            model.setTitle(entity.getTitle());
            model.setLink(entity.getLink());
            model.setPublicTime(entity.getPublicTime());
            model.setDescription(convert(entity.getDescription()));
            model.setForecasts(convertForecastList(entity.getForecasts()));
            model.setPinpointLocations(convertLinkList(entity.getPinpointLocations()));
            model.setCopyright(convert(entity.getCopyright()));
        }
        return model;
    }

    private static LocationModel convert(LocationEntity entity) {
        LocationModel model = null;
        if (entity != null) {
            model = new LocationModel();
            model.setArea(entity.getArea());
            model.setPrefecture(entity.getPrefecture());
            model.setCity(entity.getCity());
        }
        return model;
    }

    private static DescriptionModel convert(DescriptionEntity entity) {
        DescriptionModel model = null;
        if (entity != null) {
            model = new DescriptionModel();
            model.setText(entity.getText());
            model.setPublicTime(entity.getPublicTime());
        }
        return model;
    }

    private static List<ForecastModel> convertForecastList(List<ForecastEntity> entityList) {
        if (entityList != null) {
            return Observable.from(entityList)
                    .map(WeatherMapper::convert)
                    .toList()
                    .toBlocking()
                    .single();
        } else {
            return new ArrayList<>();
        }
    }

    private static ForecastModel convert(ForecastEntity entity) {
        ForecastModel model = null;
        if (entity != null) {
            model = new ForecastModel();
            model.setDate(entity.getDate());
            model.setDateLabel(entity.getDateLabel());
            model.setTelop(entity.getTelop());
            model.setImage(convert(entity.getImage()));
            model.setTemperature(convert(entity.getTemperature()));
        }
        return model;
    }

    private static ImageModel convert(ImageEntity entity) {
        ImageModel model = null;
        if (entity != null) {
            model = new ImageModel();
            model.setTitle(entity.getTitle());
            model.setLink(entity.getLink());
            model.setUrl(entity.getUrl());
            model.setWidth(entity.getWidth());
            model.setHeight(entity.getHeight());
        }
        return model;
    }

    private static TemperatureModel convert(TemperatureEntity entity) {
        TemperatureModel model = null;
        if (entity != null) {
            model = new TemperatureModel();
            model.setMax(convert(entity.getMax()));
            model.setMin(convert(entity.getMin()));
        }
        return model;
    }

    private static TemperatureUnitModel convert(TemperatureUnitEntity entity) {
        TemperatureUnitModel model = null;
        if (entity != null) {
            model = new TemperatureUnitModel();
            model.setCelsius(entity.getCelsius());
            model.setFahrenheit(entity.getFahrenheit());
        }
        return model;
    }

    private static List<LinkModel> convertLinkList(List<LinkEntity> entityList) {
        if (entityList != null) {
            return Observable.from(entityList)
                    .map(WeatherMapper::convert)
                    .toList()
                    .toBlocking()
                    .single();
        } else {
            return new ArrayList<>();
        }
    }

    private static LinkModel convert(LinkEntity entity) {
        LinkModel model = null;
        if (entity != null) {
            model = new LinkModel();
            model.setName(entity.getName());
            model.setLink(entity.getLink());
        }
        return model;
    }

    private static CopyrightModel convert(CopyrightEntity entity) {
        CopyrightModel model = null;
        if (entity != null) {
            model = new CopyrightModel();
            model.setTitle(entity.getTitle());
            model.setLink(entity.getLink());
            model.setImage(convert(entity.getImage()));
            model.setProvider(convertLinkList(entity.getProvider()));
        }
        return model;
    }

}
