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
import java.util.concurrent.ScheduledThreadPoolExecutor

class GestorPersona {
    val filas: Int =10000
    val aumentar: Int= filas/50//72000//
    fun obtenerListaPersonasCorutina(pBar: ProgressBar?) : List<String> {

        val url = URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download")
        val con = url.openConnection() as HttpURLConnection

        var br=con.inputStream.bufferedReader()
        val resultado = mutableListOf<String>()
        var cont=0;
        var data: String? = null
        var tito=true

        //while(tito){
        while(cont<filas){
            data = br.readLine()
            if (data==null) {
                tito=false
            }
            else {
                resultado.add(data)
                //Log.d("LLEGAMOS ACA", data!!)
                cont++
                if (cont % aumentar == 0) {
                    Log.d("datoCorutina",cont.toString())
                    pBar?.incrementProgressBy(1)
                }
            }
        }
        Log.d("datoCorutina", cont.toString())
        return resultado
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun guardarListaPersonasRoom(context : Context, persona : List<String>, pBar: ProgressBar?) {
        val db = AppDatabase.getInstance(context)
        val daoPersona : PersonaRoomDAO = db.getPersonaRoomDAO()
        var cont=0

        Log.d("guardarRoom", "Empieza guardado")
        persona.forEach {
            var result= it.split(";").map{it.trim()}
            cont++
            if (result[7].length!=0) { //Hay algunos valores vacios
                daoPersona.insert(
                    PersonaRoom(
                        result[7],
                        result[1],
                        result[2],
                        result[3],
                        result[9], 0
                    )
                )
                Log.d("SQLITE","SE GUARDO "+cont)
            }
            if(cont%aumentar==0){
                pBar?.incrementProgressBy(1)
            }
        }
        val editor = context.getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE).edit()
        editor.putString(Constantes.SP_ESTA_SINCRONIZADO, "SINCRONIZADO")
        editor.commit()

        pBar?.setProgress(100, true)
        Log.d("guardarRoom", "Terminó guardado "+cont)

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
            Log.d("Prueba", it.fecha_res )
            val idpost=daoPersona.findById(it.id)
            daoPersona.delete(idpost)
        }
        val peoplepos=obtenerListaPersonas(context)
        Log.d("eliminar", "Hay " + peoplepos.size+" datos" )
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