package com.kazakago.cleanarchitecture.presentation.global.livedata.nonnulllivedata;

import androidx.annotation.NonNull;

public interface NonNullObserver<T> {
    void onChanged(@NonNull T t);
}
