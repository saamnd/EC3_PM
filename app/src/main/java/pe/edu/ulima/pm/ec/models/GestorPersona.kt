package pe.edu.ulima.pm.ec.models

import android.content.Context
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.models.beans.Persona
import android.os.Handler
import android.util.Log
import pe.edu.ulima.pm.ec.Constantes
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
import java.text.SimpleDateFormat

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
        Log.d("save", "Se va a guardar")
        persona.forEach {
            daoPersona.insert(
                PersonaRoom(
                    persona[0].substring(7)+"-"+persona[0].substring(4,6)+"-"+persona[0].substring(0,3),
                    persona[1],
                    persona[2],
                    persona[3],
                    persona[4],
                    persona[5].toInt())
            )

        }

        val editor = context.getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE).edit()
        editor.putString(Constantes.SP_ESTA_SINCRONIZADO, "SINCRONIZADO")
        editor.commit()


        Log.d("save", "Se guardó")

    }

}