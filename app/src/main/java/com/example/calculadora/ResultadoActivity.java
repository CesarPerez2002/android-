package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    private TextView tvDetalleOperacion;
    private TextView tvValorResultado;
    private Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        tvDetalleOperacion = findViewById(R.id.tvDetalleOperacion);
        tvValorResultado = findViewById(R.id.tvValorResultado);
        btnVolver = findViewById(R.id.btnVolver);

        // Obtener el objeto Resultado enviado mediante Intent
        Resultado resultado = (Resultado) getIntent().getSerializableExtra("OBJETO_RESULTADO");

        if (resultado != null) {
            // Mostrar los datos almacenados en el objeto que se creó con el constructor
            String detalle = "Operación: " + resultado.getNumero1() + " " + resultado.getOperacion() + " " + resultado.getNumero2();
            tvDetalleOperacion.setText(detalle);
            tvValorResultado.setText(String.valueOf(resultado.getResultado()));
        }

        // Botón para volver a la pantalla anterior
        btnVolver.setOnClickListener(v -> finish());
    }
}
