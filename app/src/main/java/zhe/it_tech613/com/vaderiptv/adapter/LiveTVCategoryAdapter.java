package zhe.it_tech613.com.vaderiptv.adapter;


import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import zhe.it_tech613.com.vaderiptv.R;
import zhe.it_tech613.com.vaderiptv.models.CategoryModel;

public class LiveTVCategoryAdapter extends RecyclerView.Adapter<LiveTVCategoryAdapter.ViewHolder>{
    private Context context;
    private ArrayList<CategoryModel> categoryModels;
    private AdapterInterface adapterInterface;
    private int height,width;
    public LiveTVCategoryAdapter(Context context, ArrayList<CategoryModel> categoryModels,int width,int height,AdapterInterface adapterInterface){
        this.context=context;
        this.categoryModels = categoryModels;
        this.adapterInterface=adapterInterface;
        this.height=height;
        this.width=width;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_live_tv_category, viewGroup, false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapterInterface.buttonPressed(categoryModels.get(i).getId());
            }
        });
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
//        viewHolder.lay_1.setVisibility(View.GONE);
        viewHolder.lay_0.setVisibility(View.VISIBLE);
        viewHolder.small_text.setText(categoryModels.get(i).getName());
        if (categoryModels.get(i).getId()==1) viewHolder.fav_icon.setVisibility(View.VISIBLE);
        else viewHolder.fav_icon.setVisibility(View.GONE);
        viewHolder.lay_0.getLayoutParams().height = height;
        viewHolder.lay_0.getLayoutParams().width = width;
        viewHolder.lay_0.requestLayout();
    }

    @Override
    public int getItemCount() {
        return categoryModels==null? 0:categoryModels.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView small_text;
//        TextView large_text;
        ImageView fav_icon;
        RelativeLayout lay_0;
//        LinearLayout lay_1;
        ViewHolder(View view) {
            super(view);
            small_text=view.findViewById(R.id.small_text);
//            large_text=view.findViewById(R.id.large_text);
            fav_icon=view.findViewById(R.id.fav_icon);
            lay_0=(RelativeLayout)view.findViewById(R.id.lay_0);
//            lay_1=view.findViewById(R.id.lay_1);
        }
    }

    public interface AdapterInterface {
        void buttonPressed(int i);
    }
}
