package com.task.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.task.databinding.ToolbarBinding
import com.task.ui.base.listeners.ActionBarView
import com.task.ui.base.listeners.BaseView
import dagger.android.AndroidInjection

/**
 * Created by AhmedEltaher
 */


abstract class BaseActivity : AppCompatActivity(), BaseView, ActionBarView {

    protected lateinit var baseViewModel: BaseViewModel

    protected lateinit var toolbarBinding: ToolbarBinding

    protected abstract fun initializeViewModel()
    abstract fun observeViewModel()
    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewBinding()
        initializeToolbar()
        initializeViewModel()
        observeViewModel()
    }

    private fun initializeToolbar() {
        toolbarBinding = ToolbarBinding.inflate(layoutInflater)
        toolbarBinding.txtToolbarTitle.text = ""
    }
    override fun setUpIconVisibility(visible: Boolean) {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(visible)
    }

    override fun setTitle(titleKey: String) {
        toolbarBinding.txtToolbarTitle.text = titleKey
    }

    override fun setSettingsIconVisibility(visibility: Boolean) {
        toolbarBinding.icToolbarSetting.visibility = if (visibility) VISIBLE else GONE
    }

    override fun setRefreshVisibility(visibility: Boolean) {
        toolbarBinding.icToolbarRefresh.visibility = if (visibility) VISIBLE else GONE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
