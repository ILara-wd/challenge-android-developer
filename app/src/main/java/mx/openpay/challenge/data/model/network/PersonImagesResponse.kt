package mx.openpay.challenge.data.model.network

import mx.openpay.challenge.data.model.entity.Image
import com.google.gson.annotations.SerializedName

data class PersonImagesResponse(
    @SerializedName("profiles")
    override var results: List<Image>
) : BaseListResponse<Image>
