package pe.edu.ulima.pm.ec

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import pe.edu.ulima.pm.ec.fragments.*
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    private val fragmentMain = MainFragment()
    private val fragmentPersona = PersonaFragment()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fcvFragments, fragmentMain)
        ft.commit()





    }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return super.onCreateView(name, context, attrs)

        val sp = getSharedPreferences(Constantes.NOMBRE_SP, Context.MODE_PRIVATE)
        val sinc = sp.getString(Constantes.SP_ESTA_SINCRONIZADO, "")!!

        if (sinc!="") {
            findViewById<Button>(R.id.butSinc).isEnabled=false
        }

    }

}