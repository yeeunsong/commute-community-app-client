package com.example.Tab_Android.Tab2;

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

public class Frag2Adapter extends RecyclerView.Adapter<Frag2Adapter.CustomViewHolder> {

    private ArrayList<String> mList;

    public Frag2Adapter(ArrayList<String> mSearchData) { this.mList = mSearchData; }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
    protected TextView nbname;//notice board name

    public CustomViewHolder(View view) {
        super(view);
        this.nbname = (TextView) view.findViewById(R.id.nbname);

        }
    }
    public ArrayList<String> getData() {
        return mList;
    }

    public interface OnItemClickListener{
        void onClick(String name);
    }

    private OnItemClickListener onItemClickListener;

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tab2_frag2list, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.nbname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(mList.get(position));
                }
            }
        });

        viewholder.nbname.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.nbname.setGravity(Gravity.CENTER);
        viewholder.nbname.setText(mList.get(position));

    }


    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


}
