package com.aoinc.w7d1_c_hotel_contentproviders.view

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import com.aoinc.w7d1_c_hotel_contentproviders.R
import com.aoinc.w7d1_c_hotel_contentproviders.model.data.HotelGuest
import com.aoinc.w7d1_c_hotel_contentproviders.model.db.GuestDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var hotelDatabase: GuestDatabase

    private lateinit var nameEditText: EditText
    private lateinit var roomEditText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        hotelDatabase = Room.databaseBuilder(
                this,
                GuestDatabase::class.java,
                GuestDatabase.DATABASE_NAME
        )
            .allowMainThreadQueries()
            .build()

        nameEditText = findViewById(R.id.guest_name_editText)
        roomEditText = findViewById(R.id.guest_room_number_editText)

        findViewById<Button>(R.id.check_in_button).setOnClickListener {
            val hotelGuest = HotelGuest(0,
                    nameEditText.text.toString(),
                    roomEditText.text.toString().toInt()
            )

            nameEditText.text.clear()
            roomEditText.text.clear()

            hotelDatabase.getDAO().checkInGuest(hotelGuest)
            readFromDatabase()
        }

        readFromDatabase()
    }

    private fun readFromDatabase() {
        val sBuilder = StringBuilder()
        hotelDatabase.getDAO().getAllGuests().forEach {
            sBuilder.append("${it.guestName} -> room ${it.roomNumber}\n")
        }

        findViewById<TextView>(R.id.guest_textView).also {
            it.text = sBuilder
//            it.text = sBuilder.toString()
        }
    }
}