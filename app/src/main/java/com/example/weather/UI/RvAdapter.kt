package com.example.weather.UI

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.Model.Json.Forecast.ForecastModel
import com.example.weather.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_recycler.view.*

class RvAdapter : RecyclerView.Adapter<RvAdapter.RvVholder>() {
    private val list = arrayListOf<ForecastModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvVholder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler,  parent,false)
        return RvVholder(view)
    }

    fun update(list: List<ForecastModel>?) {
        if (list!= null){
            this.list.clear()
            this.list.addAll(list)
            notifyDataSetChanged()
        }
    }

    override fun onBindViewHolder(holder: RvVholder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    class RvVholder(v : View) : RecyclerView.ViewHolder(v){
        fun bind(data: ForecastModel) {
            itemView.tv.text =  data.temp.day.toInt().toString()
            itemView.Rt.text=data.temp.max.toInt().toString()
            itemView.rvTv.text=data.temp.min.toInt().toString()
            val image = data.weather.first().icon
            Picasso.get().load("http://openweathermap.org/img/w/$image.png").into(itemView.imgRV)

        }
    }
}