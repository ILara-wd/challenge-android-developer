package mx.openpay.challenge.domain

import kotlinx.coroutines.flow.Flow
import mx.openpay.challenge.tools.DataState
import mx.openpay.challenge.data.Repository
import mx.openpay.challenge.data.model.entity.Place
import javax.inject.Inject

class SavePlaceUseCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(place: Place): Flow<DataState<Boolean>> =
        repository.doSavePlaceFirestore(place)
}
