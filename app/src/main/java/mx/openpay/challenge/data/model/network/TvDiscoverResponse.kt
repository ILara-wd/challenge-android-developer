package mx.openpay.challenge.data.model.network

import mx.openpay.challenge.data.model.entity.TvShow
import com.google.gson.annotations.SerializedName

data class TvDiscoverResponse(
    @SerializedName("page")
    override var page: Int,

    @SerializedName("results")
    override var results: List<TvShow>
) : BasePageListResponse<TvShow>
