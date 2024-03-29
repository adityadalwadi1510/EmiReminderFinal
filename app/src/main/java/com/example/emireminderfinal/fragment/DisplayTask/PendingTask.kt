package com.example.emireminderfinal.fragment.DisplayTask

import android.os.Bundle
import android.view.*
import android.widget.ListAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.emireminderfinal.R
import com.example.emireminderfinal.model.Emi
import com.example.emireminderfinal.viewmodel.EmiViewModel
import kotlinx.android.synthetic.main.fragment_pending_task.view.*

class PendingTask : Fragment(), com.example.emireminderfinal.fragment.list.ListAdapter.sample {

    private lateinit var mEmiViewModel: EmiViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_pending_task, container, false)
        val adapter = com.example.emireminderfinal.fragment.list.ListAdapter(this)
        val recyclerViewPendingTask = view.recyclerViewPendingTask
        recyclerViewPendingTask.adapter = adapter
        recyclerViewPendingTask.layoutManager = LinearLayoutManager(requireContext())

        mEmiViewModel = ViewModelProvider(this).get(EmiViewModel::class.java)
        mEmiViewModel.readAllData.observe(viewLifecycleOwner, Observer { emi ->
            adapter.setData(emi)
        })



        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete) {
            deleteEmi()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteEmi() {
        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            mEmiViewModel.deleteAll()
            Toast.makeText(
                requireContext(),
                "Successfully Removed Everythings",
                Toast.LENGTH_LONG
            ).show()

        }
        builder.setNegativeButton("No") { _, _ ->


        }

        builder.setTitle("Delete Everythings?")
        builder.setMessage("Are you sure want to delete Everythings?")
        builder.create().show()
    }

    override fun onLongClick(emi: Emi) {


        val builder = androidx.appcompat.app.AlertDialog.Builder(requireContext())
        builder.setPositiveButton("Yes") { _, _ ->
            val completed = emi.completed + 1;
            var totalCompleted = 0;
            if (emi.emiDuration.toInt() == completed)
                totalCompleted = 1
            val updateEmi = Emi(
                emi.id,
                emi.emiName,
                emi.emiRs,
                emi.dueDate,
                emi.category,
                emi.descrition,
                emi.emiDuration,
                completed,
                totalCompleted
            )
            mEmiViewModel.updateEmi(updateEmi)
            Toast.makeText(
                requireContext(),
                "Successfully updated",
                Toast.LENGTH_LONG
            ).show()

        }
        builder.setNegativeButton("No") { _, _ ->
        }

        builder.setTitle("Are you sure?")
        builder.setMessage(
            "Are you sure want to Complete this emi?\nYou have total this emi months:-${emi.emiDuration}.\nYou have Complete total this emi months:-" +
                    "${emi.completed}.\nYou have remain of month for this item:-${emi.emiDuration.toInt() - emi.completed}."
        )
        builder.create().show()
    }
}