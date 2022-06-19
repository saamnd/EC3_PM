package pe.edu.ulima.pm.ec.models

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.models.beans.Persona
import android.os.Handler
import pe.edu.ulima.pm.swapp.room.AppDatabase
import pe.edu.ulima.pm.swapp.room.dao.PersonaRoomDAO
import pe.edu.ulima.pm.swapp.room.models.PersonaRoom

class GestorPersona {

    fun obtenerListaPersonasRoom(context : Context) : List<Persona> {
        val daoPersona : PersonaRoomDAO = AppDatabase.getInstance(
            context).getPersonaRoomDAO()

        val listaPersonasRoom = daoPersona.getAll() // consulta Room
        println(listaPersonasRoom.size)
        val listaPersonas = listaPersonasRoom.map {
           Persona(it.fecha_corte, it.departamento, it.provincia, it.distrito, it.metododx)
        }
        return listaPersonas
    }

    fun guardarListaPersonasRoom(context : Context, persona : List<Persona>) {
        val db = AppDatabase.getInstance(
            context)
        val daoPersona : PersonaRoomDAO = db.getPersonaRoomDAO()

        persona.forEach {
            daoPersona.insert(
                PersonaRoom(it.fecha_corte, it.departamento, it.provincia, it.distrito, it.metododx, 0)
            )
        }

    }

}