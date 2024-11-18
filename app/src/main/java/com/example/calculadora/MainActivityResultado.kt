package com.example.calculadora

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivityResultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_resultado)

        // Recibir los resultados del Intent
        val salarioBruto = intent.getDoubleExtra("salarioBruto", 0.0)
        val salarioNeto = intent.getDoubleExtra("salarioNeto", 0.0)
        val irpf = intent.getDoubleExtra("retencionIrpf", 0.0)
        val deducciones = intent.getDoubleExtra("deducciones", 0.0)

        // Mostrar los resultados en los TextViews
        val textResultBruto = findViewById<TextView>(R.id.textResultBruto)
        val textResultNeto = findViewById<TextView>(R.id.textResultNeto)
        val textResultIrpf = findViewById<TextView>(R.id.textResultIrpf)
        val textResultDeduc = findViewById<TextView>(R.id.textResultDeduc)

        // Establecer los textos
        //Se aplica %.2f para que solo muestre dos decimales
        textResultBruto.text = "Salario Bruto: %.2f €".format(salarioBruto)
        textResultNeto.text = "Salario Neto: %.2f €".format(salarioNeto)
        textResultIrpf.text = "IRPF: %.2f%%".format(irpf)
        textResultDeduc.text = "Deducciones: %.2f €".format(deducciones)


    }
}