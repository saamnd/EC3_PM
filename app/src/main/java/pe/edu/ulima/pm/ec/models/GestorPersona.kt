package pe.edu.ulima.pm.ec.models

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.models.beans.Persona
import android.os.Handler
import android.util.Log
import pe.edu.ulima.pm.ec.room.*
import pe.edu.ulima.pm.ec.room.dao.PersonaRoomDAO
import pe.edu.ulima.pm.ec.room.models.PersonaRoom
import java.net.HttpURLConnection
import java.net.URL

class GestorPersona {

    fun obtenerListaPersonasCorutina() : List<String> {
        val url = URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download")
        val con = url.openConnection() as HttpURLConnection

        var br=con.inputStream.bufferedReader()
        val resultado = mutableListOf<String>()
        var data=""
        while(br.readLine()!=null){
            data=br.readLine()
            resultado.add(data)
            Log.d("detail", data)
        }

        return resultado
    }

    fun guardarListaPersonasRoom(context : Context, persona : List<String>) {
        val db = AppDatabase.getInstance(
            context)
        val daoPersona : PersonaRoomDAO = db.getPersonaRoomDAO()

        persona.forEach {
            daoPersona.insert(
                PersonaRoom(
                    persona[0],
                    persona[1],
                    persona[2],
                    persona[3],
                    persona[4],
                    persona[5].toInt())
            )
        }

    }

}