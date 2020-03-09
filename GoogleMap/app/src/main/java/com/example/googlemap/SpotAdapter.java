package com.example.googlemap;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;


public class SpotAdapter extends RecyclerView.Adapter<SpotAdapter.ViewHolder> {

    private List<Spot> mSpotList;
    private OnNoteListener mOnNoteListener;


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView spotName;
        TextView spotType;
        Button note;
        OnNoteListener onNoteListener;

        public ViewHolder(View view, OnNoteListener onNoteListener) {
            super(view);
            spotName = (TextView) view.findViewById(R.id.spotName);
            spotType = (TextView) view.findViewById(R.id.spotType);
            note = (Button) view.findViewById(R.id.note);
            this.onNoteListener = onNoteListener;
            note.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onNoteListener.onNoteClick(getAdapterPosition());

        }
    }

    public SpotAdapter(List<Spot> spotList, OnNoteListener onNoteListener) {
        mSpotList = spotList;
        mOnNoteListener = onNoteListener;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spot_item, parent, false);
        ViewHolder holder = new ViewHolder(view,mOnNoteListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Spot spot = mSpotList.get(position);
        holder.spotName.setText(spot.getName());
        holder.spotType.setText(spot.getType());
        holder.spotType.setText(spot.getType());

    }

    public void removeItem(int position) {
        mSpotList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mSpotList.size());
    }
    public interface OnNoteListener{
        void onNoteClick(int position);
    }





    @Override
    public int getItemCount() {
        return mSpotList.size();
    }

}