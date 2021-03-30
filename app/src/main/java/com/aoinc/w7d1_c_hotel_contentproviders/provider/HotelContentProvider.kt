package com.aoinc.w7d1_c_hotel_contentproviders.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import androidx.room.Room
import com.aoinc.w7d1_c_hotel_contentproviders.model.db.GuestDatabase

class HotelContentProvider : ContentProvider() {

    private val authority = "com.aoinc.w7d1_c_hotel_contentproviders"
    private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)

    private val SPECIFIC_GUEST = 0  // content://{authority}/guests/3 -> 0
    private val ALL_GUESTS = 1      // content://{authority}/guests -> 1

    private var guestDatabase: GuestDatabase? = null

    override fun onCreate(): Boolean {
        uriMatcher.apply {
            addURI(authority, "guests", ALL_GUESTS)
            addURI(authority, "guests/#", SPECIFIC_GUEST)
        }

        context?.let {
            guestDatabase = Room.databaseBuilder(
                it,
                GuestDatabase::class.java,
                GuestDatabase.DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }

        return (guestDatabase != null)
    }

    override fun query(
        uri: Uri,
        projection: Array<out String>?,
        selection: String?,
        selectionArgs: Array<out String>?,
        sortOrder: String?
    ): Cursor? {

        val cursor =
            when (uriMatcher.match(uri)) {
                SPECIFIC_GUEST -> null
                ALL_GUESTS -> guestDatabase?.getDAO()?.getAllGuestsCursor()
                else -> null
            }

        return cursor
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<out String>?): Int {
        return 0
    }

    override fun update(
        uri: Uri,
        values: ContentValues?,
        selection: String?,
        selectionArgs: Array<out String>?
    ): Int {
        return 0
    }
}