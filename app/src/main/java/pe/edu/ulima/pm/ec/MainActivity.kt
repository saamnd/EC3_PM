package pe.edu.ulima.pm.ec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val butSinc = findViewById<Button>(R.id.butSinc)
        butSinc.setOnClickListener{SincronizarData()}
        val butLimpiar = findViewById<Button>(R.id.butLimpiar)
        val butData = findViewById<Button>(R.id.butData)
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
    private fun Limpiar(){

    }
    private fun butData(){

    }
}