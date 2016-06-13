package com.weathercock.android_cleanarchitecture.presentation.view.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

/**
 * Perform 'onItemSelected' event, only user selected Spinner.
 */
public class UserSelectSpinner extends Spinner {

    private boolean isUserOpen = false;
    private OnItemSelectedListener onItemSelectedListener;

    public UserSelectSpinner(Context context) {
        super(context);
        registerEvents();
    }

    public UserSelectSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        registerEvents();
    }

    public UserSelectSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        registerEvents();
    }

    private void registerEvents() {
        super.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (isUserOpen && onItemSelectedListener != null) {
                    onItemSelectedListener.onItemSelected(parent, view, position, id);
                    isUserOpen = false;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (onItemSelectedListener != null) {
                    onItemSelectedListener.onNothingSelected(parent);
                }
            }
        });
    }

    @Override
    public void setOnItemSelectedListener(OnItemSelectedListener listener) {
        this.onItemSelectedListener = listener;
    }

    @Override
    public boolean performClick() {
        isUserOpen = true;
        return super.performClick();
    }

}