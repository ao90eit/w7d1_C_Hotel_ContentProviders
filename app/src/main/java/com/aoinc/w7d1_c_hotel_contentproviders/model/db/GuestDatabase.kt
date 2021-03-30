package com.aoinc.w7d1_c_hotel_contentproviders.model.db

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.aoinc.w7d1_c_hotel_contentproviders.model.data.HotelGuest

@Database(version = 1, entities = [HotelGuest::class])
abstract class GuestDatabase : RoomDatabase() {

    abstract fun getDAO(): GuestDao

    companion object {
        const val DATABASE_NAME = "hotel.db"
    }
}