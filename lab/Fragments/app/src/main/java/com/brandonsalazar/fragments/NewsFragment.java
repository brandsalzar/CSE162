package com.brandonsalazar.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class NewsFragment extends Fragment {
    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        /*
        During startuup, check if there are arguments p[assed to the fragment. onStart is a good place
        to dod this because thge layout has already been applied to the frragment at this pointso we
        can safely call the method belw that sets the article text.
         */

        Bundle args = getArguments();
        if (args != null) {
            //set article based on the argument passed in
            updateArticleView(args.getInt(ARG_POSITION));
        } else if(mCurrentPosition != -1){
            //set the article based pm saved instance state defined during onCreateView
            updateArticleView(mCurrentPosition);
        }
    }

    public void updateArticleView(int position) {
        TextView article = (TextView) getActivity().findViewById(R.id.news);
        article.setText(Ipsum.Articles[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //save the current article selection in case we need to recreate the fragmenet
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

}