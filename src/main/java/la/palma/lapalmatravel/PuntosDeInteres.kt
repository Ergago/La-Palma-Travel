package la.palma.lapalmatravel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class PuntosDeInteres : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_puntos_de_interes)
        val btn1: Button = findViewById(R.id.btnasesoramiento)
        val btn2: Button = findViewById(R.id.btnInicio)
        val btn3: Button = findViewById(R.id.btninformacion)
        val btn5: Button = findViewById(R.id.btnpdi2)
        btn1.setOnClickListener{
            val intent = Intent(this, Asesoramiento::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener{
            val intent = Intent(this, InformacionUtil::class.java)
            startActivity(intent)
        }
        btn5.setOnClickListener{
            val intent = Intent(this, PuntosDeInteres::class.java)
            startActivity(intent)
        }
    }
}