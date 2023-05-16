package com.learning.lab_2_var_9.ui.main

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.learning.lab_2_var_9.DataActivity
import com.learning.myapplication.R
import java.io.OutputStreamWriter

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
        val openButton: Button = view.findViewById(R.id.openButton)

        okButton.setOnClickListener {
            val selectedProductTypeId = productTypeRadioGroup.checkedRadioButtonId
            val selectedBrandId = brandRadioGroup.checkedRadioButtonId

            if (selectedProductTypeId != -1 && selectedBrandId != -1) {
                val selectedProductType: RadioButton = view.findViewById(selectedProductTypeId)
                val selectedBrand: RadioButton = view.findViewById(selectedBrandId)
                resultTextView.text = getString(R.string.result_format, selectedProductType.text, selectedBrand.text)

                try {
                    val outputStreamWriter = OutputStreamWriter(context?.openFileOutput("data.txt", MODE_PRIVATE))
                    outputStreamWriter.write(resultTextView.text.toString())
                    outputStreamWriter.close()

                    Toast.makeText(context, "Успішно збережено", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {
                    Toast.makeText(context, "Помилка збереження", Toast.LENGTH_SHORT).show()
                }

            } else {
                resultTextView.text = getString(R.string.error_message)
                Toast.makeText(context, "Будь ласка, виберіть обидва параметри перед збереженням", Toast.LENGTH_LONG).show()
            }
        }


        openButton.setOnClickListener {
            val intent = Intent(context, DataActivity::class.java)
            startActivity(intent)
        }

        cancelButton.setOnClickListener {
            productTypeRadioGroup.clearCheck()
            brandRadioGroup.clearCheck()
            resultTextView.text = ""
        }
    }
}