package com.task.data;

import com.task.data.local.LocalRepository;
import com.task.data.remote.RemoteRepository;
import com.task.data.remote.dto.NewsModel;

import javax.inject.Inject;

import io.reactivex.Single;


/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class DataRepository implements DataSource {
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    @Inject
    public DataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    @Override
    public Single<NewsModel> requestNews() {
        return remoteRepository.getNews();
    }
}
