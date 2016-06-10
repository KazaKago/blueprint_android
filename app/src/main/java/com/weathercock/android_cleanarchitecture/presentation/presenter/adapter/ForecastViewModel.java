package com.weathercock.android_cleanarchitecture.presentation.presenter.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import android.widget.ImageView;

import com.weathercock.android_cleanarchitecture.R;
import com.weathercock.android_cleanarchitecture.domain.model.weather.ForecastModel;
import com.weathercock.android_cleanarchitecture.domain.model.weather.ImageModel;
import com.weathercock.android_cleanarchitecture.domain.model.weather.TemperatureModel;
import com.weathercock.android_cleanarchitecture.domain.model.weather.TemperatureUnitModel;
import com.squareup.picasso.Picasso;

/**
 * Forecast ViewModel
 * <p>
 * Created by tamura_k on 2016/06/01.
 */
public class ForecastViewModel {

    public ObservableField<String> dateLabel;
    public ObservableField<String> date;
    public ObservableField<String> telop;
    public ObservableField<String> maxTemperature;
    public ObservableField<String> minTemperature;

    private Context context;
    private String imageUrl;

    public ForecastViewModel(Context context, ForecastModel forecast) {
        this.context = context;
        ImageModel image = forecast.getImage();
        if (image != null) this.imageUrl = image.getUrl();
        this.dateLabel = new ObservableField<>(forecast.getDateLabel());
        this.date = new ObservableField<>(forecast.getDate());
        this.telop = new ObservableField<>(forecast.getTelop());
        this.maxTemperature = new ObservableField<>(context.getString(R.string.temperature_max, "--"));
        this.minTemperature = new ObservableField<>(context.getString(R.string.temperature_min, "--"));
        TemperatureModel temperature = forecast.getTemperature();
        if (temperature != null) {
            TemperatureUnitModel maxTemperatureUnit = temperature.getMax();
            if (maxTemperatureUnit != null) {
                Float celius = maxTemperatureUnit.getCelsius();
                if (celius != null) {
                    this.maxTemperature.set(context.getString(R.string.temperature_max, String.valueOf(celius)));
                }
            }
            TemperatureUnitModel minTemperatureUnit = temperature.getMin();
            if (minTemperatureUnit != null) {
                Float celius = minTemperatureUnit.getCelsius();
                if (celius != null) {
                    this.minTemperature.set(context.getString(R.string.temperature_min, String.valueOf(celius)));
                }
            }
        }
    }

    public void loadImage(ImageView imageView){
        Picasso.with(context).load(imageUrl).into(imageView);
    }

}
