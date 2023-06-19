package mx.openpay.challenge.data.model.entity

import com.google.firebase.Timestamp
import com.google.firebase.firestore.GeoPoint
import com.google.gson.annotations.SerializedName

data class Place(
    @SerializedName("address") val address: String? = null,
    @SerializedName("coordinate") val coordinate: GeoPoint? = null,
    @SerializedName("date") val date: Timestamp = Timestamp.now()
)