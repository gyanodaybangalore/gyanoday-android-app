package com.gdjt.gyanoday.fragment;


import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gdjt.gyanoday.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {


    private View mMainView;
    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mMainView = inflater.inflate(R.layout.fragment_about, container, false);
        return mMainView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView aboutText = mMainView.findViewById(R.id.tv_about_us);
        String str = getString(R.string.AboutUs);
        String htmlStr = str.replace("\n", "<br>");
        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            aboutText.setText(Html.fromHtml(htmlStr, Html.FROM_HTML_MODE_COMPACT));

        }else{
            aboutText.setText(Html.fromHtml(htmlStr));
        }

    }
}
