package com.example.emireminderfinal.fragment.list

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

import com.example.emireminderfinal.model.Emi
import com.example.emireminderfinal.R
import kotlinx.android.synthetic.main.custom_row.view.*
import java.text.SimpleDateFormat
import java.util.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.EmiViewHolder>() {

    private var emiList = emptyList<Emi>()
    val month = arrayOf(
        "Jan",
        "Feb",
        "Mar",
        "Apr",
        "May",
        "June",
        "July",
        "Aug",
        "Sept",
        "Oct",
        "Nov",
        "Dec"
    )
    val month30 = arrayOf(
        "Apr",
        "June",
        "Sept",
        "Nov"
    )
    val month31 = arrayOf(
        "Jan",
        "Mar",
        "May",
        "July",
        "Aug",
        "Oct",
        "Dec"
    )


    class EmiViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmiViewHolder {
        return EmiViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return emiList.size
    }

    override fun onBindViewHolder(holder: EmiViewHolder, position: Int) {
        val sdf = SimpleDateFormat("dd-MM-yyyy")
        val currentDate = sdf.format(Date()).toString()
        val currentDay = currentDate.substring(0, 2).toInt()
        val currentMonth = currentDate.substring(3, 5).toInt()
        val currentItem = emiList[position]
        holder.itemView.card_view.setOnClickListener {
            val action=ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
        val date = currentItem.dueDate.substring(0, 2).toInt()
        val month1 = currentItem.dueDate.substring(3, 5)
        var monthActual = month1.toInt()
        var remainDay = 0;
        --monthActual
        if (currentMonth >= monthActual) {
            if (currentDay > date) {
                ++monthActual;
                if (month30.contains(month[monthActual])) {
                    remainDay = (30 - currentDay) + date;
                } else if (month31.contains(month[monthActual])) {
                    remainDay = (31 - currentDay) + date;
                } else {
                    remainDay = (28 - currentDay) + date;
                }
            } else {
                if (month30.contains(month[monthActual])) {
                    remainDay = (date - currentDay)
                } else if (month31.contains(month[monthActual])) {
                    remainDay = (date - currentDay)
                } else {
                    remainDay = (date - currentDay)
                }
            }
        }
        if (remainDay > 15) {
            holder.itemView.card_view.setCardBackgroundColor(Color.parseColor("#F3EBEB"))
            holder.itemView.txt_date.setTextColor(Color.parseColor("#403E3E"))
            holder.itemView.txt_emi_dis.setTextColor(Color.parseColor("#403E3E"))
            holder.itemView.txt_emi_name.setTextColor(Color.parseColor("#403E3E"))
            holder.itemView.txt_emi_rs.setTextColor(Color.parseColor("#403E3E"))
        } else if (remainDay > 7) {
            holder.itemView.card_view.setCardBackgroundColor(Color.parseColor("#358597"))
            holder.itemView.txt_date.setTextColor(Color.parseColor("#F4A896"))
            holder.itemView.txt_emi_dis.setTextColor(Color.parseColor("#F4A896"))
            holder.itemView.txt_emi_name.setTextColor(Color.parseColor("#F4A896"))
            holder.itemView.txt_emi_rs.setTextColor(Color.parseColor("#F4A896"))
        } else {
            holder.itemView.card_view.setCardBackgroundColor(Color.parseColor("#F96167"))
            holder.itemView.txt_date.setTextColor(Color.parseColor("#FCE77D"))
            holder.itemView.txt_emi_dis.setTextColor(Color.parseColor("#FCE77D"))
            holder.itemView.txt_emi_name.setTextColor(Color.parseColor("#FCE77D"))
            holder.itemView.txt_emi_rs.setTextColor(Color.parseColor("#FCE77D"))
        }

        holder.itemView.txt_date.text = "$date\n${month[monthActual]}"
        holder.itemView.txt_emi_dis.text = "${currentItem.descrition}\nRemaining Day :- $remainDay"
        holder.itemView.txt_emi_name.text = currentItem.emiName
        holder.itemView.txt_emi_rs.text = currentItem.emiRs

    }

    fun setData(emi: List<Emi>) {
        this.emiList = emi
        notifyDataSetChanged()
    }
}