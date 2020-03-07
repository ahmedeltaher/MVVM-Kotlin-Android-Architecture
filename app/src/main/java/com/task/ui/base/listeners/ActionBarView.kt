package com.task.ui.base.listeners

/**
 * Created by AhmedEltaher
 */

interface ActionBarView {

    fun setUpIconVisibility(visible: Boolean)

    fun setTitle(titleKey: String)

    fun setSettingsIconVisibility(visibility: Boolean)

    fun setRefreshVisibility(visibility: Boolean)
}
