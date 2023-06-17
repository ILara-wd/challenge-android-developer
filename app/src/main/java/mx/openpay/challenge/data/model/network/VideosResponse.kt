package mx.openpay.challenge.data.model.network

import com.google.gson.annotations.SerializedName
import mx.openpay.challenge.data.model.entity.Video

data class VideosResponse(
    @SerializedName("results")
    override var results: List<Video>
) : BaseListResponse<Video>
