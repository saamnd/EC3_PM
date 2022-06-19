package pe.edu.ulima.pm.ec.fragments

import android.app.Person
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
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

        val pBar = view.findViewById<ProgressBar>(R.id.progressBar)
        val bData= view.findViewById<Button>(R.id.butData)
        val butSinc= view.findViewById<Button>(R.id.butSinc)
        val butLimpiar = view.findViewById<Button>(R.id.butLimpiar)

        bData.setOnClickListener{
            val ft= this.parentFragmentManager.beginTransaction()
            ft.replace(R.id.fcvFragments, fragmentPersona)
            ft.commit()
        }

        butSinc.setOnClickListener{
            butSinc.isEnabled=false
            pBar.visibility = View.VISIBLE
            SincronizarData()
        }

        butLimpiar.setOnClickListener {

            EliminarData()

            pBar?.setProgress(0)
            pBar?.visibility = View.INVISIBLE
            butSinc.isEnabled=true

        }

    }
    private fun EliminarData(){
        val ListPersonas = GestorPersona().obtenerListaPersonas(requireActivity().applicationContext)
        GestorPersona().eliminarListaPersonas(requireActivity().applicationContext,ListPersonas)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun SincronizarData(){
        //ObtenerListaPersonas

        val pBar = view?.findViewById<ProgressBar>(R.id.progressBar)

        GlobalScope.launch(Dispatchers.Main) {
            pBar?.incrementProgressBy(1)
            var lista:List<String> = mutableListOf()
            lista= withContext(Dispatchers.IO){
                GestorPersona().obtenerListaPersonasCorutina(pBar)
                }
        //Guardar Lista
            GestorPersona().guardarListaPersonasRoom(
                requireActivity().applicationContext,
                lista, pBar)

            Toast.makeText(getActivity(),"Listo!",Toast.LENGTH_SHORT).show()

         }

        }


}