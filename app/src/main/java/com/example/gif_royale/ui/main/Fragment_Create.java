package com.example.gif_royale.ui.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.example.gif_royale.Activity_Play;
import com.example.gif_royale.GifBrowsingActivity;
import com.example.gif_royale.GifFragment;
import com.example.gif_royale.MatchmakingActivity;
import com.example.gif_royale.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Fragment_Create.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Fragment_Create#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_Create extends Fragment {

    private String username;

    private OnFragmentInteractionListener mListener;

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public Fragment_Create() {
    }

    public static Fragment_Create newInstance() {
        Fragment_Create fragment = new Fragment_Create();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        username = MatchmakingActivity.username;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment__create, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        TextView helloText = getView().findViewById(R.id.textView_hello);
        helloText.setText(String.format("%s %s", getString(R.string.WelcomeMessage), username));

        MatchmakingActivity.hideKeyboardFrom(getContext(), helloText);

        Button buttonStart = getView().findViewById(R.id.button_createGame);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button button = (Button) view;
                if (button.getText() == getString(R.string.button_create_group)) {
                    button.setText(getString(R.string.button_start_game));
                } else {
                    startActivity(new Intent(getActivity(), Activity_Play.class));
                    button.setText(getString(R.string.button_create_group));
                }
            }
        });

        WebView helloGif = getView().findViewById(R.id.WebView_Create);
        helloGif.getSettings().setJavaScriptEnabled(true);
        helloGif.loadUrl("https://media2.giphy.com/media/eeZpO7bX9a3HGHcVie/200w.gif?cid=75b441445d23aa636b676256598c1785&rid=200w.gif");
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
        void onFragmentInteraction(View view);
        void onUsernameChange();
    }
}
