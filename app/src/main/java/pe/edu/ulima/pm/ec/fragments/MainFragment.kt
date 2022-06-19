package pe.edu.ulima.pm.ec.fragments

import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ec.models.GestorPersona
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.R

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bData= view.findViewById<Button>(R.id.butData)

        bData.setOnClickListener{
            val ft= this.parentFragmentManager.beginTransaction()
            ft.replace(R.id.fcvFragments, fragmentPersona)
            ft.commit()

        }
    }

}