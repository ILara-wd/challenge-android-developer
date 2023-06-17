package mx.openpay.challenge.data.model.network

import mx.openpay.challenge.data.model.entity.Credit
import com.google.gson.annotations.SerializedName

data class PersonCreditsResponse(
    @SerializedName("cast")
    override var results: List<Credit>
) : BaseListResponse<Credit>
