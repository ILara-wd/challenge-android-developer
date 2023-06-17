package mx.openpay.challenge.data.model.network

interface BasePageListResponse<T> {
    var page: Int
    var results: List<T>
}
