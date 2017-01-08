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
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ouman.luoliluoli.DailyFeedsDetailActivity;
import com.ouman.luoliluoli.NewsDetailActivity;
import com.ouman.luoliluoli.R;
import com.ouman.luoliluoli.articlefragments.DailyFeedsFragment;
import com.ouman.luoliluoli.models.DailyFeedsModel;
import com.ouman.luoliluoli.models.HotNewsModel;

import java.util.List;

/**
 * Created by jfg on 17-1-5.
 */

public class DailyFeedsRecycleViewAdapter extends RecyclerView.Adapter<DailyFeedsRecycleViewAdapter.DailyFeedsViewHolder>{

    private Context context;
    private List<DailyFeedsModel> data;
    public DailyFeedsRecycleViewAdapter(Context context, List<DailyFeedsModel> data){
        this.context = context;
        this.data = data;
    }

    public DailyFeedsRecycleViewAdapter.DailyFeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(context).inflate(R.layout.recycleview_item_article_tab1, null);
        return new DailyFeedsRecycleViewAdapter.DailyFeedsViewHolder(view);
    }

    public void onBindViewHolder(final DailyFeedsRecycleViewAdapter.DailyFeedsViewHolder holder, final int position){
        String title = data.get(position).getTitle();
        String tag = data.get(position).getTag();
        String image = data.get(position).getImage();
        System.out.println("image--" + image);

        Glide.with(holder.image.getContext())
                .load("http://" + image)
                .error(R.drawable.bk1)
                .into(holder.image);


        holder.tv_title.setText(title);
        holder.tv_tag.setText(tag);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DailyFeedsDetailActivity.class);
                view.getContext().startActivity(intent);

            }
        });

    }

    public int getItemCount(){
        return data.size();
    }

    public class DailyFeedsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title;
        TextView tv_tag;
        ImageView image;
        CardView cardView;
        public DailyFeedsViewHolder(View view){
            super(view);
            tv_title = (TextView) view.findViewById(R.id.article_tab1_recycle_item_title);
            tv_tag = (TextView) view.findViewById(R.id.article_tab1_recycle_item_tag);
            image = (ImageView) view.findViewById(R.id.article_tab1_recycle_item_image);
            cardView = (CardView) view.findViewById(R.id.article_tab1_recycle_item_cardview);
        }

    }
}
