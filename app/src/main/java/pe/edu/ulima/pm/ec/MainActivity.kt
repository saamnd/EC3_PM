package pe.edu.ulima.pm.ec

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import pe.edu.ulima.pm.ec.fragments.*

class MainActivity : AppCompatActivity() {

    private val fragmentMain = MainFragment()
    private val fragmentPersona = PersonaFragment()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft = supportFragmentManager.beginTransaction()
        ft.add(R.id.fcvFragments, fragmentMain)
        ft.commit()

        val bData = findViewById<Button>(R.id.butData)

    }
}