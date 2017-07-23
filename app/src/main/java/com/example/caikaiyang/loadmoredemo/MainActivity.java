package com.example.caikaiyang.loadmoredemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private MyAdapter adapter;

    private boolean loading = true;
    private List<String> list;


    private int currentPage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        init();
    }

    private void init() {
        list=new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add("1111");
        }

        adapter=new MyAdapter(this);

        adapter.setList(list);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            //声明一个LinearLayoutManager
            private LinearLayoutManager mLinearLayoutManager;

            //当前页，从0开始    private int currentPage = 0;
            //已经加载出来的Item的数量
            private int totalItemCount;

            //主要用来存储上一个totalItemCount
            private int previousTotal = 0;

            //在屏幕上可见的item数量
            private int visibleItemCount;

            //在屏幕可见的Item中的第一个
            private int firstVisibleItem;

            //是否正在上拉数据


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                mLinearLayoutManager= (LinearLayoutManager) recyclerView.getLayoutManager();

                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = mLinearLayoutManager.getItemCount();
                firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();
                Log.i("cky","+++++"+totalItemCount);
                Log.i("cky","visible"+visibleItemCount);

                Log.i("cky","first"+firstVisibleItem);

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;

                        /**
                         * 增加的footer要算在里面所以要加1
                         */
                        previousTotal = totalItemCount+1;

//                        Log.i("cky",previousTotal+"");
                    }
                }
                if (!loading
                        && (totalItemCount - visibleItemCount) <= firstVisibleItem) {
                    currentPage++;
                    onLoadMore(currentPage);
                    loading = true;
                }

            }
        });

    }

    private void onLoadMore(int currentPage) {
        list.add(null);
        adapter.setList(list);
        adapter.notifyDataSetChanged();

        Observable.timer(2, TimeUnit.SECONDS,AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        list.remove(list.size()-1);
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();

                        list.add("1111");
                        list.add("1111");
                        list.add("1111");
                        list.add("1111");
                        list.add("1111");
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                    }
                });


    }
}
