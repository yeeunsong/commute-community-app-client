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

public class ShowPostAdapter extends RecyclerView.Adapter<ShowPostAdapter.CustomViewHolder> {
    private ArrayList<CommentData> mList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(String name);
    }

    public ShowPostAdapter(ArrayList<CommentData> mSearchData) { this.mList = mSearchData; }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView title;//notice board name
        protected TextView name;//notice board name
        protected TextView datetime;//notice board name

        public CustomViewHolder(View view) {
            super(view);
            this.title = (TextView) view.findViewById(R.id.posttitle);
            this.name = (TextView) view.findViewById(R.id.postname);
            this.datetime = (TextView) view.findViewById(R.id.postdate);

        }
    }
    public ArrayList<CommentData> getData() {
        return mList;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tab2_noticeboardlist, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(mList.get(position).getTitle());
                }
            }
        });

        viewholder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(mList.get(position).getName());
                }
            }
        });

        viewholder.datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onClick(mList.get(position).getDate());
                }
            }
        });

        viewholder.title.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        viewholder.title.setGravity(Gravity.CENTER);
        viewholder.title.setText(mList.get(position).getName());

        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.name.setText(mList.get(position).getName());

        viewholder.datetime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        viewholder.datetime.setGravity(Gravity.CENTER);
        viewholder.datetime.setText(mList.get(position).getName());

    }


    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }


}
