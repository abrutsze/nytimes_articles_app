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
import com.nytimes.articleapp.article.data.models.emailed.ResultEmail
import com.nytimes.articleapp.article.view.activities.DetailActivity
import com.nytimes.articleapp.article.view.adapters.EmailedAdapter
import com.nytimes.articleapp.article.viewModels.EmailedViewModel
import com.nytimes.articleapp.article.viewModels.ViewModelFactory
import kotlinx.android.synthetic.main.fragment_emailed.*

class EmailedFragment() :Fragment(),EmailedAdapter.CallBack {


    private lateinit var viewModel: EmailedViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_emailed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(activity!!.applicationContext as App) {
            viewModel = androidx.lifecycle.ViewModelProviders.of(this@EmailedFragment,
                ViewModelFactory(this)
            ).get(EmailedViewModel::class.java)
        }



        initRecycler()
        initObserver()

        viewModel.getAllEmailed()
    }

    private fun initRecycler(){
        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
        }
    }

    private fun initObserver() {

        viewModel.emailedResult.observe(this, Observer<List<ResultEmail>>() {
            progress.visibility = View.GONE
            recyclerView.adapter =EmailedAdapter(it,this,"main")
        })


        viewModel.emailedLoader.observe(this,Observer{
             progress.visibility = View.GONE

        })


        viewModel.emailedError.observe(this, Observer {
             progress.visibility = View.GONE
        })
    }


    override fun onItemListener(url: String) {
        val intent = DetailActivity.newIntent(activity!!)
        intent.putExtra("url",url)
        startActivity(intent)
    }


    override fun onSavedListener(result: ResultEmail , state:Boolean) {
        viewModel.saveItem(result,state)
    }
}