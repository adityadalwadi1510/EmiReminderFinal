package com.example.emireminderfinal.fragment.DisplayTask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.emireminderfinal.R
import com.example.emireminderfinal.viewmodel.EmiViewModel
import kotlinx.android.synthetic.main.fragment_completed_task.view.*


class CompletedTask : Fragment() {
    private lateinit var mEmiViewModel: EmiViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_completed_task, container, false)
        val adapter = CompleteTaskAdapater()
        val recyclerView = view.recyclerViewCompletedTask
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mEmiViewModel = ViewModelProvider(this).get(EmiViewModel::class.java)
        mEmiViewModel.readAllDataCompleted.observe(viewLifecycleOwner, Observer { emi ->
            adapter.setData(emi)
        })

        return view
    }

}