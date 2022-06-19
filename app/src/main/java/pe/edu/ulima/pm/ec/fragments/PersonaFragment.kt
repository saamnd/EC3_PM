package pe.edu.ulima.pm.ec.fragments

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ec.models.GestorPersona
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.R

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



}