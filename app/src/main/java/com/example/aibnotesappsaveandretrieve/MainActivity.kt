package com.example.aibnotesappsaveandretrieve

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var RV: RecyclerView
    lateinit var EDT: EditText
    lateinit var BTN: Button
    private lateinit var db: DBHlper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RV=findViewById(R.id.rv)
        EDT=findViewById(R.id.edt)
        BTN=findViewById(R.id.btn)
        db = DBHlper(this)


        BTN.setOnClickListener {

            if(EDT.text.isNotBlank())
            {
                postNote()
            }
            else
            {
                Toast.makeText(this, "Enter The Note", Toast.LENGTH_LONG).show()
            }
            updateRV()

        }
    }
    private fun updateRV(){
        RV.adapter = RecyclerViewAdapter(this, getItemsList())
        RV.layoutManager = LinearLayoutManager(this)
    }

    private fun getItemsList(): ArrayList<NoteModel1>{
        return db.viewNotes()
    }

    private fun postNote(){

        db.addNote(NoteModel1(0, EDT.text.toString()))
        EDT.text.clear()
        Toast.makeText(this, "Note Added", Toast.LENGTH_LONG).show()
        updateRV()
    }

}