package com.example.gif_royale.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gif_royale.R;

import java.util.ArrayList;

public class RivalsFragmentAdapter extends RecyclerView.Adapter<RivalsFragmentAdapter.RivalsCardHolder> {
    private ArrayList<String> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class RivalsCardHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout constraintLayout;
        public TextView usernameText;

        public RivalsCardHolder(ConstraintLayout v) {
            super(v);
            constraintLayout = v;
            CardView card = (CardView) v.getChildAt(0);
            usernameText = (TextView) card.getChildAt(0);
            card.setVisibility(View.VISIBLE);
            usernameText.setVisibility(View.VISIBLE);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public RivalsFragmentAdapter(ArrayList<String> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public RivalsFragmentAdapter.RivalsCardHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        // create a new constraintLayout to hold a users
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_card_layout, parent, false);
        RivalsCardHolder vh = new RivalsCardHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(RivalsCardHolder holder, int position) {
        // - assign Text in card
        String username = mDataset.get(position);
        holder.usernameText.setText(username);
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}