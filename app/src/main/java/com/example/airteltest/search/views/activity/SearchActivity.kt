package com.example.airteltest.search.views.activity

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.airteltest.BR
import com.example.airteltest.R
import com.example.airteltest.base.BaseActivity
import com.example.airteltest.databinding.ActivitySearchBinding
import com.example.airteltest.search.viewmodel.SearchViewModel
import com.example.airteltest.search.views.adapter.SearchAdapter
import kotlinx.android.synthetic.main.activity_search.*
import javax.inject.Inject

class SearchActivity : BaseActivity<ActivitySearchBinding, SearchViewModel>(), View.OnClickListener {
    override val bindingVariable: Int
        get() = BR.viewModel
    override val layoutId: Int
        get() = R.layout.activity_search

    private var activitySearchBinding: ActivitySearchBinding? = null

    private val searchAdapter by lazy { SearchAdapter(this) }

    @Inject
    override lateinit var  viewModel: SearchViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activitySearchBinding = viewDataBinding
        initializeViews()
        setMutableLiveData()
    }

    private fun initializeViews(){
        val layoutManager=LinearLayoutManager(this)
        activitySearchBinding?.rvAddress?.apply {
            this.layoutManager=layoutManager
            adapter=searchAdapter
            setHasFixedSize(false)
        }
        imgBack?.setOnClickListener(this)
    }

    private fun setMutableLiveData(){
         viewModel.addressListResponseLiveData.observe(this, Observer {
                searchAdapter.updateData(it)
         })
    }

    override fun onClick(v: View?) {
        finish()
    }




}
