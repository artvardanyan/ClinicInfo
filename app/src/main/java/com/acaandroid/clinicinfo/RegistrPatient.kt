package com.acaandroid.clinicinfo

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.room.Room
import com.acaandroid.clinicinfo.database.AppDatabase
import kotlinx.android.synthetic.main.dialog.view.*
import kotlinx.android.synthetic.main.fragment_registr_patent.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RegisterPatient : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private val db by lazy {
        Room.databaseBuilder(
            context!!,
            AppDatabase::class.java, "database_name"
        ).build()
    }

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
        return inflater.inflate(R.layout.fragment_registr_patent, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        terapia.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog, null)
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setTitle("Login Form")
                .setPositiveButton("Ok") { _, _ ->
                    Toast.makeText(context, "clicked yes", Toast.LENGTH_LONG).show()
                    val name = mDialogView.radioButton1.text.toString()
                    terapia.text = name
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

        ortopedia.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_ortopedia, null)
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setTitle("Login Form")
                .setPositiveButton("Ok") { dialogInterface, which ->
                    Toast.makeText(context, "clicked yes", Toast.LENGTH_LONG).show()
                    val name = mDialogView.radioButton1.text.toString()
                    terapia.text = name
                }
                .setNegativeButton("Cancel") { dialogInterface, which ->
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

        surgeon.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_surgeon, null)
            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setTitle("Login Form")
                .setPositiveButton("Ok") { dialogInterface, which ->
                    Toast.makeText(context, "clicked yes", Toast.LENGTH_LONG).show()
                    val name = mDialogView.radioButton1.text.toString()
                    terapia.text = name
                }
                .setNegativeButton("Cancel") { dialogInterface, which ->
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

        registr.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                //db.patientDao().getAll()
            }
        }

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterPatient().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}