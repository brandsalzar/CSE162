package com.brandonsalazar.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.ListFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.brandonsalazar.fragments.placeholder.PlaceholderContent;

/*
A Fragment represnting a list of items
 */
public class HeadlineFragment extends ListFragment{
    OnHeadlineSelectedListener mCallback;

    //Container Activity must implement this interfave
    public interface OnHeadlineSelectedListener {
        public void onArticleSelected(int position);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        /*
        This makes sure that the container activity has implemented the callback interface. if not,
        it throuwas an exception
         */
        try{
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id){
        //notify the parent activity of selected item
        mCallback.onArticleSelected(position);

        //set the item as checked to be the higlighted when in two-pane layout
        getListView().setItemChecked(position, true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int layout = android.R.layout.simple_list_item_activated_1;
        setListAdapter(new ArrayAdapter<String>(getActivity(), layout, Ipsum.Headlines));
    }

    @Override
    public void onStart(){
        super.onStart();
        if(getFragmentManager().findFragmentById(R.id.news_fragment) != null) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        }
    }
}