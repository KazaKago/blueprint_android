package com.kazakago.cleanarchitecture.presentation.livedata.nonnulllivedata;

import androidx.annotation.NonNull;

public interface NonNullObserver<T> {
    void onChanged(@NonNull T t);
}
