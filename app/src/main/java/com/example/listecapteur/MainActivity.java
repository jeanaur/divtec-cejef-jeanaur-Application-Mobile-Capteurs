package com.example.listecapteur;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Création du menu
     * @param menu le menu à créer
     * @return retourne si le menu a bien été créé
     */
    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    /**
     * Options du menu à selectionner
     * @param item La page du menu à sélectionner
     * @return La page qui a été sélectionnée pour l'ouvrir
     */
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {

        switch (item.getItemId()){
            case R.id.list:
                Intent intentListe = new Intent(this, ArraySensor.class);
                this.startActivity(intentListe);
                return true;
            case R.id.bulle:
                Intent intentBulle = new Intent(this, MotionPhone.class);
                this.startActivity(intentBulle);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}