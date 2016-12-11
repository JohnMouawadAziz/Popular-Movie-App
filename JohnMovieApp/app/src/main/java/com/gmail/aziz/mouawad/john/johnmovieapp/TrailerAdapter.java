package com.gmail.aziz.mouawad.john.johnmovieapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.squareup.picasso.Picasso;

import java.util.List;


public class TrailerAdapter extends ArrayAdapter<Trailer> {

    public TrailerAdapter(Context context, List<Trailer> trailers) {
        super(context, 0, trailers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Trailer TM=getItem(position);

        String vedioImage="http://img.youtube.com/vi/"+TM.getKey()+"/0.jpg";

        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.trailer_list_item, parent, false);
        }
        Button trilerVedio= (Button) convertView.findViewById(R.id.trailer_button);
        trilerVedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://www.youtube.com/watch?v=" + TM.getKey()));
                    getContext().startActivity(intent);



//                String youtubeVedio= TM.getKey().toString();
//                Intent intent=new Intent(getContext(),YoutubeTrailar.class);
//                intent.putExtra("youtube_Key",youtubeVedio);
//                getContext().startActivity(intent);
//                Toast.makeText(getContext(),""+ youtubeVedio,Toast.LENGTH_SHORT).show();



            }
        });
        trilerVedio.setFocusable(false);
        ImageView imagetrailer= (ImageView) convertView.findViewById(R.id.imageView2);
        Picasso.with(getContext())
                .load(vedioImage)
                .placeholder(R.color.colorAccent)
                .into(imagetrailer);
        imagetrailer.setFocusable(false);

        TextView trailerText = (TextView) convertView.findViewById(R.id.trailer_name);
        trailerText.setText(getContext().getString(R.string.trailer,(position + 1)));
        trailerText.setFocusable(false);
        return convertView;
    }
}
