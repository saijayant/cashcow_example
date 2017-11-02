package cashcow_project.sai_jayant.com.cashcowproject.frgments;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import cashcow_project.sai_jayant.com.cashcowproject.R;
import cashcow_project.sai_jayant.com.cashcowproject.activites.DashboardActivity;
import data.Data;

/**
 * Created by macbookpro on 01/11/17.
 */

public class FirstFragment extends Fragment {
    private static JsonArray dataa;
    // Store instance variables
    private String title;
    private int page;

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
        ListView tvLabel = (ListView) view.findViewById(R.id.tvLabel);
//        tvLabel.setText(page + " -- " + title);
        Context ctx = getActivity();
        String strSavedValue = null;
        if (title.equalsIgnoreCase("one")) {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATAONE", Context.MODE_PRIVATE);
             strSavedValue = sharedPreferences.getString("Data_one","");
            Log.d("First Fragment 222222", "onCreate: " + strSavedValue);
        } else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("DATATWO", Context.MODE_PRIVATE);
            strSavedValue = sharedPreferences.getString("Data_two", "");
            Log.d("First Fragment 111111", "onCreate: " + strSavedValue);
        }
        String[] mobileArray = null;//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, items);
        try {
            JSONArray jsonArray = new JSONArray(strSavedValue);
            mobileArray = new String[jsonArray.length()];

            for (int i = 0; i < jsonArray.length(); i++) {

                jsonArray.get(i);
                String result = (jsonArray.get(i).toString()).replaceAll(",", " ");
                Log.d("Inside forloop", "onCreateView: " + jsonArray.get(i).toString());
                mobileArray[i] = result;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ArrayAdapter adapter = new ArrayAdapter<String>(getActivity(),
                R.layout.activity_listview, mobileArray);
        tvLabel.setAdapter(adapter);
        return view;
    }
}