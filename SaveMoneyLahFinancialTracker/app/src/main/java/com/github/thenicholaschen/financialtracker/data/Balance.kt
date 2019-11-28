package com.github.thenicholaschen.financialtracker.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "balance_table")
data class Balance(

    @ColumnInfo(name = "Amount")
    var amount: Double = 0.0,

    @ColumnInfo(name = "Description")
    var description: String,

    @ColumnInfo(name = "Date")
    var date: String

) {
    //does it matter if these are private or not?
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
