package la.palma.lapalmatravel

import android.util.Log
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FieldPath
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.math.log


open class Asesoramiento : AppCompatActivity() {
    var municipio = ""
    var comida = 0
    var plan = 0
    var transporte = 0
    var senderismo: Boolean = false
    var noche: Boolean = false
    var cultural: Boolean = false
    var gastronomico: Boolean = false
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    var caldera: Boolean = false
    var roque: Boolean = false
    var salinas: Boolean = false
    var tilos: Boolean = false
    var charco: Boolean = false
    var teneguia: Boolean = false
    var marcos: Boolean = false
    var volcanes: Boolean = false
    var mercadillo: Boolean = false
    var prois: Boolean = false
    var calculo: Double = 0.0
    var precioFinal = ""
    var dias = 0
    var acceso = false
    var presupuesto: Int = 0

    private val TAG = "MyActivity"
    lateinit var residencia: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asesoramiento)

        val btn: Button = findViewById(R.id.btnInicio3)
        val btn2: Button = findViewById(R.id.btnpdi5)
        val btn3: Button = findViewById(R.id.btninformacion2)
        val txtPresupuesto: EditText = findViewById(R.id.EdtPresupuesto)

        val txt18: TextView = findViewById(R.id.txtTitulo18)
        val txtPlanes: TextView = findViewById(R.id.txtPlanes)
        val txtResultado: TextView = findViewById(R.id.txtResultado)


        val chckCaldera: CheckBox = findViewById(R.id.chckCaldera)
        val chckRoque: CheckBox = findViewById(R.id.chckRoque)
        val chckSalina: CheckBox = findViewById(R.id.chckSalina)
        val chckTilos: CheckBox = findViewById(R.id.chckTilos)
        val chckCharco: CheckBox = findViewById(R.id.chckCharco)
        val chckTeneguia: CheckBox = findViewById(R.id.chckTeneguia)
        val chckMarcos: CheckBox = findViewById(R.id.chckMarcos)
        val chckVolcanes: CheckBox = findViewById(R.id.chckVolcanes)
        val chckMercadillo: CheckBox = findViewById(R.id.chckMercadillo)
        val chckProis: CheckBox = findViewById(R.id.chckProis)
        val txtDuracion: EditText = findViewById(R.id.EdtDias)

        val rbGuagua: RadioButton = findViewById(R.id.rbGuagua)
        val rbTaxi: RadioButton = findViewById(R.id.rbTaxi)
        val rbAlquiler: RadioButton = findViewById(R.id.rbAlquiler)

        val rbRestaurantes: RadioButton = findViewById(R.id.rbRestaurantes)
        val rbSupermercados: RadioButton = findViewById(R.id.rbSuper)

        val rbCalidad: RadioButton = findViewById(R.id.rbCalidad)
        val rbEconomico: RadioButton = findViewById(R.id.rbEconomico)
        val btnCalcular: Button = findViewById(R.id.btnCalcular)


        //aqui definimos la variable del Spinner SpMunicipios
        val spinner = findViewById<Spinner>(R.id.SpMunicipios)
        //aqui definimos que el valor del spinner es igual a la array list guardada en strings.xml
        val lista = resources.getStringArray(R.array.municipios)


        val adaptador = ArrayAdapter(this, android.R.layout.simple_spinner_item, lista)
        spinner.adapter = adaptador
        btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        btn2.setOnClickListener {
            val intent = Intent(this, PuntosDeInteres::class.java)
            startActivity(intent)
        }
        btn3.setOnClickListener {
            val intent = Intent(this, InformacionUtil::class.java)
            startActivity(intent)
        }
        //aqui creamos los dos casos posibles del Spinner para interactuar con él
        try {
            spinner.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                    residencia = lista[p2]

                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }

            }
        } catch (e: Exception) {
            null
        }


        btnCalcular.setOnClickListener {
            //obtenemos la variable del edittext
            try {
                presupuesto = txtPresupuesto.getText().toString().toInt()
                val finalValue = presupuesto.toInt()

            } catch (e: Exception) {
                null
            }

            if (residencia == "Barlovento"){
                municipio = "Barlovento"
            }
            if (residencia == "Breña Alta"){
                municipio = "Breña alta"
            }
            if (residencia == "Breña Baja"){
                municipio = "Breña baja"
            }
            if (residencia == "El Paso"){
                municipio = "El paso"
            }
            if (residencia == "Fuencaliente"){
                municipio = "Fuencaliente"
            }
            if (residencia == "Garafia"){
                municipio = "Garafia"
            }
            if (residencia == "Los Llanos de Aridane"){
                municipio = "Los llanos"
            }
            if (residencia == "Puntallana"){
                municipio = "Puntallana"
            }
            if (residencia == "San Andrés y Los Sauces"){
                municipio = "San andres y sauces"
            }
            if (residencia == "Santa Cruz"){
                municipio = "Santa cruz"
            }
            if (residencia == "Tazacorte"){
                municipio = "Tazacorte"
            }
            if (residencia == "Puntagorda"){
                municipio = "puntagorda"
            }
            if (residencia == "Tijarafe"){
                municipio = "tijarafe"
            }
            if (residencia == "Villa de Mazo"){
                municipio = "villa de mazo"
            }
            Toast.makeText(this, residencia, Toast.LENGTH_SHORT).show()




            if (municipio != "") {
                try{
                comida = 0
                plan = 0
                transporte = 0
                senderismo = false
                noche = false
                cultural = false
                gastronomico = false
                acceso = false
                caldera = false
                roque = false
                salinas = false
                tilos = false
                charco = false
                teneguia = false
                marcos = false
                volcanes = false
                mercadillo = false
                prois = false
                calculo = 0.0
                precioFinal = ""
                dias = 0
                dias = txtDuracion.getText().toString().toInt()

                if(chckCaldera.isChecked || chckRoque.isChecked || chckCharco.isChecked ||
                    chckSalina.isChecked ||chckTilos.isChecked ||chckTeneguia.isChecked ||
                    chckMarcos.isChecked ||chckMercadillo.isChecked ||chckProis.isChecked ||
                    chckVolcanes.isChecked){
                    acceso = true

                }





                if (chckCaldera.isChecked)
                    caldera = true
                if (chckRoque.isChecked)
                    roque = true
                if (chckSalina.isChecked)
                    salinas = true
                if (chckTilos.isChecked)
                    tilos = true
                if (chckCharco.isChecked)
                    charco = true
                if (chckTeneguia.isChecked)
                    teneguia = true
                if (chckMarcos.isChecked)
                    marcos = true
                if (chckVolcanes.isChecked)
                    volcanes = true
                if (chckMercadillo.isChecked)
                    mercadillo = true
                if (chckProis.isChecked)
                    prois = true

                try {
                    val estancia: String = txtDuracion.getText().toString()
                    val finalValue = estancia.toInt()

                } catch (e: Exception) {
                    null
                }

                if (rbGuagua.isChecked)
                    transporte = 1
                if (rbTaxi.isChecked)
                    transporte = 2
                if (rbAlquiler.isChecked)
                    transporte = 3

                if (rbRestaurantes.isChecked)
                    comida = 1
                if (rbSupermercados.isChecked)
                    comida = 2


                if (rbCalidad.isChecked)
                    plan = 1
                if (rbEconomico.isChecked)
                    plan = 2
                var contador2 = 0
                var contador1 = 0
                var contador = 0
                var texto = ""
                var actividades = ""
                var visitas = 0
                var suma = 0
                var precioX = arrayListOf<Int>()
                var precio = arrayListOf<Int>()
                var precio1 = arrayListOf<Int>()
                var precio2 = arrayListOf<Int>()
                var nombres = arrayListOf<String>()
                var Ltaxi = arrayListOf<Int>()
                var Lguagua = arrayListOf<Int>()
                var nombre = arrayListOf<String>()
                var comidaV = arrayListOf<Int>()
                var nombre1 = arrayListOf<String>()
                var nombre2 = arrayListOf<String>()
                var ubicacion = arrayListOf<String>()
                var ubicacion1 = arrayListOf<String>()
                var ubicacion2 = arrayListOf<String>()
                var precios = arrayListOf<Int>()
                //var guagua = arrayListOf<Int>()
                var datos = ""
                var senderos = ""
                var num1 = 0
                var num2 = 0
                var num3 = arrayListOf<Int>()
                    var planF = arrayListOf<String>()

                var supermercado = 15
                when (plan) {
                    1 ->//calidad
                        db.collection(municipio)
                            .whereEqualTo("calidad", "calidad")
                            .whereEqualTo("tipo", "residencia")
                            .get()
                            .addOnSuccessListener { snapshot ->
                                for (document in snapshot) {
                                    nombre.add("${document.id}\n => ")
                                    precios.add(document["precio"].toString().toInt())
                                    ubicacion.add(document["ubicacion"].toString())



                                }

                                when (comida) {
                                    1 -> //restaurante
                                        db.collection(municipio)
                                            .whereEqualTo("calidad", "calidad")
                                            .whereEqualTo("tipo", "restaurante")
                                            .get()
                                            .addOnSuccessListener { snapshot ->
                                                for (document in snapshot) {
                                                    nombre1.add("${document.id}\n => ")
                                                    comidaV.add(
                                                        document["precio"].toString().toInt()
                                                    )
                                                    ubicacion1.add(document["ubicacion"].toString())

                                                }

                                                when (transporte) {
                                                    1 -> {//guagua
                                                        nombres.clear()
                                                        precio2.clear()
                                                        txtResultado.setText("-")
                                                        txtPlanes.setText("-")
                                                        Lguagua.clear()
                                                        nombre2.clear()
                                                        if (caldera) {

                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "la caldera")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)

                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }

                                                        }

                                                        if (roque) {
                                                            try{
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "el roque")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    //txt18.setText("holaaaaa")
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()

                                                                }
                                                        }catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }
                                                        }

                                                        if (charco) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "charco azul"
                                                                )
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (salinas) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "salinas")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (tilos) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "los tilos")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (teneguia) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "volcan teneguia"
                                                                )
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                }
                                                        }

                                                        if (marcos) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "marcos y corderos"
                                                                )
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (volcanes) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "ruta de los volcanes"
                                                                )
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (mercadillo) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "mercadillo")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (prois) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "prois")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }
                                                        if (acceso == false) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {

                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")


                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1
                                                            contador = 0

                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {

                                                                num3.add(precioX[contador])
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n El precio supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }


                                                    }
                                                    2 -> {//taxi
                                                        nombres.clear()
                                                        precio2.clear()
                                                        txtResultado.setText("-")
                                                        txtPlanes.setText("-")
                                                        Lguagua.clear()
                                                        nombre2.clear()
                                                        if (caldera) {

                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "la caldera")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->

                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }

                                                        }

                                                        if (roque) {

                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "el roque")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    //txt18.setText("holaaaaa")
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()

                                                                }

                                                        }

                                                        if (charco) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "charco azul"
                                                                )
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (salinas) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "salinas")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (tilos) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "los tilos")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (teneguia) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "volcan teneguia"
                                                                )
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (marcos) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "marcos y corderos"
                                                                )
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (volcanes) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "ruta de los volcanes"
                                                                )
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (mercadillo) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "mercadillo")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (prois) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "prois")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF
                                                                }
                                                        }

                                                        if (acceso == false) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {

                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")


                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1
                                                            contador = 0

                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {

                                                                num3.add(precioX[contador])
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n El precio supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                    }
                                                    3 -> {//vehiculo de alquiler
                                                        nombres.clear()
                                                        precio2.clear()
                                                        txtResultado.setText("-")
                                                        txtPlanes.setText("-")
                                                        Lguagua.clear()
                                                        nombre2.clear()
                                                        if (caldera) {


                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()


                                                        }

                                                        if (roque) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (charco) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (salinas) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (tilos) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (teneguia) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (marcos) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (volcanes) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (mercadillo) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (prois) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if(acceso == false) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                    }
                                                }
                                            }
                                    2 -> //supermercado
                                    {
                                        when (transporte) {
                                            1 -> {//guagua

                                                precio2.clear()
                                                txtResultado.setText("-")
                                                txtPlanes.setText("-")
                                                Lguagua.clear()
                                                nombre2.clear()
                                                nombres.clear()
                                                if (caldera) {


                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "la caldera")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->

                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                }

                                                if (roque) {

                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "el roque")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->

                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                }

                                                if (charco) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "charco azul"
                                                        )
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (salinas) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "salinas")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (tilos) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "los tilos")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (teneguia) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "volcan teneguia"
                                                        )
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (marcos) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "marcos y corderos"
                                                        )
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (volcanes) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "ruta de los volcanes"
                                                        )
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (mercadillo) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "mercadillo")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (prois) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "prois")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if(acceso == false) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    while (nombre2.count() > contador) {
                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                        visitas += Lguagua[contador]

                                                        precio2.add(Lguagua[contador])
                                                        contador++
                                                    }
                                                    contador = 0
                                                    num1 = precio2.sum()
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }

                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()
                                                }


                                            }
                                            2 -> {//taxi
                                                nombres.clear()
                                                precio2.clear()
                                                txtResultado.setText("-")
                                                txtPlanes.setText("-")
                                                Lguagua.clear()
                                                nombre2.clear()
                                                if (caldera) {

                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "la caldera")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->

                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                }

                                                if (roque) {

                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "el roque")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            //txt18.setText("holaaaaa")
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                }

                                                if (charco) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "charco azul"
                                                        )
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (salinas) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "salinas")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (tilos) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "los tilos")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (teneguia) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "volcan teneguia"
                                                        )
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (marcos) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "marcos y corderos"
                                                        )
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (volcanes) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "ruta de los volcanes"
                                                        )
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (mercadillo) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "mercadillo")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (prois) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "prois")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if(acceso == false) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    while (nombre2.count() > contador) {
                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                        visitas += Lguagua[contador]

                                                        precio2.add(Lguagua[contador])
                                                        contador++
                                                    }
                                                    contador = 0
                                                    num1 = precio2.sum()
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }

                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()
                                                }



                                            }
                                            3 -> {//vehiculo de alquiler
                                                nombres.clear()
                                                precio2.clear()
                                                txtResultado.setText("-")
                                                txtPlanes.setText("-")
                                                Lguagua.clear()
                                                nombre2.clear()
                                                if (caldera) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (roque) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (charco) {

                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (salinas) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (tilos) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (teneguia) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (marcos) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (volcanes) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (mercadillo) {

                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (prois) {

                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if(acceso == false){
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }




                                            }
                                        }
                                    }


                                }
                            }

                    2 -> //ahorro
                        db.collection(municipio)
                            .whereEqualTo("calidad", "ahorro")
                            .whereEqualTo("tipo", "residencia")
                            .get()
                            .addOnSuccessListener { snapshot ->
                                for (document in snapshot) {
                                    nombre.add("${document.id}\n => ")
                                    precios.add(document["precio"].toString().toInt())
                                    ubicacion.add(document["ubicacion"].toString())



                                }

                                when (comida) {
                                    1 -> //restaurante
                                        db.collection(municipio)
                                            .whereEqualTo("calidad", "ahorro")
                                            .whereEqualTo("tipo", "restaurante")
                                            .get()
                                            .addOnSuccessListener { snapshot ->
                                                for (document in snapshot) {
                                                    nombre1.add("${document.id}\n => ")
                                                    comidaV.add(
                                                        document["precio"].toString().toInt()
                                                    )
                                                    ubicacion1.add(document["ubicacion"].toString())

                                                }

                                                when (transporte) {
                                                    1 -> {//guagua
                                                        nombres.clear()
                                                        precio2.clear()
                                                        txtResultado.setText("-")
                                                        txtPlanes.setText("-")
                                                        Lguagua.clear()
                                                        nombre2.clear()
                                                        if (caldera) {

                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "la caldera")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }

                                                        }

                                                        if (roque) {

                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "el roque")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    //txt18.setText("holaaaaa")
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }

                                                        }

                                                        if (charco) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "charco azul"
                                                                )
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (salinas) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "salinas")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (tilos) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "los tilos")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (teneguia) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "volcan teneguia"
                                                                )
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (marcos) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "marcos y corderos"
                                                                )
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (volcanes) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "ruta de los volcanes"
                                                                )
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (mercadillo) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "mercadillo")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (prois) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "prois")
                                                                .whereNotEqualTo("guagua", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["guagua"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }
                                                        if (acceso == false) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {

                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")


                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1
                                                            contador = 0

                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {

                                                                num3.add(precioX[contador])
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n El precio supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }


                                                    }
                                                    2 -> {//taxi
                                                        nombres.clear()
                                                        precio2.clear()
                                                        txtResultado.setText("-")
                                                        txtPlanes.setText("-")
                                                        Lguagua.clear()
                                                        nombre2.clear()
                                                        if (caldera) {

                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "la caldera")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->

                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }

                                                        }

                                                        if (roque) {

                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "el roque")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    //txt18.setText("holaaaaa")
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()

                                                                }

                                                        }

                                                        if (charco) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "charco azul"
                                                                )
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (salinas) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "salinas")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (tilos) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "los tilos")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (teneguia) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "volcan teneguia"
                                                                )
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (marcos) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "marcos y corderos"
                                                                )
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (volcanes) {
                                                            db.collection(municipio)
                                                                .whereEqualTo(
                                                                    "sitio",
                                                                    "ruta de los volcanes"
                                                                )
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF
                                                                }
                                                        }

                                                        if (mercadillo) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "mercadillo")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")
                                                                            // "plan " + nombre[contador] + precios[contador] + " Euros\n"
                                                                            //precioFinal += "plan " + (contador + 1).toString() + "=>\n" + (comidaV[contador] * dias).toString() + " Euros\n"

                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }

                                                        if (prois) {
                                                            db.collection(municipio)
                                                                .whereEqualTo("sitio", "prois")
                                                                .whereNotEqualTo("taxi", null)
                                                                .get()
                                                                .addOnSuccessListener { snapshot ->
                                                                    for (document in snapshot) {

                                                                        try {

                                                                            Lguagua.add(
                                                                                document["taxi"].toString()
                                                                                    .toInt() * 2
                                                                            )
                                                                            nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }
                                                                    }
                                                                    while (nombre.count() > contador) {
                                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                        texto += nombres[contador]
                                                                        precio.add(precios[contador] * dias)
                                                                        contador++

                                                                    }
                                                                    contador1 = contador
                                                                    contador = 0
                                                                    while (nombre1.count() > contador) {
                                                                        try {
                                                                            nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")


                                                                            texto += nombres[contador + contador1]

                                                                            precio1.add(comidaV[contador] * dias)

                                                                            contador++
                                                                        } catch (e: Exception) {
                                                                            Log.i(TAG, e.toString())
                                                                        }


                                                                    }
                                                                    contador1 = contador + contador1
                                                                    contador = 0

                                                                    while (nombre2.count() > contador) {
                                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                        visitas += Lguagua[contador]

                                                                        precio2.add(Lguagua[contador])
                                                                        contador++
                                                                    }
                                                                    contador = 0
                                                                    num1 = precio2.sum()
                                                                    precioX = precio.zip(
                                                                        precio1,
                                                                        Int::plus
                                                                    ) as ArrayList<Int>

                                                                    while (precioX.count() > contador) {
                                                                        Log.i(TAG, num2.toString())
                                                                        num3.add(precioX[contador] + num1)
                                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                        num2 = num3[contador]
                                                                        contador++

                                                                    }

                                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                                    txtPlanes.setText(texto + actividades)
                                                                    contador = 0
                                                                    texto = ""
                                                                    actividades = ""
                                                                    contador1 = 0
                                                                    precio.clear()
                                                                    precio1.clear()
                                                                    precio2.clear()
                                                                    if (num2 > presupuesto) {
                                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                                    }
                                                                    num3.clear()
                                                                    planF.clear()
                                                                }
                                                        }
                                                        if (acceso == false) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {

                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")


                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1
                                                            contador = 0

                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {

                                                                num3.add(precioX[contador])
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n El precio supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                    }
                                                    3 -> {//vehiculo de alquiler
                                                        nombres.clear()
                                                        precio2.clear()
                                                        txtResultado.setText("-")
                                                        txtPlanes.setText("-")
                                                        Lguagua.clear()
                                                        nombre2.clear()
                                                        if (caldera) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()


                                                        }

                                                        if (roque) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (charco) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (salinas) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (tilos) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (teneguia) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                        if (marcos) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (volcanes) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (mercadillo) {
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if (prois) {

                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                        if(acceso == false) {

                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            while (nombre1.count() > contador) {
                                                                try {
                                                                    nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre1[contador] + comidaV[contador] + " Euros\n" + "Direccion => \n" + ubicacion1[contador] + "\n \n")

                                                                    texto += nombres[contador + contador1]

                                                                    precio1.add(comidaV[contador] * dias)

                                                                    contador++
                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }


                                                            }
                                                            contador1 = contador + contador1



                                                            visitas = (100 * dias)
                                                            contador = 0
                                                            num1 = visitas
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>
                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++
                                                            }
                                                            actividades =
                                                                "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()

                                                        }

                                                    }
                                                }
                                            }
                                    2 -> //supermercado
                                    {
                                        when (transporte) {
                                            1 -> {//guagua

                                                precio2.clear()
                                                txtResultado.setText("-")
                                                txtPlanes.setText("-")
                                                Lguagua.clear()
                                                nombre2.clear()
                                                nombres.clear()
                                                if (caldera) {


                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "la caldera")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->

                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                }

                                                if (roque) {

                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "el roque")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->

                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                }

                                                if (charco) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "charco azul"
                                                        )
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (salinas) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "salinas")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (tilos) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "los tilos")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (teneguia) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "volcan teneguia"
                                                        )
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (marcos) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "marcos y corderos"
                                                        )
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (volcanes) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "ruta de los volcanes"
                                                        )
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (mercadillo) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "mercadillo")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (prois) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "prois")
                                                        .whereNotEqualTo("guagua", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["guagua"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio de la guagua (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if(acceso == false) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    while (nombre2.count() > contador) {
                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                        visitas += Lguagua[contador]

                                                        precio2.add(Lguagua[contador])
                                                        contador++
                                                    }
                                                    contador = 0
                                                    num1 = precio2.sum()
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }

                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()
                                                }


                                            }
                                            2 -> {//taxi
                                                nombres.clear()
                                                precio2.clear()
                                                txtResultado.setText("-")
                                                txtPlanes.setText("-")
                                                Lguagua.clear()
                                                nombre2.clear()
                                                if (caldera) {

                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "la caldera")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->

                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                }

                                                if (roque) {

                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "el roque")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->

                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }

                                                }

                                                if (charco) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "charco azul"
                                                        )
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (salinas) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "salinas")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (tilos) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "los tilos")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (teneguia) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "volcan teneguia"
                                                        )
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (marcos) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "marcos y corderos"
                                                        )
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (volcanes) {
                                                    db.collection(municipio)
                                                        .whereEqualTo(
                                                            "sitio",
                                                            "ruta de los volcanes"
                                                        )
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (mercadillo) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "mercadillo")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " +planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if (prois) {
                                                    db.collection(municipio)
                                                        .whereEqualTo("sitio", "prois")
                                                        .whereNotEqualTo("taxi", null)
                                                        .get()
                                                        .addOnSuccessListener { snapshot ->
                                                            for (document in snapshot) {

                                                                try {

                                                                    Lguagua.add(
                                                                        document["taxi"].toString()
                                                                            .toInt() * 2
                                                                    )
                                                                    nombre2.add("${document.id}\n Precio del taxi (ida y vuelta)=> ")

                                                                } catch (e: Exception) {
                                                                    Log.i(TAG, e.toString())
                                                                }
                                                            }
                                                            while (nombre.count() > contador) {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                                texto += nombres[contador]
                                                                precio.add(precios[contador] * dias)
                                                                contador++

                                                            }
                                                            contador1 = contador
                                                            contador = 0
                                                            try {
                                                                nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                                texto += nombres[contador1]
                                                                precio1.add(15 * dias)


                                                            } catch (e: Exception) {
                                                                Log.i(TAG, e.toString())
                                                            }

                                                            while (nombre2.count() > contador) {
                                                                actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                                visitas += Lguagua[contador]

                                                                precio2.add(Lguagua[contador])
                                                                contador++
                                                            }
                                                            contador = 0
                                                            num1 = precio2.sum()
                                                            precioX = precio.zip(
                                                                precio1,
                                                                Int::plus
                                                            ) as ArrayList<Int>

                                                            while (precioX.count() > contador) {
                                                                Log.i(TAG, num2.toString())
                                                                num3.add(precioX[contador] + num1)
                                                                planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                                num2 = num3[contador]
                                                                contador++

                                                            }

                                                            txtResultado.setText("precio estimado: " + planF + "euros")
                                                            txtPlanes.setText(texto + actividades)
                                                            contador = 0
                                                            texto = ""
                                                            actividades = ""
                                                            contador1 = 0
                                                            precio.clear()
                                                            precio1.clear()
                                                            precio2.clear()
                                                            if (num2 > presupuesto) {
                                                                txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                            }
                                                            num3.clear()
                                                            planF.clear()
                                                        }
                                                }

                                                if(acceso == false) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    while (nombre2.count() > contador) {
                                                        actividades += "-" + nombre2[contador] + Lguagua[contador] + " Euros\n \n"
                                                        visitas += Lguagua[contador]

                                                        precio2.add(Lguagua[contador])
                                                        contador++
                                                    }
                                                    contador = 0
                                                    num1 = precio2.sum()
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }

                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()
                                                }



                                            }
                                            3 -> {//vehiculo de alquiler
                                                nombres.clear()
                                                precio2.clear()
                                                txtResultado.setText("-")
                                                txtPlanes.setText("-")
                                                Lguagua.clear()
                                                nombre2.clear()
                                                if (caldera) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (roque) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (charco) {

                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (salinas) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (tilos) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (teneguia) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (marcos) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (volcanes) {
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (mercadillo) {

                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if (prois) {

                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }

                                                if(acceso == false){
                                                    while (nombre.count() > contador) {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + nombre[contador] + precios[contador] + " Euros\n" + "Direccion =>\n" + ubicacion[contador] + "\n \n")
                                                        texto += nombres[contador]
                                                        precio.add(precios[contador] * dias)
                                                        contador++

                                                    }
                                                    contador1 = contador
                                                    contador = 0
                                                    try {
                                                        nombres.add("-Plan " + (contador + 1).toString() + ":\n" + "Supermercado\n => " + 15 + " Euros\n" + "\n")


                                                        texto += nombres[contador1]
                                                        precio1.add(15 * dias)


                                                    } catch (e: Exception) {
                                                        Log.i(TAG, e.toString())
                                                    }

                                                    visitas = (100 * dias)
                                                    contador = 0
                                                    num1 = visitas
                                                    precioX = precio.zip(
                                                        precio1,
                                                        Int::plus
                                                    ) as ArrayList<Int>

                                                    while (precioX.count() > contador) {
                                                        Log.i(TAG, num2.toString())
                                                        num3.add(precioX[contador] + num1)
                                                        planF.add("plan " + (contador + 1) + "=> " + num3[contador] +" ")
                                                        num2 = num3[contador]
                                                        contador++

                                                    }
                                                    actividades =
                                                        "-" + "vehiculo de alquiler " + (100 * dias) + " Euros\n \n"
                                                    txtResultado.setText("precio estimado: " + planF + "euros")
                                                    txtPlanes.setText(texto + actividades)
                                                    contador = 0
                                                    texto = ""
                                                    actividades = ""
                                                    contador1 = 0
                                                    precio.clear()
                                                    precio1.clear()
                                                    precio2.clear()
                                                    if (num2 > presupuesto) {
                                                        txtResultado.setText("precio estimado: " + planF + "euros" + "\n Al menos uno de los precios supera el presupuesto")
                                                    }
                                                    num3.clear()
                                                    planF.clear()

                                                }




                                            }
                                        }
                                    }


                                }
                            }
                }
                }catch (e: Exception) {
                    Log.i(TAG, e.toString())
                }

            }
        }


    }
}




