package com.example.caikaiyang.loadmoredemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caikaiyang on 2017/7/23.
 */

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private Context context;

    private List<String> list;


    private static final int ONE_TYPE=1;
    private static final int TWO_TYPE=2;
    private static final int FOOTER_TYEP=3;
    public MyAdapter(Context context){
        this.context=context;
        list=new ArrayList<>();
    }

    public void setList(List<String> list){
        this.list=list;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=null;
        RecyclerView.ViewHolder viewHolder=null;
        if(viewType==ONE_TYPE){
            view=inflater.inflate(R.layout.item_one_layout,parent,false);
            viewHolder=new MyViewHolderOne(view);
        }
        if(viewType==TWO_TYPE){
            view=inflater.inflate(R.layout.item_two_layout,parent,false);
            viewHolder=new MyViewHolderTwo(view);
        }
        if(viewType==FOOTER_TYEP){
            view=inflater.inflate(R.layout.item_footer_layout,parent,false);
            viewHolder=new FooterViewHolder(view);
        }


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {

        String item=list.get(position);
        if(item==null){
            return FOOTER_TYEP;
        }

        if(position%2==0){
            return TWO_TYPE;
        }else{
            return ONE_TYPE;
        }

    }

    public class MyViewHolderOne extends RecyclerView.ViewHolder{

        public MyViewHolderOne(View itemView) {
            super(itemView);
        }
    }


    public class MyViewHolderTwo extends RecyclerView.ViewHolder{

        public MyViewHolderTwo(View itemView) {
            super(itemView);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }

}
