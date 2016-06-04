package com.ignis.android_cleanarchitecture.presentation.presenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.databinding.ObservableField;
import android.net.Uri;
import android.view.View;

import com.ignis.android_cleanarchitecture.domain.model.LinkModel;

/**
 * PinpointLocation ViewModel
 * <p>
 * Created by tamura_k on 2016/06/01.
 */
public class PinpointLocationViewModel {

    private Context context;
    private LinkModel location;
    public ObservableField<String> name;

    public PinpointLocationViewModel(Context context, LinkModel location) {
        this.context = context;
        this.location = location;
        this.name = new ObservableField<>(location.getName());
    }

    public void onItemClick(View view) {
        toPinpointLocation(location.getLink());
    }

    private void toPinpointLocation(String pinpointLocationUrl) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(pinpointLocationUrl));
        context.startActivity(intent);
    }

}
