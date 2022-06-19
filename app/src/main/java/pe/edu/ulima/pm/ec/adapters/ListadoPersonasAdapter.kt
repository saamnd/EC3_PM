package pe.edu.ulima.pm.ec.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ec.R
import pe.edu.ulima.pm.ec.models.beans.Persona

class ListadoPersonasAdapter (private val mListaPersonas : List<Persona>):
    RecyclerView.Adapter<ListadoPersonasAdapter.ViewHolder>(){
    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        val tviDepa : TextView //tviPlanetaNombre
        val tviCant : TextView

        init {
            tviDepa = view.findViewById(R.id.tviDepartamento)
            tviCant = view.findViewById(R.id.tviCount)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_persona, parent, false)
        val viewHolder = ViewHolder(view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val persona = mListaPersonas[position]
        holder.tviDepa.text = persona.departamento
        holder.tviCant.text = persona.cantidad.toString()


    }

    override fun getItemCount(): Int {
        // Devolver el nro de items a mostrar
        return mListaPersonas.size
    }
}