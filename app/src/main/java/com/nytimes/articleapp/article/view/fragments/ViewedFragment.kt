package com.nytimes.articleapp.article.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.articleapp.article.App
import com.nytimes.articleapp.article.R
import com.nytimes.articleapp.article.data.models.viewed.ResultView
import com.nytimes.articleapp.article.view.activities.DetailActivity
import com.nytimes.articleapp.article.view.adapters.ViewedAdapter
import com.nytimes.articleapp.article.viewModels.ViewModelFactory
import com.nytimes.articleapp.article.viewModels.ViewedViewModel
import kotlinx.android.synthetic.main.fragment_viewed.progress
import kotlinx.android.synthetic.main.fragment_viewed.recyclerView

class ViewedFragment : Fragment() ,ViewedAdapter.CallBack{

    private lateinit var viewModel: ViewedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_viewed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity!!.applicationContext as App) {
            viewModel = androidx.lifecycle.ViewModelProviders.of(this@ViewedFragment,
                ViewModelFactory(this)
            ).get(ViewedViewModel::class.java)
        }



        initRecycler()
        initObserver()

        viewModel.getAllViewed()
    }

    private fun initRecycler(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
    }

    private fun initObserver() {

        viewModel.viewedResult.observe(this, Observer<List<ResultView>>() {
            progress.visibility = View.GONE
            recyclerView.adapter = ViewedAdapter(it,this,"main")
        })


        viewModel.viewedLoader.observe(this,Observer{
             progress.visibility = View.GONE

        })


        viewModel.viewedError.observe(this, Observer {
             progress.visibility = View.GONE
        })
    }

    override fun onItemListener(url: String) {
        val intent = DetailActivity.newIntent(activity!!)
        intent.putExtra("url",url)
        startActivity(intent)
    }

    override fun onSavedListener(result: ResultView, state: Boolean) {
        viewModel.saveItem(result,state)
    }

}