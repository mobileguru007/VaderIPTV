package zhe.it_tech613.com.vaderiptv.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.ArrayList;

import zhe.it_tech613.com.vaderiptv.R;
import zhe.it_tech613.com.vaderiptv.models.ChannelModel;
import zhe.it_tech613.com.vaderiptv.models.ChannelRealmModel;

public class LiveTVChannelAdapter extends RecyclerView.Adapter<LiveTVChannelAdapter.ViewHolder>{
    private Context context;
    private ArrayList<ChannelModel> channelRealmModels;
    private AdapterInterface adapterInterface;
    private int height,width,size;
    public LiveTVChannelAdapter(Context context, ArrayList<ChannelModel> channelRealmModels, int width, int height, int size, AdapterInterface adapterInterface){
        this.context=context;
        this.channelRealmModels = channelRealmModels;
        this.adapterInterface=adapterInterface;
        this.height=height;
        this.width=width;
        this.size=size;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_live_tv_channel, viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterInterface.buttonPressed(channelRealmModels.get(i).getId());
            }
        });
        return new ViewHolder(itemView);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//        viewHolder.lay_1.setVisibility(View.GONE);
        viewHolder.lay_0.setVisibility(View.VISIBLE);
        ChannelModel channelRealmModel = channelRealmModels.get(i);
        viewHolder.channel_name.setText(channelRealmModel.getStream_display_name());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholder);
        requestOptions.error(R.drawable.television);
        Glide.with(context)
                .load(channelRealmModel.getStream_icon())
                .apply(requestOptions)
                .into(viewHolder.fav_icon);
        viewHolder.lay_0.getLayoutParams().height = height;
        viewHolder.lay_0.getLayoutParams().width = width;
        viewHolder.lay_0.requestLayout();
        viewHolder.item_number.setText(String.valueOf((i % size)+1));
    }

    @Override
    public int getItemCount() {
        return channelRealmModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ProgressBar progressBar;
        TextView channel_name,item_number;
        ImageView fav_icon;
        RelativeLayout lay_0;
        ViewHolder(View view) {
            super(view);
            progressBar=view.findViewById(R.id.progress_bar);
            channel_name=view.findViewById(R.id.channel_name);
            item_number=view.findViewById(R.id.item_number);
            fav_icon=view.findViewById(R.id.fav_icon);
            lay_0=(RelativeLayout)view.findViewById(R.id.lay_0);
        }
    }

    public interface AdapterInterface {
        void buttonPressed(long id);
    }
}
