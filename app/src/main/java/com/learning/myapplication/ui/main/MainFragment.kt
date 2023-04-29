package com.learning.myapplication.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.learning.myapplication.R

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Initialize the viewModel
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productTypeRadioGroup: RadioGroup = view.findViewById(R.id.productTypeRadioGroup)
        val brandRadioGroup: RadioGroup = view.findViewById(R.id.brandRadioGroup)
        val okButton: Button = view.findViewById(R.id.okButton)
        val cancelButton: Button = view.findViewById(R.id.cancelButton)
        val resultTextView: TextView = view.findViewById(R.id.resultTextView)

        okButton.setOnClickListener {
            val selectedProductTypeId = productTypeRadioGroup.checkedRadioButtonId
            val selectedBrandId = brandRadioGroup.checkedRadioButtonId

            if (selectedProductTypeId != -1 && selectedBrandId != -1) {
                val selectedProductType: RadioButton = view.findViewById(selectedProductTypeId)
                val selectedBrand: RadioButton = view.findViewById(selectedBrandId)
                resultTextView.text = getString(R.string.result_format, selectedProductType.text, selectedBrand.text)
            } else {
                resultTextView.text = getString(R.string.error_message)
            }
        }

        cancelButton.setOnClickListener {
            productTypeRadioGroup.clearCheck()
            brandRadioGroup.clearCheck()
            resultTextView.text = ""
        }
    }
}