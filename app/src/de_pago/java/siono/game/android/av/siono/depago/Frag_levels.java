package siono.game.android.av.siono.depago;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import siono.game.android.av.siono.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frag_levels.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frag_levels#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frag_levels extends Fragment implements View.OnClickListener {


    private ImageView btn_lvl;
    private View  onview;
    //private Level_1 level_1 = new Level_1();
    //private Level_2 level_2 = new Level_2();
    private SoundPool click;
    private int flujodemusica;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Frag_levels() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frag_levels.
     */
    // TODO: Rename and change types and number of parameters
    public static Frag_levels newInstance(String param1, String param2) {
        Frag_levels fragment = new Frag_levels();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        //SOUNDPOOL SONIDO TIEMPO
        click =  new SoundPool(0, AudioManager.STREAM_MUSIC,0);
        this.getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);
        flujodemusica = click.load(this.getActivity(),R.raw.click,1);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        onview= inflater.inflate(R.layout.fragment_frag_levels, container, false);

        btn_lvl = (ImageView)onview.findViewById(R.id.btn_levels);
        btn_lvl.setOnClickListener(this);
        return onview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btn_levels:
                click.play(flujodemusica,0.5f,0.5f,0,0,1);
                Intent i = new Intent(getActivity(),Levels_all.class);
                getActivity().finish();
                startActivity(i);

                break;
        }
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
        void onFragmentInteraction(Uri uri);
    }
}