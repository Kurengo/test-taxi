package com.example.test_task.ui.activities;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test_task.R;
import com.example.test_task.models.City;

import java.util.List;

public class CitiesAdapter extends RecyclerView.Adapter<CitiesAdapter.ViewHolder> {
    private ItemClickListener itemClickListener;
    private List<City> cityList;
    private Context context;

    public CitiesAdapter(Context context) {
        this.context = context;
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public void setCityList(List<City> cityList) {
        this.cityList = cityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.city_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("log", "onBindViewHolder:------------- "+cityList.size());
        final City city = cityList.get(position);
        String cityName = city.getCityName();
        if (TextUtils.isEmpty(cityName)) {
            cityName = context.getString(R.string.empty_city);
        }
        holder.textViewCity.setText(cityName);
        if (itemClickListener != null) {
            holder.parentLinearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (itemClickListener != null) {
                        itemClickListener.onItemClick(city);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return cityList != null ? cityList.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCity;
        private LinearLayout parentLinearLayout;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewCity = itemView.findViewById(R.id.cityTextView);
            parentLinearLayout = itemView.findViewById(R.id.parentLinearLayout);
        }
    }

    public interface ItemClickListener {
        void onItemClick(City city);
    }
}