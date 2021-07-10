package com.example.Tab_Android.Tab2;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Tab_Android.R;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

private ArrayList<TableData> mList;
private SparseBooleanArray mSelectedItems = new SparseBooleanArray(0);


    public MainAdapter(ArrayList<TableData> mSearchData) {
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
    protected TextView name;
    protected TextView department;
    protected TextView phone;
    protected TextView email;

    public CustomViewHolder(View view) {
        super(view);
        this.name = (TextView) view.findViewById(R.id.name);
        //this.department = (TextView) view.findViewById(R.id.department);
        //this.phone = (TextView) view.findViewById(R.id.phonenumber);
        //this.email = (TextView) view.findViewById(R.id.email);


    }

}
    /*
    public void removeItem(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(CustomData item, int position) {
        mList.add(position, item);
        notifyItemInserted(position);
    }

    public ArrayList<CustomData> getData() {
        return mList;
    }*/



public interface OnItemClickListener{
    void onClick(String name);
}

    private OnItemClickListener onItemClickListener;



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tab2_mainlist, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {


        viewholder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(mList.get(position).getName());
                }
            }
        });


        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 10);
        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.name.setText(mList.get(position).getName());


    }


    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
