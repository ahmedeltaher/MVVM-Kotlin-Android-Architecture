package com.task.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.task.R
import com.task.ui.base.listeners.ActionBarView
import com.task.ui.base.listeners.BaseView
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.toolbar.*

/**
 * Created by AhmedEltaher on 5/12/2016
 */


abstract class BaseActivity : AppCompatActivity(), BaseView, ActionBarView {

    protected lateinit var baseViewModel: BaseViewModel

    abstract val layoutId: Int

    protected abstract fun initializeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initializeToolbar()
        initializeViewModel()
    }

    private fun initializeToolbar() {
        if (toolbar != null) {
            setSupportActionBar(toolbar)
            supportActionBar?.title = ""
        }
    }

    override fun setUpIconVisibility(visible: Boolean) {
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(visible)
    }

    override fun setTitle(titleKey: String) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            val title = findViewById<TextView>(R.id.txt_toolbar_title)
            title?.text = titleKey
        }
    }

    override fun setSettingsIconVisibility(visibility: Boolean) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            val icon = findViewById<ImageView>(R.id.ic_toolbar_setting)
            icon?.visibility = if (visibility) VISIBLE else GONE
        }
    }

    override fun setRefreshVisibility(visibility: Boolean) {
        val actionBar = supportActionBar
        if (actionBar != null) {
            val icon = findViewById<ImageView>(R.id.ic_toolbar_refresh)
            icon?.visibility = if (visibility) VISIBLE else GONE
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
