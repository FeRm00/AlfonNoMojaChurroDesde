package com.kikisnight.alfonnomojachurrodesde;

import android.media.MediaPlayer;
import android.net.ParseException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.os.Handler;

import java.text.DecimalFormat;


public class MainActivity extends AppCompatActivity {

    String fechaInicio = "15-01-2017 11:30:00";
    Handler handler;
    Runnable obtenerFecha;

    /**
     * Variables para comparar
     */

        int bixoCasosAño = 3366;
        double apollo11TiempoViaje = 8 * (24*60*60) + 3 * (60*60) + 18 * (60) + 20;
        int numeroPokemonsDia = 3472 / 221;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Runnable
        handler = new Handler();
        obtenerFecha = new ObtenerFecha();
        handler.post(obtenerFecha);

    }


    private void mostrarDiferencia(String message) {
        TextView mostrarTextView = (TextView) findViewById(R.id.diferencia);
        mostrarTextView.setText(message);
    }

    private void mostrarNumeroBixo(String message) {
        TextView mostrarTextView = (TextView) findViewById(R.id.textoBixoTiempo);
        mostrarTextView.setText(message);
    }

    private void mostrarNumeroApollo11(String message) {
        TextView mostrarTextView = (TextView) findViewById(R.id.textoApollo11Tiempo);
        mostrarTextView.setText(message);
    }

    private void mostrarNumeroPokemons(String message) {
        TextView mostrarTextView = (TextView) findViewById(R.id.textoPokemonsTiempo);
        mostrarTextView.setText(message);
    }

    class ObtenerFecha implements Runnable{
        @Override
        public void run() {
            try {
                //Fecha de Inicio
                SimpleDateFormat stringDate1 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                Date DateInicio = stringDate1.parse(fechaInicio);
                Calendar CalendarInicio = new GregorianCalendar();
                CalendarInicio.setTime(DateInicio);

                //Obtener fecha actual
                Calendar CalendarActual = new GregorianCalendar();
                //Date DateActual = CalendarActual.getTime();
                SimpleDateFormat stringDate2 = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                //String fechaActual = stringDate2.format(DateActual);

                // tomamos la instancia del tipo de calendario

                Calendar stringCalendarActual = Calendar.getInstance();


                // obtenemos el valor de las fechas en milisegundos
                long milisegundos1 = CalendarInicio.getTimeInMillis();
                long milisegundos2 = CalendarActual.getTimeInMillis();
                // tomamos la diferencia
                long diffMilisegundos = milisegundos2 - milisegundos1;


                // calcular la diferencia en segundos
                long diffSegundos = Math.abs(diffMilisegundos / 1000);
                long diferenciasegundos = diffSegundos % 60;

                // calcular la diferencia en minutos
                long diffMinutos = Math.abs(diffMilisegundos / (60 * 1000));
                long restominutos = diffMinutos % 60;

                // calcular la diferencia en horas
                long diffHoras = (diffMilisegundos / (60 * 60 * 1000));
                long restohoras = diffHoras % 24;

                // calcular la diferencia en dias
                long diffdias = Math.abs(diffMilisegundos / (24 * 60 * 60 * 1000));

                String diferencia = diffdias + " días " + restohoras + " horas " + restominutos + " minutos y " + diferenciasegundos + " segundos";

                /**
                 * Número de casos de bixo hasta ahora
                 * Número de misiones Apollo11
                 */

                //Formato de dos decimales
                DecimalFormat formatodecimal = new DecimalFormat("0.00");

                long bixo = bixoCasosAño * diffSegundos / (365*24*60*60);
                String bixoString = bixo + "";

                double apollo11 = diffSegundos / apollo11TiempoViaje;
                String apollo11String = formatodecimal.format(apollo11);

                long pokemons = numeroPokemonsDia * diffdias;
                String pokemonsString = pokemons + "";

                //Llamada para mostrar fechas
                mostrarDiferencia(diferencia);
                //Llamada para mostrar número de Bixo
                mostrarNumeroBixo(bixoString);
                //Llamada para mostrar número de Apollo11
                mostrarNumeroApollo11(apollo11String);
                //Lammada para mostrar número de Pokemons
                mostrarNumeroPokemons(pokemonsString);

            } catch (ParseException e) {
                //Log.e(TAG, “Función obtenerFecha: Error Parse “ e);
            } catch (Exception e) {
                //Log.e(TAG, “Función obtenerFecha: Error “ + e);
            }
            Log.d("Handlers", "Called on main thread");

            // Run the above code block on the main thread after 2 seconds
            handler.postDelayed(ObtenerFecha.this, 1000);
        }
    }
}