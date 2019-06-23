package com.nytimes.articleapp.article.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.articleapp.article.App
import com.nytimes.articleapp.article.R
import com.nytimes.articleapp.article.data.models.shared.ResultShare
import com.nytimes.articleapp.article.view.activities.DetailActivity
import com.nytimes.articleapp.article.view.adapters.SharedAdapter
import com.nytimes.articleapp.article.viewModels.SharedViewModel
import com.nytimes.articleapp.article.viewModels.ViewModelFactory
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_shared.progress
import kotlinx.android.synthetic.main.fragment_shared.recyclerView


class ShareSavedFragment : Fragment(), SharedAdapter.CallBack{

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_shared, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity!!.applicationContext as App) {
            viewModel = androidx.lifecycle.ViewModelProviders.of(this@ShareSavedFragment,
                ViewModelFactory(this)
            ).get(SharedViewModel::class.java)
        }



        initRecycler()
        initObserver()

        viewModel.getAllShareSaved()
    }

    private fun initRecycler(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
    }

    private fun initObserver() {

        viewModel.sharedResult.observe(this, Observer<List<ResultShare>>() {
            progress.visibility = View.GONE
            recyclerView.adapter = SharedAdapter(it,this,"saved")
        })


        viewModel.sharedLoader.observe(this,Observer{
             progress.visibility = View.GONE

        })


        viewModel.sharedError.observe(this, Observer {
             progress.visibility = View.GONE
        })
    }


    override fun onItemListener(url: String) {
        val intent = DetailActivity.newIntent(activity!!)
        intent.putExtra("url",url)
        startActivity(intent)
    }

    override fun onSavedListener(result: ResultShare, state: Boolean) {
        viewModel.saveItem(result,state)
    }
}