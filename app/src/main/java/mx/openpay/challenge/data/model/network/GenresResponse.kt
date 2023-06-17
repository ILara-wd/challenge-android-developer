package mx.openpay.challenge.data.model.network

import com.google.gson.annotations.SerializedName
import mx.openpay.challenge.data.model.entity.Genre

data class GenresResponse(
    @SerializedName("genres")
    override var results: List<Genre>
) : BaseListResponse<Genre>
