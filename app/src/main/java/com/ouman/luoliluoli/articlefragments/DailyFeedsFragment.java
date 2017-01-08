package com.ouman.luoliluoli.articlefragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.NetworkOnMainThreadException;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.media.RatingCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.ouman.luoliluoli.HotFragment;
import com.ouman.luoliluoli.R;
import com.ouman.luoliluoli.adapters.DailyFeedsRecycleViewAdapter;
import com.ouman.luoliluoli.adapters.HotRecyclerViewAdapter;
import com.ouman.luoliluoli.models.DailyFeedsModel;
import com.ouman.luoliluoli.models.HotNewsModel;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

/**
 * Created by jfg on 17-1-3.
 */

public class DailyFeedsFragment extends Fragment {

    public static final String ARG_PAGE = "ARG_PAGE";

    private DailyFeedsFragment.OnFragmentInteractionListener mListener;

    private int mPage;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ArrayList<DailyFeedsModel> dailyFeeds;
    private RecyclerView recyclerView;
    private DailyFeedsRecycleViewAdapter adapter;

    public static DailyFeedsFragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        DailyFeedsFragment fragment = new DailyFeedsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.article_fragment_tab1, container, false);

        dailyFeeds = new ArrayList<>();


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_article_tab1);
        recyclerView = (RecyclerView) view.findViewById(R.id.article_tab1_recycleview);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        //add swipe refresh on recycler view
        swipeRefreshLayout.setSize(SwipeRefreshLayout.DEFAULT);
        swipeRefreshLayout.setColorSchemeResources(android.R.color.holo_green_light, android.R.color.holo_red_light,
                android.R.color.holo_blue_light, android.R.color.black);
        swipeRefreshLayout.setProgressViewEndTarget(true, 200);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                System.out.println("refresh!----");
                new Thread(getFeedsThread).start();
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                swipeRefreshLayout.setRefreshing(false);

                swipeRefreshLayout.setRefreshing(false);
            }
        });

        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.recyclerview_spacing);
        recyclerView.addItemDecoration(new SpaceItemDecoration(spacingInPixels));

        adapter = new DailyFeedsRecycleViewAdapter(getActivity(), dailyFeeds);
        recyclerView.setAdapter(adapter);

        //call http get thread
        new  Thread(getFeedsThread).start();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }



    public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
        private int space;
        public SpaceItemDecoration(int space) {
            this.space = space;
        }
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            if(parent.getChildPosition(view) != 0)
                outRect.top = space;
        }
    }

    private  Thread getFeedsThread = new Thread(){
        public void run(){
            //In here we solve http request to get data
            swipeRefreshLayout.setRefreshing(true);
            try{
                Document doc = Jsoup.connect("http://jandan.net/").get();
                Elements posts = doc.select("div.list-post");
                int i=0;
                for (Element post: posts){
                    i++;
                    if (i < 5){
                    }else{
                        String articleUrl = post.select("div.thumbs_b a").attr("href");
                        String imageUrl = post.select("div.thumbs_b a img").attr("data-original");
                        if (imageUrl == null){
                            imageUrl = post.select("div.thumbs_b a img").attr("src");
                        }

                        if (imageUrl.length() != 0){
                            imageUrl = imageUrl.substring(2, imageUrl.length());
                        }
                        String title = post.select("div.indexs h2").text();
                        String author = post.select("div.indexs div.time_s a").text();
                        String tag = post.select("div.indexs div.time_s strong").text();
                        String describe = post.select("div.indexs div").text();

                        DailyFeedsModel dailyFeed = new DailyFeedsModel();
                        dailyFeed.setImage(imageUrl);
                        dailyFeed.setArticleUrl(articleUrl);
                        dailyFeed.setTitle(title);
                        dailyFeed.setAuthor(author);
                        dailyFeed.setTag(tag);
                        dailyFeeds.add(dailyFeed);
                    }


                }

                if (posts != null){
                    Message msg = Message.obtain();
                    msg.what = 0;
                    getFeedsHandler.sendMessage(msg);
                }
            } catch (NetworkOnMainThreadException e) {
                System.out.println("Network exception.");

            } catch (IOException e){
                System.out.println("IO exception.");
            }



        }
    };

    private Handler getFeedsHandler = new Handler(){
        public void handleMessage(Message msg){
            if (msg.what == 0){
                //add data stop refresher and send to adapter data
                //changed

                swipeRefreshLayout.setRefreshing(false);
            }else if (msg.what == 1){
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getActivity(), "Already up-to-date!", LENGTH_SHORT).show();
            }
        }
    };



}
