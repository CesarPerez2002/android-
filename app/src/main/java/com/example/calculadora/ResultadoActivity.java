package com.example.calculadora;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultadoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        TextView tvDetalleOperacion = findViewById(R.id.tvDetalleOperacion);
        TextView tvValorResultado = findViewById(R.id.tvValorResultado);
        Button btnVolver = findViewById(R.id.btnVolver);

        Resultado resultado = (Resultado) getIntent().getSerializableExtra("OBJETO_RESULTADO");

        if (resultado != null) {
            String detalle;
            if ("cos".equalsIgnoreCase(resultado.getOperacion())) {
                detalle = "Operación: cos(" + resultado.getNumero1() + "°)";
            } else {
                detalle = "Operación: " + resultado.getNumero1() + " " + resultado.getOperacion() + " " + resultado.getNumero2();
            }
            tvDetalleOperacion.setText(detalle);
            tvValorResultado.setText(String.valueOf(resultado.getResultado()));
        }

        btnVolver.setOnClickListener(v -> finish());
    }
}
