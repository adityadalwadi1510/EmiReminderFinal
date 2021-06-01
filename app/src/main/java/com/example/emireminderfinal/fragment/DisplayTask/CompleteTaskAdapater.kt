package com.example.emireminderfinal.fragment.DisplayTask

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.emireminderfinal.R
import com.example.emireminderfinal.model.Emi
import kotlinx.android.synthetic.main.custom_row.view.*

class CompleteTaskAdapater :
    RecyclerView.Adapter<CompleteTaskAdapater.EmiViewHolderComplete>() {


    private var emiList = emptyList<Emi>()

    inner class EmiViewHolderComplete(itemView: View) : RecyclerView.ViewHolder(itemView) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EmiViewHolderComplete {
        return EmiViewHolderComplete(
            LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return emiList.size
    }

    override fun onBindViewHolder(holder: EmiViewHolderComplete, position: Int) {
        val currentItem = emiList[position]
        holder.itemView.card_view.setCardBackgroundColor(Color.parseColor("#F3EBEB"))
        holder.itemView.txt_date.setTextColor(Color.parseColor("#403E3E"))
        holder.itemView.txt_emi_dis.setTextColor(Color.parseColor("#403E3E"))
        holder.itemView.txt_emi_name.setTextColor(Color.parseColor("#403E3E"))
        holder.itemView.txt_emi_rs.setTextColor(Color.parseColor("#403E3E"))
        holder.itemView.txt_date.visibility = View.GONE
        holder.itemView.img_check_completed.visibility = View.VISIBLE
        holder.itemView.txt_emi_dis.text =
            "${currentItem.descrition}\nYou Completed this Emi on Time"
        holder.itemView.txt_emi_name.text = currentItem.emiName
        holder.itemView.txt_emi_rs.text = currentItem.emiRs

    }

    fun setData(emi: List<Emi>) {
        this.emiList = emi
        notifyDataSetChanged()
    }
}