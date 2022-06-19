package pe.edu.ulima.pm.ec.models

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.models.beans.Persona
import android.os.Handler

class GestorPersona {

    fun obtenerListaPersonasCorutinas(callback : (List<Persona>)->Unit){
        GlobalScope.launch {
            val lista: List<Persona> = listOf(
                Persona("Sam")
            )


                callback(lista)


        }
    }

}