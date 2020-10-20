package com.acaandroid.clinicinfo

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.dialog_rec.*
import kotlinx.android.synthetic.main.fragment_list_patient.*
import java.text.DateFormat
import java.util.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ListPatient : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list_patient, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)


        fab.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_rec, null)
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setTitle("Login Form")
                .setPositiveButton("Ok") { _, _ ->
                    Toast.makeText(context, "clicked yes", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancel") { _, _ ->
                    Toast.makeText(context, "clicked Cancel", Toast.LENGTH_LONG).show()
                }


            val mAlertDialog = mBuilder.show()

            mDialogView.setOnClickListener {
                mAlertDialog.dismiss()

                mDialogView.setOnClickListener {
                    mAlertDialog.dismiss()
                }
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ListPatient().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}