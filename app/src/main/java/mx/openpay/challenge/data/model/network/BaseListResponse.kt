package mx.openpay.challenge.data.model.network

interface BaseListResponse<T> {
    var results: List<T>
}
