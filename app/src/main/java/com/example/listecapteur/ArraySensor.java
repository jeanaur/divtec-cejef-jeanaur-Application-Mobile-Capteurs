package com.example.listecapteur;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ArraySensor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.array_sensor);

        // Active le menu
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Dis que l'on aimerait avoir une liste des capteurs de notre téléphone.
        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ALL);
        ListView list = (ListView) findViewById(R.id.list);
        list.setAdapter(new MySensorAdapter(this, R.layout.row_item, sensors));
    }

}
