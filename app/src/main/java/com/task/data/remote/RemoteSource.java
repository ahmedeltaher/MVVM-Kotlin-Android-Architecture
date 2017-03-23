package com.task.data.remote;

import io.reactivex.Observable;

/**
 * Created by ahmedeltaher on 3/23/17.
 */

interface RemoteSource {
    Observable getNews();
}
