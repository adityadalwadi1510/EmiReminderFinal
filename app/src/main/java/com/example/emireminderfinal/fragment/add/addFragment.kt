package com.example.emireminderfinal.fragment.add

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.emireminderfinal.model.Emi
import com.example.emireminderfinal.viewmodel.EmiViewModel
import com.example.emireminderfinal.R
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*
import java.util.*


class addFragment : Fragment() {

    private lateinit var mEmiViewModel: EmiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mEmiViewModel = ViewModelProvider(this).get(EmiViewModel::class.java)
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        view.txt_due_date.setOnClickListener {
            val dpd = DatePickerDialog(
                requireContext(),
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    var dayOfMonthDummy = "$dayOfMonth"
                    Log.d("dummy", dayOfMonthDummy)
                    if (dayOfMonthDummy.length == 1) {
                        dayOfMonthDummy = "0$dayOfMonth"
                        Log.d("dummy", dayOfMonthDummy)
                    }

                    var monthOfYearDummy = "$monthOfYear"
                    if (monthOfYearDummy.length == 1) {
                        monthOfYearDummy = "0$monthOfYearDummy"
                    }

                    txt_due_date.text = "$dayOfMonthDummy-$monthOfYearDummy-$year"
                },
                year,
                month,
                day
            )
            dpd.show()

        }

        view.btn_add_emi.setOnClickListener {
            insertDataToDatabase();
        }
        return view;
    }

    private fun insertDataToDatabase() {
        val emiName = edt_emi_name.text.toString()
        val emiRs = edt_emi_rs.text.toString()
        val emiDuration = edt_emi_duration.text.toString()
        val emiDueDate = txt_due_date.text.toString()
        val emiCategory = spn_category.selectedItem.toString()
        val emiDis = edt_emi_des.text.toString()
        if (inputCheck(emiName, emiRs, emiDuration, emiDueDate, emiCategory)) {
            val emi = Emi(
                0,
                emiName,
                emiRs,
                emiDueDate,
                emiCategory,
                emiDis,
                emiDuration
            )
            mEmiViewModel.addUser(emi)
            Toast.makeText(requireContext(), "Inserted", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill proper data!!", Toast.LENGTH_LONG).show()
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

}