package com.billy.mymonashapp.application.shared

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@ExperimentalCoroutinesApi
fun <T> CollectionReference.observeList(transformer: (QuerySnapshot) -> List<T>): Flow<List<T>> =
    callbackFlow {
        val listener = addSnapshotListener { documents, firebaseException ->
            if (documents != null) {
                offer(transformer(documents))
            } else {
                offer(emptyList<T>())
            }
            firebaseException?.let {
                cancel(it.message.toString())
                throw Exception(it.message)
            }
        }

        awaitClose {
            listener.remove()
            cancel()
        }

    }

@ExperimentalCoroutinesApi
fun <T> CollectionReference.observeObject(transformer: (QuerySnapshot) -> T?): Flow<T?> =
    callbackFlow {
        val listener = addSnapshotListener { documents, firebaseException ->
            if (documents != null) {
                offer(transformer(documents))
            } else {
                offer(null)
            }
            firebaseException?.let {
                cancel(it.message.toString())
                throw Exception(it.message)
            }
        }

        awaitClose {
            listener.remove()
            cancel()
        }
    }