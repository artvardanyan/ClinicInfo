package com.acaandroid.clinicinfo.fragments

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.text.format.DateFormat.is24HourFormat
import android.view.*
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.acaandroid.clinicinfo.R
import com.acaandroid.clinicinfo.databasenote.Notes
import com.acaandroid.clinicinfo.databasenote.ClinicInfo
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*


class HomeFragment : Fragment() {


    private lateinit var noteList: List<Notes>
    private var db: ClinicInfo? = null
    private lateinit var viewAdapter : RecNoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //build database
      /*  db = context?.let {
            Room.databaseBuilder(
                it,
                ClinicInfo::class.java, "clinic_info"
            ).build()
        }

        //get notes list from database
        GlobalScope.launch(Dispatchers.Default) {

            if (db != null) {
                noteList = db!!.notesDao().getAllNotes()
            }
        }*/
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewAdapter = RecNoteAdapter()
        viewAdapter.setList(noteList)

        //show list of notes
        recViewNote.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewAdapter

        }

        //searching by patient name,recording time or phone
        searchView.setOnQueryTextListener(object : android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                var  searchingList : List<Notes>? = null
                if (p0!!.isNotEmpty()){
                    searchingList = noteList.filter {
                        it.name.toLowerCase(Locale.getDefault()).contains(p0.toLowerCase(Locale.getDefault())) or
                        it.phone.contains(p0) or
                        it.time.toLowerCase(Locale.getDefault()).contains(p0)


                    }
                    recViewNote.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = viewAdapter
                        viewAdapter.setList(searchingList!!)

                }
                }else{
                    recViewNote.apply {
                        layoutManager = LinearLayoutManager(context)
                        adapter = viewAdapter
                        viewAdapter.setList(noteList)

                    }
                }
                return true
            }


        })

        //filter data by note's date
        filter.setOnClickListener {
            val filterMenu = PopupMenu(context,filter)
            filterMenu.inflate(R.menu.filter_menu)
            filterMenu.show()


            filterMenu.setOnMenuItemClickListener { item ->

                val calendar = Calendar.getInstance()
                when(item.itemId){
                    R.id.allTimes -> {


                        viewAdapter.setList(noteList)



                    }
                    R.id.lastMonth -> {

                       val month = DateFormat.format("MMM",calendar).toString()
                       val year = DateFormat.format("yyyy",calendar).toString()

                       val searchingList = noteList.filter {
                                   it.date.contains(month) and
                                   it.date.contains(year)
                       }
                       recViewNote.apply {
                           layoutManager = LinearLayoutManager(context)
                           adapter = viewAdapter
                           viewAdapter.setList(searchingList)
                       }

                   }
                    R.id.lastWeek -> {


//                        val searchingList = mutableListOf<Notes>()
//                        var i = 0
//                        while(i < 7){
//                            val dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar).toString()
//                            searchingList += noteList.filter {
//                                it.date == dateText
//                            } as MutableList<Notes>
//                            calendar.add(Calendar.DAY_OF_YEAR,-1)
//                            i++
//                        }
//                        Toast.makeText(context,"$searchingList",Toast.LENGTH_LONG).show()
//                        recViewNote.apply {
//                            layoutManager = LinearLayoutManager(context)
//                            adapter = viewAdapter
//                            viewAdapter.setList(searchingList)
//                        }
                        val calendarLastWeek = calendar.add(Calendar.DAY_OF_YEAR,-7)
                        val searchingList  = noteList.filter{
                            it.getCalendar().after(calendarLastWeek)
                        }

                        viewAdapter.setList(searchingList)


                    }
                    R.id.today -> {

                        val dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar).toString()
                        val searchingList = noteList.filter {
                            it.date.contains(dateText)
                        }
                        recViewNote.apply {
                            layoutManager = LinearLayoutManager(context)
                            adapter = viewAdapter
                            viewAdapter.setList(searchingList)
                        }


                    }
                    R.id.custom -> {
                        val YEAR = calendar.get(Calendar.YEAR)
                        val MONTH = calendar.get(Calendar.MONTH)
                        val DATE = calendar.get(Calendar.DATE)
                        val datePickerDialog =
                            context?.let {
                                DatePickerDialog(it, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                                    val calendar1 = Calendar.getInstance()
                                    calendar1.set(Calendar.YEAR, i)
                                    calendar1.set(Calendar.MONTH, i2)
                                    calendar1.set(Calendar.DATE, i3)
                                    val dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString()

                                    val searchingList = noteList.filter {
                                        it.date == dateText
                                    }
                                    recViewNote.apply {
                                        layoutManager = LinearLayoutManager(context)
                                        adapter = viewAdapter
                                        viewAdapter.setList(searchingList)
                                    }

                                }, YEAR, MONTH, DATE)
                            }

                        datePickerDialog?.show()




                   }
                   else -> false
               }
                //Toast.makeText(context,"$dateText",Toast.LENGTH_LONG).show()


                true
            }
        }

        //Add new note
        addNote.setOnClickListener {
            val mDialogView = LayoutInflater.from(context).inflate(R.layout.create_note, null)
            val btnPickDate: Button = mDialogView.findViewById(R.id.pickDate)
            val btnPickTime: Button = mDialogView.findViewById(R.id.pickTime)
            val txtDateDialog: TextView  = mDialogView.findViewById(R.id.txtDateDialog)
            val txtTimeDialog: TextView  = mDialogView.findViewById(R.id.txtTimeDialog)
            val etName: EditText = mDialogView.findViewById(R.id.etFullName)
            val etPhone: EditText = mDialogView.findViewById(R.id.etPhone)

            val mBuilder = AlertDialog.Builder(context)
                .setView(mDialogView)
                .setTitle("Create Note")
                .setPositiveButton("Create") { _, _ ->

                    //create note
                    val note = Notes(0,etName.text.toString(),etPhone.text.toString(),
                        txtDateDialog.text.toString(), 0, txtTimeDialog.text.toString())

                    //insert note to database
                    val job = GlobalScope.launch(Dispatchers.Default) {

                        db?.notesDao()?.insertNote(note)
                    }
                   //?????????????????????????????????????
                   viewAdapter.setList(noteList)

                }
                .setNegativeButton("Cancel") { _, _ ->

                }
            //pick date
            btnPickDate.setOnClickListener {
                handelDateButton(txtDateDialog)
            }

            //pick time
            btnPickTime.setOnClickListener {
                 handleTimeButton(txtTimeDialog)
            }


            mBuilder.show()
        }


    }

    //pick date
    @RequiresApi(Build.VERSION_CODES.N)
    private fun handelDateButton(view: TextView) {
        val calendar = Calendar.getInstance()
        val YEAR = calendar.get(Calendar.YEAR)
        val MONTH = calendar.get(Calendar.MONTH)
        val DATE = calendar.get(Calendar.DATE)
        val datePickerDialog =
            context?.let {
                DatePickerDialog(it, DatePickerDialog.OnDateSetListener { datePicker, i, i2, i3 ->
                    val calendar1 = Calendar.getInstance()
                    calendar1.set(Calendar.YEAR, i)
                    calendar1.set(Calendar.MONTH, i2)
                    calendar1.set(Calendar.DATE, i3)
                    val dateText = DateFormat.format("EEEE, MMM d, yyyy", calendar1).toString()
                    view.text = dateText

                }, YEAR, MONTH, DATE)
            }

        datePickerDialog?.show()


    }

    //pick time
    private fun handleTimeButton(view: TextView){
        val calendar = Calendar.getInstance()
        val HOUR = calendar.get(Calendar.HOUR)
        val MINUTE = calendar.get(Calendar.MINUTE)
        val is24HourFormat : Boolean = is24HourFormat(context)

        val timePickerDialog =
            TimePickerDialog(context, TimePickerDialog.OnTimeSetListener { timePicker, i, i2 ->
                val calendar1 = Calendar.getInstance()
                calendar1.set(Calendar.HOUR, i)
                calendar1.set(Calendar.MINUTE, i2)
                val dateText = DateFormat.format("h:mm a", calendar1).toString()
                view.text = dateText

            }, HOUR, MINUTE, is24HourFormat)

        timePickerDialog.show()


    }
}