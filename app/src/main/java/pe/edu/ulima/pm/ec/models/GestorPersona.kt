package pe.edu.ulima.pm.ec.models

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.R
import pe.edu.ulima.pm.ec.fragments.MainFragment
import pe.edu.ulima.pm.ec.room.*
import pe.edu.ulima.pm.ec.room.dao.PersonaRoomDAO
import pe.edu.ulima.pm.ec.room.models.PersonaRoom
import java.net.HttpURLConnection
import java.net.URL



class GestorPersona {

    fun obtenerListaPersonasCorutina(pBar: ProgressBar?) : List<String> {

        val url = URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download")
        val con = url.openConnection() as HttpURLConnection

        var br=con.inputStream.bufferedReader()

        val resultado = mutableListOf<String>()
        var cont=0;
        var data=""

        while(br.readLine()!=null){
            data=br.readLine()
            resultado.add(data)
            cont++
            if (cont%10000==0){
            pBar?.incrementProgressBy(1)}
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