package pe.edu.ulima.pm.ec.fragments

import android.app.Person
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ec.models.GestorPersona
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.MainActivity
import pe.edu.ulima.pm.ec.Constantes
import pe.edu.ulima.pm.ec.R
import pe.edu.ulima.pm.ec.models.beans.Persona
import pe.edu.ulima.pm.ec.room.AppDatabase
import pe.edu.ulima.pm.ec.room.dao.PersonaRoomDAO
import pe.edu.ulima.pm.ec.room.models.PersonaRoom
import java.net.HttpURLConnection
import java.net.URL

class MainFragment: Fragment() {

    private val fragmentPersona = PersonaFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Dashboard"
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bData= view.findViewById<Button>(R.id.butData)

        bData.setOnClickListener{
            val ft= this.parentFragmentManager.beginTransaction()
            ft.replace(R.id.fcvFragments, fragmentPersona)
            ft.commit()

        }
        val butSinc= view.findViewById<Button>(R.id.butSinc)

        butSinc.setOnClickListener{
            //DESACTIVAR BOTON
            butSinc.isEnabled=false
            SincronizarData()

        }

    }
    @RequiresApi(Build.VERSION_CODES.N)
    private fun SincronizarData(){
        //ObtenerListaPersonas

        var pBar = view?.findViewById<ProgressBar>(R.id.progressBar)

        GlobalScope.launch(Dispatchers.Main) {
            pBar?.incrementProgressBy(1)
            var lista:List<String> = mutableListOf()
            lista= withContext(Dispatchers.IO){
                GestorPersona().obtenerListaPersonasCorutina( pBar)

                }
        //Guardar Lista
            GestorPersona().guardarListaPersonasRoom(
                requireActivity().applicationContext,
                lista
            )

         }
        }


}