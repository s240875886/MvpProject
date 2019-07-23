package com.thp.mvp.networking;

import android.util.Log;

import com.thp.mvp.model.NewsInfo;

import java.util.List;
import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by thp on 2019/6/1
 */
public class NetService {
    private final ServiceApi mApi;

    public NetService(RetrofitClient restClient) {
        mApi = restClient.create(ServiceApi.class);
    }
    public void getNewsList(String type, String id, int startPage){
        mApi.getNewsList("headline", "T1348647909107", 1 * 20)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Map<String, List<NewsInfo>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Map<String, List<NewsInfo>> stringListMap) {
                        Log.e("test",stringListMap.toString());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
