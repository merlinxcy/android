package com.xeldax.signeveryday;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xeldax.signeveryday.dataHelper.SharedHelper;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link HomeFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home,container,false);
        return view;
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        SharedHelper helper = new SharedHelper(getActivity().getApplicationContext());
        TextView fail_counts = (TextView)getActivity().findViewById(R.id.fail_count);
        TextView sign_dates = (TextView)getActivity().findViewById(R.id.sign_date);
        TextView long_dates = (TextView)getActivity().findViewById(R.id.long_date);
        fail_counts.setText(helper.read_fail_count()+"天");
        sign_dates.setText(helper.read_sign_time()+"天");
        long_dates.setText(helper.read_top_sign_time()+"天");
    }

}
