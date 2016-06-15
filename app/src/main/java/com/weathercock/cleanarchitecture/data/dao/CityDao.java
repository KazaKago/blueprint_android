package com.weathercock.cleanarchitecture.data.dao;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.weathercock.cleanarchitecture.data.entity.city.PrefEntity;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * City Data Access Object.
 * <p>
 * Created by weath on 2016/06/14.
 */
public class CityDao {

    private Context context;

    public CityDao(Context context) {
        this.context = context;
    }

    public List<PrefEntity> find() throws IOException, JSONException {
        String cityJson = readJson();
        return parseJson(cityJson);
    }

    private String readJson() throws IOException {
        InputStream is = null;
        BufferedReader br = null;
        String text = "";

        try {
            is = context.getAssets().open("json/city.json");
            br = new BufferedReader(new InputStreamReader(is));

            String str;
            while ((str = br.readLine()) != null) {
                text += str + "\n";
            }
        } finally {
            if (is != null) is.close();
            if (br != null) br.close();
        }

        return text;
    }

    private List<PrefEntity> parseJson(String jsonStr) throws JSONException {
        Gson gson = new Gson();
        List<PrefEntity> entityList = gson.fromJson(jsonStr, new TypeToken<List<PrefEntity>>() {
        }.getType());

        return entityList;
    }

}
