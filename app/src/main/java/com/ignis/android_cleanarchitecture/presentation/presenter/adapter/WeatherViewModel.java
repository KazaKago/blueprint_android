package com.ignis.android_cleanarchitecture.presentation.presenter.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.ignis.android_cleanarchitecture.R;
import com.ignis.android_cleanarchitecture.domain.model.ForecastModel;
import com.ignis.android_cleanarchitecture.domain.model.ImageModel;
import com.ignis.android_cleanarchitecture.domain.model.TemperatureModel;
import com.ignis.android_cleanarchitecture.domain.model.TemperatureUnitModel;

/**
 * Weather ViewModel
 * <p>
 * Created by tamura_k on 2016/06/01.
 */
public class WeatherViewModel {

    public ObservableField<String> dateLabel;
    public ObservableField<String> date;
    public ObservableField<String> telop;
    public ObservableField<String> maxTemperature;
    public ObservableField<String> minTemperature;

    private Context context;
    private String imageUrl;

    public WeatherViewModel(Context context, ForecastModel forecast) {
        this.context = context;
        ImageModel image = forecast.getImage();
        if (image != null) this.imageUrl = image.getUrl();
        this.dateLabel = new ObservableField<>(forecast.getDateLabel());
        this.date = new ObservableField<>(forecast.getDate());
        this.telop = new ObservableField<>(forecast.getTelop());
        String maxTemperatureStr = "--";
        String minTemperatureStr = "--";
        TemperatureModel temperature = forecast.getTemperature();
        if (temperature != null) {
            TemperatureUnitModel maxTemperatureUnit = temperature.getMax();
            if (maxTemperatureUnit != null) {
                Float celius = maxTemperatureUnit.getCelsius();
                if (celius != null) {
                    maxTemperatureStr = String.valueOf(celius);
                }
            }
            TemperatureUnitModel minTemperatureUnit = temperature.getMin();
            if (minTemperatureUnit != null) {
                Float celius = minTemperatureUnit.getCelsius();
                if (celius != null) {
                    minTemperatureStr = String.valueOf(celius);
                }
            }
        }
        this.maxTemperature = new ObservableField<>(context.getString(R.string.temperature_max, maxTemperatureStr));
        this.minTemperature = new ObservableField<>(context.getString(R.string.temperature_min, minTemperatureStr));
    }

    public void onItemClick(View view) {
        showToast(telop.get());
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
