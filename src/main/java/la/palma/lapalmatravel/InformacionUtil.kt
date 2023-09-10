package la.palma.lapalmatravel



import android.content.Intent
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.text.util.Linkify
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class InformacionUtil : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_informacion_util)
        val btn1: Button = findViewById(R.id.btnasesoramiento2)
        val btn2: Button = findViewById(R.id.btnInicio2)
        val btn3: Button = findViewById(R.id.btnpdi3)
        val btn4: Button = findViewById(R.id.btnpdi4)

        val userInput: TextView = findViewById(R.id.txtResumenLaPalma)

        if (userInput != null) {
            Linkify.addLinks(
                userInput,
                Patterns.PHONE,
                "tel:",
                Linkify.sPhoneNumberMatchFilter,
                Linkify.sPhoneNumberTransformFilter
            )
            userInput.movementMethod = LinkMovementMethod.getInstance()
        }




        btn1.setOnClickListener{
            val intent = Intent(this, Asesoramiento::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener{
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener{
            val intent = Intent(this, PuntosDeInteres::class.java)
            startActivity(intent)
        }
        btn4.setOnClickListener{
            val intent = Intent(this, InformacionUtil::class.java)
            startActivity(intent)
        }
    }
}