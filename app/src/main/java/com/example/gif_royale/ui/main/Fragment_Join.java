package com.example.gif_royale.ui.main;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.gif_royale.MatchmakingActivity;
import com.example.gif_royale.R;

import java.util.ArrayList;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Join.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Join#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Join extends Fragment {
    private String username;
    private OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static ArrayList<ArrayList<String>> groups;

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public Fragment_Join() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param
     * @param
     * @return A new instance of fragment Fragment_Rivals.
     */
    // TODO: Rename and change types and number of parameters
    public static Fragment_Join newInstance() {
        Fragment_Join fragment = new Fragment_Join();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.username = MatchmakingActivity.username;

        groups = new ArrayList<>();
        groups.add(new ArrayList<String>());
        for (int i = 1; i < 6; ++i) {
            groups.get(0).add(String.format(Locale.getDefault(), "User %d", i));
        }
        groups.add(new ArrayList<String>());
        for (int i = 6; i < 8; ++i) {
            groups.get(1).add(String.format(Locale.getDefault(),"User %d", i));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__join, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // get the RecyclerView, a scrolling view which holds the group cards
        recyclerView = getView().findViewById(R.id.RecyclerView_Join);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // specify an adapter (See class JoinFragmentAdapter)
        mAdapter = new JoinFragmentAdapter(groups);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(View view);
    }
}
