package com.example.gif_royale.ui.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gif_royale.R;

import java.util.ArrayList;

public class JoinFragmentAdapter extends RecyclerView.Adapter<JoinFragmentAdapter.MyViewHolder> {
    private ArrayList<ArrayList<String>> mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linearLayout;
        public MyViewHolder(LinearLayout v) {
            super(v);
            linearLayout = v;
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public JoinFragmentAdapter(ArrayList<ArrayList<String>> myDataset) {
        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public JoinFragmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        // create a new linearLayout to hold a group of users
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.join_cards_layout, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get each username in a group
        // - add another card to the group
        ArrayList<String> group = mDataset.get(position);
        LinearLayout groupLayout = holder.linearLayout;
        groupLayout = (LinearLayout) groupLayout.getChildAt(0);
        for (String user : group) {
            groupLayout.addView(LayoutInflater.from(groupLayout.getContext())
                    .inflate(R.layout.user_card_layout, groupLayout, false));
            CardView card = (CardView) ((ConstraintLayout) groupLayout.getChildAt(groupLayout.getChildCount() - 1))
                    .getChildAt(0);
            TextView username = (TextView) card.getChildAt(0);
            username.setText(user);
            card.setVisibility(View.VISIBLE);
            username.setVisibility(View.VISIBLE);
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }
}
