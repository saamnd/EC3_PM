package pe.edu.ulima.pm.ec.models

import android.app.Person
import android.content.Context
import android.os.Build
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.models.beans.Persona
import android.os.Handler
import android.util.Log
import pe.edu.ulima.pm.ec.Constantes
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.R
import pe.edu.ulima.pm.ec.fragments.MainFragment
import pe.edu.ulima.pm.ec.room.*
import pe.edu.ulima.pm.ec.room.dao.PersonaRoomDAO
import pe.edu.ulima.pm.ec.room.models.DepartamentoRoom
import pe.edu.ulima.pm.ec.room.models.PersonaRoom
import java.net.HttpURLConnection
import java.net.URL
import java.text.SimpleDateFormat

class GestorPersona {
    @RequiresApi(Build.VERSION_CODES.N)
    fun obtenerListaPersonasCorutina(pBar: ProgressBar?) : List<String> {

        val url = URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download")
        val con = url.openConnection() as HttpURLConnection

        var br=con.inputStream.bufferedReader()
        val resultado = mutableListOf<String>()
        var cont=0;
        var data=""
        while(cont<500){
        //while(br.readLine()!=null){
            data=br.readLine()
            resultado.add(data)
            cont++
            if (cont%20000==0){
            pBar?.incrementProgressBy(1)
                Log.d("detail", data)}

        }
        pBar?.setProgress(100, true)
        return resultado
    }

    fun guardarListaPersonasRoom(context : Context, persona : List<String>) {
        val db = AppDatabase.getInstance(
            context)
        val daoPersona : PersonaRoomDAO = db.getPersonaRoomDAO()
        Log.d("save", "Se va a guardar")

        var cont=0
        persona.forEach {
            var result= it.split(";").map{it.trim()}
            cont++
            daoPersona.insert(
                PersonaRoom(
                    result[0].substring(7)+"-"+result[0].substring(4,6)+"-"+result[0].substring(0,3),
                    result[1],
                    result[2],
                    result[3],
                    result[9],0)
            )

        }

        val editor = context.getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE).edit()
        editor.putString(Constantes.SP_ESTA_SINCRONIZADO, "SINCRONIZADO")
        editor.commit()


        Log.d("save", "Se guardó "+cont)

    }
    fun obtenerListaPersonasRoom (context : Context) : List<Persona> {
        val daoPersona : PersonaRoomDAO = AppDatabase.getInstance(
            context).getPersonaRoomDAO()
        val fecha_ej="17-06-2022"
        val listaPersonasRoom = daoPersona.PorDepa(fecha_ej) // consulta Room
        println(listaPersonasRoom.size)
        val listaPlanetas = listaPersonasRoom.map {
            Persona(it.departamento, it.cantidad,fecha_ej)
        }
        return listaPlanetas
    }



}