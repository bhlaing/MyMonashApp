package com.billy.mymonashapp.application

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.ktx.firestoreSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideFireBaseFireStore(): FirebaseFirestore {
        // Enable offline capability
        // should be enabled by default!
        val settings = firestoreSettings {
            isPersistenceEnabled = true
            // Unlimited cache-size for demo. Default is 100MB
            cacheSizeBytes = FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED
        }

        return FirebaseFirestore.getInstance().apply { firestoreSettings = settings }
    }
}