package com.georgcantor.hilt.model.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "article")
@Parcelize
data class Article(
    var title: String,
    var description: String,
    var url: String,
    var urlToImage: String?,
    var publishedAt: String,
    var source: Source,
    var content: String
) : Parcelable {
    @IgnoredOnParcel
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long = 0
}