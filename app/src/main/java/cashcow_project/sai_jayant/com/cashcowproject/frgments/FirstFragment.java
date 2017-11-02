package cashcow_project.sai_jayant.com.cashcowproject.frgments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Iterator;
import java.util.List;

import cashcow_project.sai_jayant.com.cashcowproject.R;
import cashcow_project.sai_jayant.com.cashcowproject.activites.DashboardActivity;
import cashcow_project.sai_jayant.com.cashcowproject.adapters.MyAdapter;
import data.Data;

/**
 * Created by macbookpro on 01/11/17.
 */

public class FirstFragment extends Fragment {
    private static JsonArray dataa;
    // Store instance variables
    private String title;
    private int page;
    private ArrayList<Data> list = new ArrayList<>();
    private MyAdapter mAdapter;

    // newInstance constructor for creating fragment with arguments
    public static FirstFragment newInstance(int page, String title) {
        FirstFragment fragmentFirst = new FirstFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);
//        TextView tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        RecyclerView tvLabel = (RecyclerView) view.findViewById(R.id.tvLabel);
//        tvLabel.setText(page + " -- " + title);
        Context ctx = getActivity();
        String strSavedValue = null;
        if (title.equalsIgnoreCase("one")) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATAONE", Context.MODE_PRIVATE);
            strSavedValue = sharedPreferences.getString("Data_one", "");
            Log.d("First Fragment 222222", "onCreate: " + strSavedValue);
        } else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATATWO", Context.MODE_PRIVATE);
            strSavedValue = sharedPreferences.getString("Data_two", "");
            Log.d("First Fragment 111111", "onCreate: " + strSavedValue);
        }
        try {

            JsonParser jsonParser = new JsonParser();
            JsonObject jo = (JsonObject) jsonParser.parse(strSavedValue);

            list.clear();


            for (int i = 0; i < 5; i++) {
                Data d = new Data();
                JsonObject j1 = (JsonObject) jo.get("id_" + i);//the name of the object is response so i extracted separately
                d.setOne(j1.get("id").toString());
                Log.d("data", "onCreateView: ------ id -----" + j1.get("id").toString());
                d.setTwo(j1.get("product").toString());
                Log.d("data", "onCreateView: ------ product -----" + j1.get("product").toString());
                d.setThree(j1.get("bank").toString());
                Log.d("data", "onCreateView: ------ bank -----" + j1.get("bank").toString());
                list.add(d);
            }


            mAdapter = new MyAdapter(getActivity(), list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());

            tvLabel.setHasFixedSize(true);
            tvLabel.setLayoutManager(layoutManager);

        } catch (Exception e) {
            Log.d("exp", "onCreateView: " + e.getLocalizedMessage());
        }

        tvLabel.setAdapter(mAdapter);
        return view;
    }
}