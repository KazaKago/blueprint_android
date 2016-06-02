package com.ignis.android_cleanarchitecture.presentation.presenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.ignis.android_cleanarchitecture.CleanApplication;
import com.ignis.android_cleanarchitecture.domain.model.ProfileModel;
import com.ignis.android_cleanarchitecture.domain.usecase.ProfileUseCase;
import com.ignis.android_cleanarchitecture.presentation.listener.fragment.MainFragmentListener;
import com.ignis.android_cleanarchitecture.presentation.presenter.adapter.ProfileViewModel;
import com.ignis.android_cleanarchitecture.presentation.view.activity.AboutActivity;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Main Fragment ViewModel
 *
 * @author Kensuke
 */
public class MainFragmentViewModel {

    @Inject
    public ProfileUseCase profileUseCase;

    private Context context;
    private MainFragmentListener listener;
    private CompositeSubscription subscriptions;

    public MainFragmentViewModel(Context context, MainFragmentListener listener) {
        CleanApplication.getInstance(context).getApplicationComponent().inject(this);
        this.context = context;
        this.listener = listener;
    }

    public void onStart() {
        subscriptions = new CompositeSubscription();
    }

    public void onStop() {
        subscriptions.unsubscribe();
    }

    public void onClickAbout(View view) {
        toAboutActivity();
    }

    /**
     * プロフィールリストを取得する
     *
     * @return
     */
    public void getProfileList() {
        subscriptions.add(Observable
                .create((Observable.OnSubscribe<List<ProfileModel>>) subscriber -> {
                    //Profile Modelリストで一括で取得
                    subscriber.onNext(profileUseCase.getProfileList());
                    subscriber.onCompleted();
                })
                .flatMap(Observable::from) //Profile Modelリストを分解
                .map(profileModel -> new ProfileViewModel(context, profileModel)) //Profile Modelを1つずつProfile ViewModelに変換
                .toList() //Profile ViewModelをリスト化
                .subscribeOn(Schedulers.newThread()) //実行はバックグラウンドスレッドで動作
                .observeOn(AndroidSchedulers.mainThread()) //結果はUIスレッドで取得
                .subscribe(new Subscriber<List<ProfileViewModel>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<ProfileViewModel> profileViewModelList) {
                        listener.onGetProfileList(profileViewModelList);
                    }
                }));
    }

    /**
     * About画面をブラウザで開く
     */
    private void toAboutActivity() {
        Intent intent = AboutActivity.newInstance(context);
        context.startActivity(intent);
    }

}
