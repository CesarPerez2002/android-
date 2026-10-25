package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText etNumero1;
    private EditText etNumero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        Button btnSumar = findViewById(R.id.btnSumar);
        Button btnRestar = findViewById(R.id.btnRestar);
        Button btnMultiplicar = findViewById(R.id.btnMultiplicar);
        Button btnDividir = findViewById(R.id.btnDividir);
        Button btnCoseno = findViewById(R.id.btnCoseno);

        btnSumar.setOnClickListener(v -> calcular("+"));
        btnRestar.setOnClickListener(v -> calcular("-"));
        btnMultiplicar.setOnClickListener(v -> calcular("×"));
        btnDividir.setOnClickListener(v -> calcular("÷"));
        btnCoseno.setOnClickListener(v -> calcular("cos"));
    }

    private void calcular(String operacion) {
        String texto1 = etNumero1.getText().toString().trim();
        String texto2 = etNumero2.getText().toString().trim();

        if (texto1.isEmpty()) {
            etNumero1.setError("Ingrese el primer número/ángulo");
            etNumero1.requestFocus();
            return;
        }

        double num1;
        try {
            num1 = Double.parseDouble(texto1);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingrese un valor numérico válido", Toast.LENGTH_SHORT).show();
            return;
        }

        double num2 = 0;

        // Para las operaciones binarias (+, -, ×, ÷) se valida también el segundo número
        if (!operacion.equals("cos")) {
            if (texto2.isEmpty()) {
                etNumero2.setError("Ingrese el segundo número");
                etNumero2.requestFocus();
                return;
            }

            try {
                num2 = Double.parseDouble(texto2);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Por favor ingrese un valor numérico válido en el segundo campo", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        double resultado = 0;

        switch (operacion) {
            case "+":
                resultado = num1 + num2;
                break;
            case "-":
                resultado = num1 - num2;
                break;
            case "×":
                resultado = num1 * num2;
                break;
            case "÷":
                if (num2 == 0) {
                    etNumero2.setError("No se puede dividir entre cero");
                    etNumero2.requestFocus();
                    Toast.makeText(this, "Error: División por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                resultado = num1 / num2;
                break;
            case "cos":
                // Se calcula el coseno asumiendo que el ángulo ingresado está en grados
                resultado = Math.cos(Math.toRadians(num1));
                break;
        }

        Resultado objetoResultado = new Resultado(num1, num2, operacion, resultado);

        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        intent.putExtra("OBJETO_RESULTADO", objetoResultado);
        startActivity(intent);
    }
}
