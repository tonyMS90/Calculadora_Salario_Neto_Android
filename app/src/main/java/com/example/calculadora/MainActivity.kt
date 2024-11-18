package com.example.calculadora

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Recogida de elementos


            val salBruto = findViewById<EditText>(R.id.editSalario)
            val pagas = findViewById<EditText>(R.id.editPagas)
            val edad = findViewById<EditText>(R.id.editEdad)
            val grupoProfesional = findViewById<EditText>(R.id.editGrupo)
            val discapacidad = findViewById<EditText>(R.id.editDisca)
            val estadoCivil = findViewById<EditText>(R.id.editEstado)
            val hijos = findViewById<EditText>(R.id.editHijos)




        //inicializo botón
        val calcularBoton = findViewById<Button>(R.id.calcular)

        //Resultado al hacer click en el boton calcular
        calcularBoton.setOnClickListener {
            val calcSalario = salBruto.text.toString().toDoubleOrNull() ?: 0.0
            val calcPagas = pagas.text.toString().toIntOrNull() ?: 0
            val calcEdad = edad.text.toString().toIntOrNull() ?: 0
            val calcGrupo = grupoProfesional.text.toString()
            val calcDiscapacidad = discapacidad.text.toString().toDoubleOrNull() ?: 0.0
            val calcEstadoCivil = estadoCivil.text.toString()
            val calcHijos = hijos.text.toString().toIntOrNull() ?: 0

            //cálculos
            val irpf = calcularIrpf(calcSalario)
            val deducciones = calcularDeducciones(calcHijos,calcDiscapacidad,calcEstadoCivil)
            val salarioNeto = calcSalario - (calcSalario * irpf) - deducciones

            //envio de resultados a la pagina siguiente(otro main)

            val intent = Intent(this, MainActivityResultado:: class.java)
            intent.putExtra("salarioBruto", calcSalario)
            intent.putExtra("salarioNeto", salarioNeto)
            intent.putExtra("retencionIrpf", irpf *100)
            intent.putExtra("deducciones", deducciones)

            //Inicio en el otro mainActivity

            startActivity(intent)

        }
    }


    //Metodos para realizar los calculos

    //IRPF según salario
    private fun calcularIrpf(calcSalario: Double) : Double{
        return when{
            calcSalario < 12450 -> 0.095
            calcSalario >= 12450 && calcSalario < 20199 -> 0.12
            calcSalario >= 20200 && calcSalario < 35199 -> 0.15
            calcSalario >= 35200 && calcSalario < 59999 -> 0.185
            calcSalario >= 60000 && calcSalario < 299999 -> 0.225
            else -> 0.245
        }
    }
    //deducciones
    private fun calcularDeducciones(calcHijos:Int, calcDiscapacidad:Double, calcEstadoCivil:String):Double{
        var deducciones = 0.0

        //deduccion numero hijos
        deducciones += calcHijos * 500

        //deducción discapacidad

        when{
            calcDiscapacidad < 33 -> deducciones += 0
            calcDiscapacidad >= 33 && calcDiscapacidad < 65 -> deducciones += 1000
            calcDiscapacidad > 65 -> deducciones += 2000
        }

        //deduccion estado civil
        when (calcEstadoCivil){
            "casado" -> deducciones +=1500
            "divorciado" -> deducciones += 1000
            "viudo" -> deducciones += 2000
            else -> deducciones += 0
        }
        return deducciones
    }
}