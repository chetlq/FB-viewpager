package com.example.sbt.a7fragmentexercise;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PageFragment extends Fragment {
    static final String ARGUMENT_PAGE_NUMBER = "arg_page_number";
    Shelves shelve;
    //private List<Shelves> myShelves;
    String pageNumber;
    int backColor;


    static PageFragment newInstance(String page) {
        PageFragment pageFragment = new PageFragment();
        Bundle arguments = new Bundle();
        arguments.putString(ARGUMENT_PAGE_NUMBER, page);
        pageFragment.setArguments(arguments);
        return pageFragment;
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//       // myShelves =
//        assert getArguments() != null;
//        pageNumber = getArguments().getString(ARGUMENT_PAGE_NUMBER);
//        shelve = AboveShelves.get().getShelve(pageNumber);
//        Random rnd = new Random();
//        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
//    }
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // myShelves =
        assert getArguments() != null;
        pageNumber = getArguments().getString(ARGUMENT_PAGE_NUMBER);
        shelve = AboveShelves.get().getShelve(pageNumber);
        Random rnd = new Random();
        backColor = Color.argb(40, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_screen_slide_page, null);

        TextView tvPage = (TextView) view.findViewById(R.id.tvPage);
        tvPage.setText("Page " + shelve.getRedline());
        tvPage.setBackgroundColor(backColor);

        return view;
    }
}

