package com.example.gif_royale;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.JsonReader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.example.gif_royale.ui.main.Fragment_Create;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link GifFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link GifFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GifFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    public void setOnFragmentInteractionListener(OnFragmentInteractionListener mListener) {
        this.mListener = mListener;
    }

    public GifFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     **/

    // TODO: Rename and change types and number of parameters
    public static GifFragment newInstance() {
        GifFragment fragment = new GifFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gif, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        WebView currentGif = getView().findViewById(R.id.WebView_gif);
        currentGif.getSettings().setJavaScriptEnabled(true);
        try {
            URL giphyEndpoint = new URL("https://api.giphy.com/v1/gifs/random?api_key=SKBHjyYBvHxbfkDWGeak6jFMz9EXwwtX&tag=cats&rating=PG-13");
            HttpsURLConnection myConnection = (HttpsURLConnection) giphyEndpoint.openConnection();
            myConnection.setRequestMethod("POST");
            myConnection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(myConnection.getOutputStream());
            out.writeBytes(giphyEndpoint.toString());
            out.flush();
            out.close();
            InputStreamReader responseBodyReader =
                    new InputStreamReader(myConnection.getInputStream(), "UTF-8");
            JsonReader jsonReader = new JsonReader(responseBodyReader);
            jsonReader.beginObject(); // Start processing the JSON object
            while (jsonReader.hasNext()) { // Loop through all keys
                String key = jsonReader.nextName(); // Fetch the next key
                System.out.println(key);
                if (key.equals("organization_url")) { // Check if desired key
                    // Fetch the value as a String
                    String value = jsonReader.nextString();
                    // Do something with the value
                    // ...
                    break; // Break out of the loop
                } else {
                    jsonReader.skipValue(); // Skip values of other keys
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }
}
