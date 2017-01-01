package com.task.data.remote;

import android.accounts.NetworkErrorException;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.task.App;
import com.task.data.remote.dto.NewsModel;
import com.task.data.remote.service.NewsService;
import com.task.utils.Constants;
import com.task.utils.L;

import java.io.IOException;

import javax.inject.Inject;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

import static com.task.data.remote.ServiceError.NETWORK_ERROR;
import static com.task.utils.Constants.ERROR_UNDEFINED;
import static com.task.utils.NetworkUtils.isConnected;
import static com.task.utils.ObjectUtil.isNull;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class ApiRepository {
    private ServiceGenerator serviceGenerator;

    @Inject
    public ApiRepository(ServiceGenerator serviceGenerator) {
        this.serviceGenerator = serviceGenerator;
    }

    public Observable getNews() {

        Observable<NewsModel> newsObservable = Observable.create(new Observable.OnSubscribe<NewsModel>() {
            @Override
            public void call(Subscriber<? super NewsModel> subscriber) {
                if (!isConnected(App.getContext())) {
                    Exception e = new NetworkErrorException();
                    subscriber.onError(e);
                } else {
                    NewsService newsService = serviceGenerator.createService(NewsService.class, Constants.BASE_URL);
                    ServiceResponse serviceResponse = processCall(newsService.fetchNews());
                    NewsModel newsModel = (NewsModel) serviceResponse.getData();
                    subscriber.onNext(newsModel);
                    subscriber.onCompleted();
                }
            }
        });
        return newsObservable;
    }

    //Process the calls
    @NonNull
    private ServiceResponse processCall(Call call) {
        if (!isConnected(App.getContext())) {
            return new ServiceResponse(new ServiceError());
        }
        return processResponse(call, false);
    }

    @NonNull
    private ServiceResponse processResponse(Call call, boolean isVoid) {
        try {
            retrofit2.Response response = call.execute();
            Gson gson = new Gson();
            L.json(NewsModel.class.getName(), gson.toJson(response.body()));
            if (isNull(response)) {
                //Extra check in case internet is disconnected in between or no proper response
                // received from backend
                return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
            }
            int responseCode = response.code();
            if (response.isSuccessful()) {
                return new ServiceResponse(responseCode, isVoid ? null : response.body());
            } else {
                ServiceError ServiceError;
                ServiceError = new ServiceError(response.message(), responseCode);
                return new ServiceResponse(ServiceError);
            }
        } catch (IOException e) {
            return new ServiceResponse(new ServiceError(NETWORK_ERROR, ERROR_UNDEFINED));
        }
    }
}
