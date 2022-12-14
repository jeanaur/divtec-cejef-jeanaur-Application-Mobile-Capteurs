package com.example.listecapteur;

import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MotionPhone extends AppCompatActivity implements SensorEventListener {
    // Création des variables
    private SensorManager sensorManager;

    private TextView txtView_axeY;
    private TextView txtView_axeX;

    private ImageView bulle;

    private int blockPosX = 0;
    private int blockPosY = 0;

    private int display_height;
    private int display_width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bulle_niveau);

        // Menu a ouvrir
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // Récupération des données
        // Eléments XML
        bulle = findViewById(R.id.bulle);
        txtView_axeY = findViewById(R.id.txtView_axeY);
        txtView_axeX = findViewById(R.id.txtView_axeX);
        // Dimensions de l'écran
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        //Point size = new Point();
        //display.getSize(size);

        // Hauteur et largeur de l'écran
        display_height = displayMetrics.heightPixels - 200;
        display_width = displayMetrics.widthPixels;

        // Service de gestion des capteurs
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY),
                SensorManager.SENSOR_DELAY_FASTEST);

    }

    /**
     * Méthode appelée lorsque le capteur détecte un changement
     *
     * @param event événement du changement
     */
    public void onSensorChanged(SensorEvent event) {
        // Déplacer le block normallement
        moveBlock(event.values[0]*100 / SensorManager.GRAVITY_EARTH, event.values[1]*100 / SensorManager.GRAVITY_EARTH, event.values[2]);
        // Affiche la position de l'image sur les axes X & Y
        //txtView_axeX.setText(Integer.toString(blockPosX));
        //txtView_axeY.setText(Integer.toString(blockPosY ));
        txtView_axeX.setText((int)(event.values[0]*100 / SensorManager.GRAVITY_EARTH)+"%");
        txtView_axeY.setText((int)(event.values[1]*100 / SensorManager.GRAVITY_EARTH)+"%");



    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /**
     * Méthode qui fait bouger le block
     *
     * @param x valeur de l'accélération selon l'axe X
     * @param y valeur de l'accélération selon l'axe Y
     * @param z valeur de l'accélération selon l'axe Z
     */
    public void moveBlock(float x, float y, float z) {
        // Récupérer les dimensions du block
        int block_height = bulle.getHeight();
        int block_width = bulle.getWidth();

        // Calculer la force
        float force = (float) Math.sqrt(x * x + y * y + z * z);

        // Modifier la puissance de la force
        force = force / 10;

        // Calculer les nouvelles coordonnées de l'image
        blockPosX = (int) ( blockPosX - x * force );
        blockPosY = (int) ( blockPosY + y * force );

        // Si l'image sort de l'écran, la positionner sur l'écran
        if (blockPosX < 0) {
            blockPosX = 0;
        } else if (blockPosX > display_width - block_width) {
            blockPosX = display_width - block_width;
        }
        if (blockPosY < 0) {
            blockPosY = 0;
        } else if (blockPosY > display_height - block_height) {
            blockPosY = display_height - block_height;
        }

        // Déplacer l'image
        bulle.setX(blockPosX);
        bulle.setY(blockPosY);

    }
}
