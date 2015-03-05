package com.android.material.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import com.android.material.Information;
import com.android.material.R;

/**
 * Created by Windows on 22-12-2014.
 */
public class AdapterVivz extends RecyclerView.Adapter<AdapterVivz.MyViewHolder> {
    List<Information> data = Collections.emptyList();
    private LayoutInflater inflater;
    private Context context;

    public AdapterVivz(Context context, List<Information> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.custom_row, parent, false);

        MyViewHolder holder = new MyViewHolder(view);
        Log.d("MyViewHolder","Criou view holder");
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Information current = data.get(position);
        holder.title.setText(current.title);
        holder.icon.setImageResource(current.iconId);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView title;
        ImageView icon;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.listText);
            icon = (ImageView) itemView.findViewById(R.id.listIcon);

            title.setOnClickListener(this);
            icon.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Log.d("MyViewHolder","Clicou icon/text "+getPosition());
            
//            delete(getPosition());
        }
    }
}
