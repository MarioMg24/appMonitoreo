package dev.mario.appmonitoreo.component

import com.google.firebase.database.*
import dev.mario.appmonitoreo.component.Alerta
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

object FirebaseRepository {
    private val database = FirebaseDatabase.getInstance()
    private val alertasRef = database.getReference("alertas")

    fun obtenerAlertas(): Flow<List<Alerta>> = callbackFlow {
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val listaAlertas = mutableListOf<Alerta>()
                for (child in snapshot.children) {
                    val alerta = child.getValue(Alerta::class.java)
                    alerta?.let { listaAlertas.add(it) }
                }
                trySend(listaAlertas).isSuccess
            }

            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        alertasRef.addValueEventListener(listener)

        awaitClose {
            alertasRef.removeEventListener(listener)
        }
    }
}