package com.example.airteltest.base

import android.graphics.Color
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel>:DaggerAppCompatActivity() {

    var viewDataBinding: T? = null
    private var baseViewModel: V? = null

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    abstract val bindingVariable: Int
    /**
     * @return layout resource id
     */
    @get:LayoutRes
    abstract val layoutId: Int
    /**
     * Override for set view model
     *
     * @return view model instance
     */
    abstract val viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        setUpTransparentStatusBar()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }


    private fun setUpTransparentStatusBar() {
        window.apply {
            clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            statusBarColor=Color.TRANSPARENT
        }
    }

    private fun performDataBinding() {
        viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
        this.baseViewModel = if (baseViewModel == null) viewModel else baseViewModel
        viewDataBinding?.setVariable(bindingVariable, baseViewModel)
        viewDataBinding?.executePendingBindings()
    }
}