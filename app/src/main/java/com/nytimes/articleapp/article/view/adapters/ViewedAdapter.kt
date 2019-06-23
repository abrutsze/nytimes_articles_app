package com.nytimes.articleapp.article.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nytimes.articleapp.article.R
import com.nytimes.articleapp.article.data.models.viewed.ResultView
import com.bumptech.glide.Glide

class ViewedAdapter  (private val results: List<ResultView>, private val callback: CallBack , private val type:String) : RecyclerView.Adapter<ViewedAdapter.ViewHolder>() {

    private var mList = ArrayList<ResultView>()

    init {
        this.mList = results as java.util.ArrayList<ResultView>
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val v  = LayoutInflater.from(viewGroup.context).
            inflate(R.layout.item_viewed,viewGroup,false);
        return ViewHolder(v)
    }


    fun addItems(results: List<ResultView>) {
        mList.addAll(results)
    }
    override fun getItemCount(): Int = mList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView.context).load(mList[position].media?.get(0)?.mediaMetadata?.get(0)?.url).centerCrop().into(holder.image)
        holder.tvNumber.text = mList[position].views.toString() + "."
        holder.tvTitle.text = mList[position].title
        holder.tvDate.text = mList[position].published_date
        holder.tvDesc.text = mList[position].abstract

        var saved : Boolean = mList[position].saved!!

        if (saved) holder.tvBook.setImageResource(R.drawable.ic_bookmark_black_24dp)


        holder.itemView.setOnClickListener {
            callback.onItemListener(mList[position].url!!)
        }

        holder.tvBook.setOnClickListener {
            saved = if (saved){
                holder.tvBook.setImageResource(R.drawable.ic_bookmark_border_black_24dp)
                false
            } else{
                holder.tvBook.setImageResource(R.drawable.ic_bookmark_black_24dp)
                true

            }

            callback.onSavedListener(mList[position] , saved)

        }
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvDate: TextView = itemView.findViewById(R.id.tvDate)
        var tvNumber: TextView = itemView.findViewById(R.id.tvNumber)
        var tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        var tvDesc: TextView = itemView.findViewById(R.id.tvDesc)
        var image: ImageView = itemView.findViewById(R.id.image)
        var tvBook: ImageView = itemView.findViewById(R.id.tvBook)
    }

    interface CallBack{
        fun onItemListener(url:String)
        fun onSavedListener(result: ResultView,state:Boolean)

    }

}