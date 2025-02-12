// FirebaseRepository.kt
package dev.mario.appmonitoreo.component

import com.google.firebase.database.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object FirebaseRepository {
    private val database = FirebaseDatabase.getInstance()
    private val alertasRef = database.getReference("alertas")

    fun obtenerTodasLasAlertas(): Flow<List<Alerta>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listaAlertas = snapshot.children.mapNotNull { it.getValue(Alerta::class.java) }
                trySend(listaAlertas).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        alertasRef.addValueEventListener(listener)
        awaitClose { alertasRef.removeEventListener(listener) }
    }
}