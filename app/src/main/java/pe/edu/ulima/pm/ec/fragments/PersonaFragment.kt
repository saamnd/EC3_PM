package pe.edu.ulima.pm.ec.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ec.models.GestorPersona
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.R
import pe.edu.ulima.pm.ec.adapters.ListadoPersonasAdapter
import pe.edu.ulima.pm.ec.models.beans.Persona

class PersonaFragment: Fragment() {
    private lateinit var mRviPersonas : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.title = "Ver Data"
        setHasOptionsMenu(true)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Si no existe la BD
                //pedir sincronización
                //Si existe

        mRviPersonas = view.findViewById(R.id.rviPersonas)
        val etFecha= view.findViewById<EditText>(R.id.EtFecha)
        val butBuscar = view.findViewById<Button>(R.id.butBuscar)


        butBuscar.setOnClickListener{
            var fecha= etFecha.text.toString()
            Log.i("fecha",fecha)
            var lista : List<Persona> = mutableListOf()
            lista = GestorPersona().obtenerListaPersonasRoom(
                requireContext().applicationContext, fecha)
            if(lista.isNotEmpty()){
            cargarListaDepartamentos(lista)}
            else{
                Toast.makeText(getActivity(),"No se encontraron datos", Toast.LENGTH_SHORT).show()
            }
        }



    }

    private fun cargarListaDepartamentos(lista: List<Persona>) {
        val adapter = ListadoPersonasAdapter(lista)
        mRviPersonas.adapter = adapter
    }
}