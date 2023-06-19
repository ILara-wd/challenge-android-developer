package mx.openpay.challenge.data

import com.google.firebase.firestore.CollectionReference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import mx.openpay.challenge.tools.DataState
import mx.openpay.challenge.data.model.entity.Place
import mx.openpay.challenge.di.FirebaseModule
import java.util.UUID
import javax.inject.Inject

class FirebaseRepository @Inject constructor(
    @FirebaseModule.PlaceCollection private val collection: CollectionReference
) {

    suspend fun savePlaceFirestore(place: Place): Flow<DataState<Boolean>> = flow {
        emit(DataState.Loading)
        try {
            var uploadSuccessful = false
            collection.document(UUID.randomUUID().toString()).set(
                hashMapOf(
                    "address" to place.address,
                    "coordinate" to place.coordinate,
                    "date" to place.date
                )
            )
                .addOnSuccessListener {
                    uploadSuccessful = true
                }.addOnFailureListener {
                    uploadSuccessful = false
                }.await()
            emit(DataState.Success(uploadSuccessful))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
            emit(DataState.Finished)
        }
    }

    suspend fun getAllPlaceFirestore(): Flow<DataState<List<Place>>> = flow {
        emit(DataState.Loading)
        try {
            val place = collection
                .get()
                .await()
                .toObjects(Place::class.java)
            emit(DataState.Success(place))
            emit(DataState.Finished)
        } catch (e: Exception) {
            emit(DataState.Error(e))
        }
    }

}
