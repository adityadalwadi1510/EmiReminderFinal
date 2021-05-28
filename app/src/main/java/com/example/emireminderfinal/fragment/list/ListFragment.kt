package com.example.emireminderfinal.fragment.list

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.emireminderfinal.viewmodel.EmiViewModel
import com.example.emireminderfinal.R
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {

    private lateinit var mEmiViewModel: EmiViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_list, container, false)

        val adapter = ListAdapter()
        val recyclerView = view.recyclerView
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mEmiViewModel = ViewModelProvider(this).get(EmiViewModel::class.java)
        mEmiViewModel.readAllData.observe(viewLifecycleOwner, Observer { emi ->
            adapter.setData(emi)
        })


        view.floating_add_button.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        setHasOptionsMenu(true)
        return view;
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu,menu)
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

}