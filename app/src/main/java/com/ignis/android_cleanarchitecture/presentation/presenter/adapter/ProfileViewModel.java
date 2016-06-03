package com.ignis.android_cleanarchitecture.presentation.presenter.adapter;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import android.widget.Toast;

import com.ignis.android_cleanarchitecture.domain.model.WeatherModel;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Profile ViewModel
 * <p/>
 * Created by tamura_k on 2016/06/01.
 */
public class ProfileViewModel {

    private Context context;
    public ObservableField<String> firstName;
    public ObservableField<String> lastName;
    public ObservableField<String> fullName;
    public ObservableField<String> birthday;
    public ObservableField<String> age;

    public ProfileViewModel(Context context, WeatherModel weatherModel) {
        this.context = context;
//        this.firstName = new ObservableField<>(weatherModel.getFirstName());
//        this.lastName = new ObservableField<>(weatherModel.getLastName());
//        this.fullName = new ObservableField<>(context.getString(R.string.full_name, weatherModel.getFirstName(), weatherModel.getLastName()));
//        this.birthday = new ObservableField<>(formatBirthday(weatherModel.getBirthday()));
//        this.age = new ObservableField<>(context.getString(R.string.age_unit, getAge(weatherModel.getBirthday())));
    }

    public void onItemClick(View view) {
        showToast(fullName.get());
    }

    private String formatBirthday(Date birthday) {
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(context);
        return dateFormat.format(birthday);
    }

    private int getAge(Date birthday) {
        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.setTime(birthday);
        Calendar todayCalendar = Calendar.getInstance();
        int ageYear = (todayCalendar.get(Calendar.YEAR) - birthdayCalendar.get(Calendar.YEAR));
        int ageMonth = (todayCalendar.get(Calendar.MONTH) - birthdayCalendar.get(Calendar.MONTH));
        if (ageMonth < 0) ageYear--;
        return ageYear;
    }

    private void showToast(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

}
