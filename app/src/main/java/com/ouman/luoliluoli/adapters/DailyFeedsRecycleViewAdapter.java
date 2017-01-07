package com.ouman.luoliluoli.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ouman.luoliluoli.NewsDetailActivity;
import com.ouman.luoliluoli.R;
import com.ouman.luoliluoli.models.HotNewsModel;

import java.util.List;

/**
 * Created by jfg on 17-1-5.
 */

public class DailyFeedsRecycleViewAdapter extends RecyclerView.Adapter<DailyFeedsRecycleViewAdapter.DailyFeedsViewHolder>{

    private Context context;
    private List<HotNewsModel> data;
    public DailyFeedsRecycleViewAdapter(Context context, List<HotNewsModel> data){
        this.context = context;
        this.data = data;
    }

    public DailyFeedsRecycleViewAdapter.DailyFeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.hot_recyclerview_item, null);
        return new DailyFeedsRecycleViewAdapter.DailyFeedsViewHolder(view);
    }

    public void onBindViewHolder(final DailyFeedsRecycleViewAdapter.DailyFeedsViewHolder holder, final int position){
        String title = data.get(position).getTitle();
        String imageUrl = data.get(position).getImages();
        String date = data.get(position).getDate();

        holder.tv_title.setText(title);
        holder.tv_date.setText(date);
        holder.hotCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(view.getContext(), "inside viewholder position = " +
//                        String.valueOf(position), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(view.getContext(), NewsDetailActivity.class);
                view.getContext().startActivity(intent);

            }
        });

    }

    public int getItemCount(){
        return data.size();
    }

    public class DailyFeedsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_date;
        ImageView news_image;
        CardView hotCardView;
        public DailyFeedsViewHolder(View view){
            super(view);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_date = (TextView) view.findViewById(R.id.tv_date);
            news_image = (ImageView) view.findViewById(R.id.news_image);
            hotCardView = (CardView) view.findViewById(R.id.hot_recyclerview_cardview);
        }

    }
}
