package com.example.ava1

import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDate

class FormActivity : AppCompatActivity() {

    private val calendarView: CalendarView by lazy { findViewById(R.id.calendarView) }
    private val scheduleAppointmentBtn: Button by lazy { findViewById(R.id.scheduleBtn) }
    private val userName: TextView by lazy { findViewById(R.id.textView) }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var name = ""
        var localDate = ""
        calendarView.setOnDateChangeListener { _, year, month, day ->
            localDate = LocalDate.of(year, month + 1, day).toString()
        }

        scheduleAppointmentBtn.setOnClickListener{
            name = userName.text.toString()
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Aviso")
            builder.setMessage("Deseja confirmar a consulta para $localDate")
            builder.setPositiveButton("Sim") { _, _ ->
                Toast.makeText(this, "Agendamento confirmado", Toast.LENGTH_LONG).show()
//            calendarView.visibility = View.GONE
//            findViewById<EditText>(R.id.editTextTextEmailAddress).visibility = View.GONE
//            findViewById<Button>(R.id.scheduleBtn).visibility = View.GONE
//            findViewById<Button>(R.id.returnBtn).visibility = View.VISIBLE
//            findViewById<ImageView>(R.id.imageView2).visibility = View.VISIBLE
//            findViewById<TextView>(R.id.textView2).visibility = View.VISIBLE
            }
            builder.setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            builder.create().show()
        }
    }
}