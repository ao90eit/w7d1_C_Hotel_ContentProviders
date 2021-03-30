package com.aoinc.w7d1_c_hotel_contentproviders.model.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "guests")
data class HotelGuest (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "guest_id")
    val guestId: Int,

    @ColumnInfo(name = "guest_name")
    val guestName: String,

    @ColumnInfo(name = "room_number")
    val roomNumber: Int
)
