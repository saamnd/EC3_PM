package pe.edu.ulima.pm.ec.fragments

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import pe.edu.ulima.pm.ec.models.GestorPersona
import kotlinx.coroutines.*
import pe.edu.ulima.pm.ec.R
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
            val ft= this.parentFragmentManager.beginTransaction()
            ft.replace(R.id.fcvFragments, fragmentPersona)
            ft.commit()

        }
        val butSinc= view.findViewById<Button>(R.id.butSinc)
        butSinc.setOnClickListener{
            SincronizarData()
        }
    }
    private fun SincronizarData(){
        GlobalScope.launch(Dispatchers.Main) {
            val url = URL("https://files.minsa.gob.pe/s/eRqxR35ZCxrzNgr/download")
            val con = url.openConnection() as HttpURLConnection
            var datas="not yet"
            Log.d("Detail_Carga","auuuun")
            withContext(Dispatchers.IO){
                var br=con.inputStream.bufferedReader()
                while(br.readLine()!=null){
                    datas=con.inputStream.bufferedReader().readLine()
                    Log.d("Detail_Carga",datas)
                }
                Log.d("Detail_Carga",datas)}

        }
    }

}