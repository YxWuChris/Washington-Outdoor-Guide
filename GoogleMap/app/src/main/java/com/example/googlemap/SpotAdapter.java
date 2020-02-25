package com.example.googlemap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.ViewHolder> {

    private List<Spot> mSpotList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView spotName;

        public ViewHolder(View view) {
            super(view);

            spotName = (TextView) view.findViewById(R.id.spotName);
        }

    }

    public SpotAdapter(List<Spot> spotList) {
        mSpotList = spotList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spot_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Spot spot = mSpotList.get(position);
        holder.spotName.setText(spot.getName());
    }

    @Override
    public int getItemCount() {
        return mSpotList.size();
    }

}