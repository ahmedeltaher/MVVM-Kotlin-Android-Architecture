package com.task.ui.base


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.task.R
import com.task.ui.base.listeners.BaseView

/**
 * Created by AhmedEltaher on 5/12/2016
 */


abstract class BaseFragment : Fragment(), BaseView {

    protected var presenter: Presenter<*>? = null

    abstract val layoutId: Int

    private val toolbarTitleKey: String? = null

    protected abstract fun initializeDagger()

    protected abstract fun initializePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDagger()
        initializePresenter()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(layoutId, container, false)
        presenter?.initialize(arguments)
        return view
    }

    override fun onStart() {
        super.onStart()
        presenter?.start()
    }

    override fun onStop() {
        super.onStop()
        presenter?.finalizeView()
    }

    fun setTitle(title: String) {
        val actionBar = (activity as BaseActivity).supportActionBar
        if (actionBar != null) {
            val titleTextView = activity?.findViewById<TextView>(R.id.txt_toolbar_title)
            if (!title.isEmpty()) {
                titleTextView?.text = title
            }
        }
    }
}
