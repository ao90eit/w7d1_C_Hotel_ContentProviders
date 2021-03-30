package com.aoinc.w7d1_c_hotel_contentproviders.model.db

import android.database.Cursor
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.aoinc.w7d1_c_hotel_contentproviders.model.data.HotelGuest

@Dao
interface GuestDao {

    @Insert
    fun checkInGuest(guest: HotelGuest)

    @Delete
    fun checkOutGuest(guest: HotelGuest)

    @Update
    fun updateGuest(guest: HotelGuest)

    @Query("SELECT * FROM guests")
    fun getAllGuests() : List<HotelGuest>

    @Query("SELECT * FROM guests")
    fun getAllGuestsCursor() : Cursor
}