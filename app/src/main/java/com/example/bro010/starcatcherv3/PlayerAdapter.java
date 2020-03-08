package com.example.bro010.starcatcherv3;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by bro016 on 05/03/2018.
 */

public class PlayerAdapter  extends ArrayAdapter<Player> {

        Context context;
        List<Player> objects;

        public PlayerAdapter(Context context, int resource, List<Player> objects) {
            super(context, resource, objects);
            this.objects = objects;
            this.context = context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = ((Activity) context).getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.onerow, parent, false);

            TextView tvName = (TextView) view.findViewById(R.id.tvName);
            TextView tvscore = (TextView) view.findViewById(R.id.tvscore);

            Player temp = objects.get(position);
            tvscore.setText(String.valueOf(temp.getScore()));
            tvName.setText(temp.getPlayerName());

            return view;
        }
    }


