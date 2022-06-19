package pe.edu.ulima.pm.ec.models

import android.content.Context
import android.os.Build
import pe.edu.ulima.pm.ec.models.beans.Persona
import android.os.Handler
import android.util.Log
import pe.edu.ulima.pm.ec.Constantes
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
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

        //while(br.readLine()!=null){
        while(cont<400){
            data=br.readLine()
            resultado.add(data)
            cont++
            if (cont%40000==0){
            pBar?.incrementProgressBy(1)
                Log.d("detail", data)}

        }
        return resultado
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun guardarListaPersonasRoom(context : Context, persona : List<String>, pBar: ProgressBar?) {
        val db = AppDatabase.getInstance(
            context)
        val daoPersona : PersonaRoomDAO = db.getPersonaRoomDAO()
        Log.d("save", "Se va a guardar")

        var cont=0
        persona.forEach {
            var result= it.split(";").map{it.trim()}
            cont++
            var fecha= result[0].substring(6,8)+"/"+result[0].substring(4,6)+"/"+result[0].substring(0,4)
             daoPersona.insert(
                PersonaRoom(
                    fecha,
                    result[1],
                    result[2],
                    result[3],
                    result[9],0)
            )
            if(cont%1000==0){
                pBar?.incrementProgressBy(1)
                Log.d("result",fecha + " " + cont.toString())
            }
        }

        pBar?.setProgress(100, true)

        val editor = context.getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE).edit()
        editor.putString(Constantes.SP_ESTA_SINCRONIZADO, "SINCRONIZADO")
        editor.commit()

        Log.d("save", "Se guardó "+cont)

    }
    fun obtenerListaPersonas(context: Context):List<PersonaRoom>{
        val db = AppDatabase.getInstance(context)
        val daoPersona : PersonaRoomDAO = db.getPersonaRoomDAO()
        val listaPersonas = daoPersona.getAll()

        return listaPersonas
    }

    fun eliminarListaPersonas(context : Context, Persona: List<PersonaRoom>){
        val db = AppDatabase.getInstance(context)
        val daoPersona : PersonaRoomDAO = db.getPersonaRoomDAO()
        Persona.forEach{
            val idpost=daoPersona.findById(it.id)
            daoPersona.delete(idpost)
        }
    }

    fun obtenerListaPersonasRoom (context : Context,fecha :String) : List<Persona> {
        val daoPersona : PersonaRoomDAO = AppDatabase.getInstance(
            context).getPersonaRoomDAO()

        val listaPersonasRoom = daoPersona.PorDepa(fecha) // consulta Room
        println(listaPersonasRoom.size)
        val listaPlanetas = listaPersonasRoom.map {
            Persona(fecha,it.cantidad,it.departamento)
        }
        return listaPlanetas
    }

}