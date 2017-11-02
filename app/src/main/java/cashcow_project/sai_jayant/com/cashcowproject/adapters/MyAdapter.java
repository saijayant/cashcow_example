package cashcow_project.sai_jayant.com.cashcowproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cashcow_project.sai_jayant.com.cashcowproject.R;
import data.Data;

/**
 * Created by macbookpro on 02/11/17.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    public ArrayList<Data> itemList;
    private Context context;

    public MyAdapter(Context context, ArrayList<Data> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_layout, null);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Log.d("dogs", "onBindViewHolder 1 : " + itemList.get(position).getOne());
        Log.d("dogs", "onBindViewHolder 2 : " + itemList.get(position).getTwo());
        Log.d("dogs", "onBindViewHolder 3 : " + itemList.get(position).getThree());

        viewHolder.first.setText("Index number : " + (itemList.get(position).getOne()).replaceAll("^\"|\"$", ""));
        viewHolder.second.setText("Product name : " + (itemList.get(position).getTwo()).replaceAll("^\"|\"$", ""));
        viewHolder.third.setText("Bank name  : " + (itemList.get(position).getThree()).replaceAll("^\"|\"$", ""));
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imgViewIcon;
        public TextView first;
        public TextView second;
        public TextView third;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.image);
            first = (TextView) itemLayoutView.findViewById(R.id.firstLine);
            second = (TextView) itemLayoutView.findViewById(R.id.secondLine);
            third = (TextView) itemLayoutView.findViewById(R.id.thirdLine);
        }
    }


    @Override
    public int getItemCount() {
        return itemList.size();
    }
}

