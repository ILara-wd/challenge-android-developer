package mx.openpay.challenge.di

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mx.openpay.challenge.data.ChallengeConstant.PLACE_COLLECTION
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirestore(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

    @PlaceCollection
    @Provides
    @Singleton
    fun provideUsersCollection(
        firestore: FirebaseFirestore
    ): CollectionReference {
        return firestore.collection(PLACE_COLLECTION)
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PlaceCollection
}