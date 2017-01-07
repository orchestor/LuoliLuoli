package com.ouman.luoliluoli.articlefragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ouman.luoliluoli.R;

/**
 * Created by jfg on 17-1-3.
 */

public class DuanziShouFragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";

    private int mPage;

    public static DuanziShouFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DuanziShouFragment fragment = new DuanziShouFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment_tab4, container, false);
        TextView textView = (TextView) view.findViewById(R.id.tv_tab4);
        textView.setText("Fragment #" + mPage);
        return view;
    }
}
