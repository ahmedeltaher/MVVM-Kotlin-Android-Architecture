package com.task.data

import com.task.data.remote.Data

/**
 * Created by ahmedeltaher on 3/23/17.
 */

interface DataSource {
    fun requestNews(): Data?
}
