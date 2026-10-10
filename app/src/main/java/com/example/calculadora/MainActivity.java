package com.example.calculadora;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // Declaración de componentes de la interfaz de usuario
    private EditText etNumero1;
    private EditText etNumero2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Enlazar componentes del XML con Java mediante sus IDs
        etNumero1 = findViewById(R.id.etNumero1);
        etNumero2 = findViewById(R.id.etNumero2);
        Button btnSumar = findViewById(R.id.btnSumar);
        Button btnRestar = findViewById(R.id.btnRestar);
        Button btnMultiplicar = findViewById(R.id.btnMultiplicar);
        Button btnDividir = findViewById(R.id.btnDividir);

        // 2. Configurar eventos de clic para cada botón
        btnSumar.setOnClickListener(v -> calcular("+"));
        btnRestar.setOnClickListener(v -> calcular("-"));
        btnMultiplicar.setOnClickListener(v -> calcular("×"));
        btnDividir.setOnClickListener(v -> calcular("÷"));
    }

    /**
     * Valida las entradas numéricas y ejecuta la operación seleccionada.
     */
    private void calcular(String operacion) {
        String texto1 = etNumero1.getText().toString().trim();
        String texto2 = etNumero2.getText().toString().trim();

        // Validación 1: Verificar que los campos no estén vacíos
        if (texto1.isEmpty()) {
            etNumero1.setError("Ingrese el primer número");
            etNumero1.requestFocus();
            return;
        }

        if (texto2.isEmpty()) {
            etNumero2.setError("Ingrese el segundo número");
            etNumero2.requestFocus();
            return;
        }

        double num1;
        double num2;

        // Validación 2: Verificar que se hayan ingresado números válidos
        try {
            num1 = Double.parseDouble(texto1);
            num2 = Double.parseDouble(texto2);
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Por favor ingrese valores numéricos válidos", Toast.LENGTH_SHORT).show();
            return;
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
                // Validación 3: Evitar división entre cero
                if (num2 == 0) {
                    etNumero2.setError("No se puede dividir entre cero");
                    etNumero2.requestFocus();
                    Toast.makeText(this, "Error: División por cero", Toast.LENGTH_SHORT).show();
                    return;
                }
                resultado = num1 / num2;
                break;
        }

        // 3. Crear el objeto mediante el CONSTRUCTOR de la clase Resultado
        Resultado objetoResultado = new Resultado(num1, num2, operacion, resultado);

        // 4. Pasar el objeto a la segunda pantalla (ResultadoActivity)
        Intent intent = new Intent(MainActivity.this, ResultadoActivity.class);
        intent.putExtra("OBJETO_RESULTADO", objetoResultado);
        startActivity(intent);
    }
}
