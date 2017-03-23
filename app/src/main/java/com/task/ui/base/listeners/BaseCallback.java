package com.task.ui.base.listeners;

import com.task.data.remote.dto.NewsModel;

/**
 * Created by ahmedeltaher on 3/22/17.
 */

public interface BaseCallback {
    void onSuccess(NewsModel newsModel);

    void onFail();
}
