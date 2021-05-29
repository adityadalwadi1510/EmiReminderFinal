package com.example.emireminderfinal.fragment.update

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.emireminderfinal.R
import com.example.emireminderfinal.model.Emi
import com.example.emireminderfinal.viewmodel.EmiViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()

    private lateinit var mEmiViewModel: EmiViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mEmiViewModel = ViewModelProvider(this).get(EmiViewModel::class.java)

        view.edt_emi_des_updt.setText(args.currentUser.descrition)
        view.edt_emi_name_updt.setText(args.currentUser.emiName)
        view.edt_emi_rs_updt.setText(args.currentUser.emiRs)
        view.edt_emi_duration_updt.setText(args.currentUser.emiDuration)
        view.txt_due_date_updt.setText(args.currentUser.dueDate)
        view.spn_category_updt.setSelection(0)


        view.btn_add_emi_updt.setOnClickListener {
            updateItem()
        }
        setHasOptionsMenu(true)
        return view;
    }

    private fun updateItem() {
        val emiName = edt_emi_name_updt.text.toString()
        val emides = edt_emi_des_updt.text.toString()
        val emirs = edt_emi_rs_updt.text.toString()
        val emiduration = edt_emi_duration_updt.text.toString()
        val emiduedate = txt_due_date_updt.text.toString()
        val emicategory = spn_category_updt.selectedItem.toString()
        if (inputCheck(emiName, emirs, emiduration, emiduedate, emicategory)) {
            val updateEmi = Emi(
                args.currentUser.id,
                emiName,
                emirs,
                emiduedate,
                emicategory,
                emides,
                emiduration,
                args.currentUser.completed,
                0
            )
            mEmiViewModel.updateEmi(updateEmi)
            Toast.makeText(requireContext(), "Successfully Updated", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)

        } else {
            Toast.makeText(requireContext(), "Some Data are emibious", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(
        emiName: String,
        emiRs: String,
        emiDuration: String,
        emiDuaDate: String,
        emiCategory: String
    ): Boolean {
        if (emiName.isEmpty() || emiRs.isEmpty() || emiDuration.isEmpty() || emiDuaDate.equals("Due Date") || emiCategory.equals(
                "SELECT CATEGORY"
            )
        ) {
            return false
        }
        return true
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
            mEmiViewModel.deleteEmi(args.currentUser)
            Toast.makeText(
                requireContext(),
                "Successfully Removed ${args.currentUser.emiName}",
                Toast.LENGTH_LONG
            ).show()
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        }
        builder.setNegativeButton("No") { _, _ ->


        }

        builder.setTitle("Delete ${args.currentUser.emiName}")
        builder.setMessage("Are you sure want to delete ${args.currentUser.emiName}")
        builder.create().show()
    }
}




