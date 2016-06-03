package com.ignis.android_cleanarchitecture.domain.model;

import java.util.List;

/**
 * Weather Model
 * <p>
 * Created by tamura_k on 2016/05/31.
 */
public class WeatherModelOld {

    //予報を発表した地域を定義
    private Location location;
    //タイトル・見出し
    private String title;
    //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
    private String link;
    //予報の発表日時
    private String publicTime;
    //天気概況文
    private Description description;
    //府県天気予報の予報日毎の配列
    private List<Forecasts> forecasts;
    //ピンポイント予報の発表地点の配列
    private List<Link> pinpointLocation;
    //コピーライト
    private Copyright copyright;

    public static class Location {
        //地方名（例・九州地方）
        private String area;
        //都道府県名（例・福岡県）
        private String pref;
        //1次細分区名（例・八幡）
        private String city;
    }

    public static class Description {
        //天気概況文
        private String text;
        //天気概況文の発表時刻
        private String publicTime;
    }

    public static class Forecasts {
        //予報日
        private String date;
        //予報日(今日、明日、明後日のいずれか)
        private String dateLabel;
        //天気（晴れ、曇り、雨など）
        private String telop;
        //画像
        private Image image;
        //気温
        private Temperature temperature;
    }

    public static class Image {
        //天気（晴れ、曇り、雨など）
        private String title;
        //リクエストされたデータの地域に該当するlivedoor 天気情報のURL
        private String link;
        //天気アイコンのURL
        private String url;
        //天気アイコンの幅
        private Integer width;
        //天気アイコンの高さ
        private Integer height;
    }

    public static class Temperature {
        //最高気温
        private TemperatureUnit max;
        //最低気温
        private TemperatureUnit min;
    }

    public static class TemperatureUnit {
        //摂氏
        private Float celsius;
        //華氏
        private Float fahrenheit;
    }

    public static class Link {
        //市区町村名
        private String name;
        //対応するlivedoor 天気情報のURL
        private String link;
    }

    public static class Copyright {
        //コピーライトの文言
        private String title;
        //livedoor 天気情報のURL
        private String link;
        //livedoor 天気情報へのURL、アイコンなど
        private Image image;
        //livedoor 天気情報で使用している気象データの配信元
        private List<Link> provider;
    }

}
