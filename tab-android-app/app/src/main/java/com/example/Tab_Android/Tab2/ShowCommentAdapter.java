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

public class ShowCommentAdapter extends RecyclerView.Adapter<ShowCommentAdapter.CustomViewHolder>{
    private ArrayList<CommentData> mList;
    private OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

    public interface OnItemClickListener{
        void onClick(String contents);
    }


    public ShowCommentAdapter(ArrayList<CommentData> mSearchData) { this.mList = mSearchData; }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView name;//notice board name
        protected TextView content;//notice board name
        protected TextView datetime;//notice board name

        public CustomViewHolder(View view) {
            super(view);
            this.content = (TextView) view.findViewById(R.id.commentcontent);
            this.name = (TextView) view.findViewById(R.id.commentname);
            this.datetime = (TextView) view.findViewById(R.id.commentdate);

        }
    }
    public ArrayList<CommentData> getData() {
        return mList;
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.tab2_commentlist, viewGroup, false);

        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {

                    onItemClickListener.onClick(mList.get(position).getContent());
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

        viewholder.content.setTextSize(TypedValue.COMPLEX_UNIT_SP, 12);
        viewholder.content.setGravity(Gravity.CENTER);
        viewholder.content.setText(mList.get(position).getContent().replace("\"",""));

        viewholder.name.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        viewholder.name.setGravity(Gravity.CENTER);
        viewholder.name.setText(mList.get(position).getName().replace("\"",""));

        viewholder.datetime.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8);
        viewholder.datetime.setGravity(Gravity.CENTER);
        viewholder.datetime.setText(mList.get(position).getDate().replace("\"",""));

    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
