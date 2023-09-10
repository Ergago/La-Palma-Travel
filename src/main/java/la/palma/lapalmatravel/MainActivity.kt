package la.palma.lapalmatravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Botón para el cambio de actividad
        val btn2: Button = findViewById(R.id.btnpdi)
        val btn1: Button = findViewById(R.id.btnasesoramiento)
        val btn3: Button = findViewById(R.id.btninformacion)

        btn1.setOnClickListener{
            val intent = Intent(this, Asesoramiento::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener{
            val intent = Intent(this, PuntosDeInteres::class.java)
            startActivity(intent)

        }
        btn3.setOnClickListener{
            val intent = Intent(this, InformacionUtil::class.java)
            startActivity(intent)
        }
    }
}