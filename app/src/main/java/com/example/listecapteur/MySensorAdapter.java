package com.example.listecapteur;

import android.content.Context;
import android.hardware.Sensor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MySensorAdapter extends ArrayAdapter<Sensor> {

    private int textViewResourceId;

    private static class ViewHolder {
        private TextView itemView;
    }

    /**
     * Les capteurs a afficher
     * @param context Dans quel contexte les capteurs sont.
     * @param textViewResourceId Les ressources du capteurs
     * @param items La liste des capteurs
     */
    public MySensorAdapter(Context context, int textViewResourceId, List<Sensor> items) {
        super(context, textViewResourceId, items);
        this.textViewResourceId = textViewResourceId;
    }

    /**
     * Afficher le capteur
     * @param position la position du capteur
     * @param convertView Convertissement de la view en texte.
     * @param parent Est un groupe de view.
     * @return Le capteur avec son texte.
     */
    public View getView (int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(this.getContext()).inflate(textViewResourceId, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.itemView = (TextView) convertView.findViewById(R.id.content);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Sensor item = getItem(position);

        if (item != null) {
            viewHolder.itemView.setText(" Name : " + item.getName() +"\n\n Description : "
                    + item.getType());
        }

        return convertView;
    }


}
