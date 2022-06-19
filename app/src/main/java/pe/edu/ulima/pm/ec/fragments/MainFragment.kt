package pe.edu.ulima.pm.ec.fragments

import android.app.Person
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ec.models.GestorPersona
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val bData= view.findViewById<Button>(R.id.butData)

        bData.setOnClickListener{
            val fm= this.parentFragmentManager.beginTransaction()
            fm.replace(R.id.fcvFragments, fragmentPersona)
            fm.commit()

        }
        val butSinc= view.findViewById<Button>(R.id.butSinc)
        butSinc.setOnClickListener{
            SincronizarData()
        }
    }
    private fun SincronizarData(){
        //ObtenerListaPersonas
        GlobalScope.launch(Dispatchers.Main) {
            val url = URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download")
            val con = url.openConnection() as HttpURLConnection
            var datas="not yet"
            Log.d("Detail_Carga","auuuun")
            withContext(Dispatchers.IO){
                var br=con.inputStream.bufferedReader()
                while(br.readLine()!=null){
                    datas=br.readLine()
                    var result = datas.split(";").map { it.trim() }
                    //Insertar en BD
                    Log.d("Detail_Carga",datas)

                    val db = AppDatabase.getInstance(requireActivity().applicationContext)
                    val daoPersona: PersonaRoomDAO =db.getPersonaRoomDAO()

                    daoPersona.insert(
                        PersonaRoom(result[0],result[1],result[2],result[3],result[4],result[5].toInt())
                    )
                }

            }

            //DESACTIVAR BIOTON
        }
    }

}