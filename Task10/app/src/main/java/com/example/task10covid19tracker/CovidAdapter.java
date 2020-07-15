package com.example.task10covid19tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CovidAdapter extends RecyclerView.Adapter<CovidAdapter.MyViewHolder> {

    Context myCt;
    List<Covid> myCovid;
    public CovidAdapter(Context ct, List<Covid> covidList) {

        myCt=ct;
        myCovid=covidList;
    }

    @NonNull
    @Override
    public CovidAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(myCt).inflate(R.layout.item,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidAdapter.MyViewHolder holder, int position) {

        Covid covid= myCovid.get(position);
        holder.tv_date.setText(covid.getDate());
        holder.tv_active.setText(covid.getActive());
        holder.tv_recovered.setText(covid.getRecovered());
        holder.tv_deaths.setText(covid.getDeaths());

    }

    @Override
    public int getItemCount() {
        return myCovid.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_date,tv_active,tv_recovered,tv_deaths;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_date=itemView.findViewById(R.id.date);
            tv_active=itemView.findViewById(R.id.active);
            tv_recovered=itemView.findViewById(R.id.recover);
            tv_deaths=itemView.findViewById(R.id.deaths);

        }
    }
}
