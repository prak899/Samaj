package com.siddhi.softwares.sutsamaj.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.siddhi.softwares.sutsamaj.Forms.MembersActivity;
import com.siddhi.softwares.sutsamaj.Forms.SurveyForm;
import com.siddhi.softwares.sutsamaj.Login.LoginActivity;
import com.siddhi.softwares.sutsamaj.Model.Survey;
import com.siddhi.softwares.sutsamaj.R;
import com.siddhi.softwares.sutsamaj.SpalshScreen;

import java.util.ArrayList;


public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder>{

    private final ArrayList<Survey> cities;

    public Adapter(ArrayList<Survey> cities) {
        this.cities = cities;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row_item, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Survey city = cities.get(position);

        holder.name.setText(city.getName());
        holder.description.setText(city.getSubName());

    }

    @Override
    public int getItemCount() {
        if (cities != null) {
            return cities.size();
        } else {
            return 0;
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView name;
        public final TextView description;
        private final Context context;

        public ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.formTitleTextView);
            description = view.findViewById(R.id.formCategoryTextView);
            context = view.getContext();

            itemView.setOnClickListener(view1 -> {
                int itemPosition = getLayoutPosition();
                final Intent intent;
                switch (itemPosition){
                    case 0:
                        intent =  new Intent(context, SurveyForm.class);
                        break;
                    case 1:
                        intent =  new Intent(context, SurveyForm.class);
                        break;
                    default:
                        intent =  new Intent(context, SurveyForm.class);
                        break;
                }
                context.startActivity(intent);
            });
        }
    }

}