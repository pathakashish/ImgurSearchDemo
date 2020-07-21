package com.ashish.imgursearchdemo.ui.search.images

import android.os.Parcel
import android.os.Parcelable
import com.ashish.imgursearchdemo.model.Image

class UiImage(val id: String, val title: String, val link: String) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(title)
        parcel.writeString(link)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UiImage> {
        override fun createFromParcel(parcel: Parcel): UiImage {
            return UiImage(parcel)
        }

        override fun newArray(size: Int): Array<UiImage?> {
            return arrayOfNulls(size)
        }

        fun fromImage(image: Image): UiImage {
            return UiImage(image.id, image.title ?: "", image.link)
        }
    }
}