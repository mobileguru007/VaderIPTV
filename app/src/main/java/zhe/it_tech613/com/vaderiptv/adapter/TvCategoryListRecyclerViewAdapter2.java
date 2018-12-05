package zhe.it_tech613.com.vaderiptv.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;
import zhe.it_tech613.com.vaderiptv.fragment.LiveTvFragment;
import zhe.it_tech613.com.vaderiptv.models.TvCategory;
import zhe.it_tech613.com.vaderiptv.utils.LayoutUtils;
import zhe.it_tech613.com.vaderiptv.utils.OnSelected;

public class TvCategoryListRecyclerViewAdapter2 extends Adapter<TvCategoryListRecyclerViewAdapter2.ViewHolder> {
    /* renamed from: a */
    private final LiveTvFragment liveTvFragment;
    /* renamed from: b */
    private final List<TvCategory> tvCategories;
    /* renamed from: c */
    private final OnSelected<TvCategory> tvCategoryOnSelected;
    /* renamed from: d */
    private int f12865d = -1;
    /* renamed from: e */
    private int f12866e = -1;
    /* renamed from: f */
    private UserManager userManager;
    /* renamed from: g */
    private LinearLayoutManager linearLayoutManager;
    /* renamed from: h */
    private View f12869h = null;
    /* renamed from: i */
    private TextView f12870i;
    /* renamed from: j */
    private TextView f12871j;
    /* renamed from: k */
    private int f12872k;
    /* renamed from: l */
    private final OnClickListener onClickListener = new ClickListener(this);

    /* renamed from: tv.vaders.apk.livetv.adapter.TvCategoryListRecyclerViewAdapter2$1 */
    class ClickListener implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ TvCategoryListRecyclerViewAdapter2 tvCategoryListRecyclerViewAdapter2;

        ClickListener(TvCategoryListRecyclerViewAdapter2 tvCategoryListRecyclerViewAdapter2) {
            this.tvCategoryListRecyclerViewAdapter2 = tvCategoryListRecyclerViewAdapter2;
        }

        public void onClick(View view) {
            this.tvCategoryListRecyclerViewAdapter2.liveTvFragment.m21523i(true);
        }
    }

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        /* renamed from: n */
        final TextView f12859n;
        /* renamed from: o */
        final ImageView f12860o;
        /* renamed from: p */
        final /* synthetic */ TvCategoryListRecyclerViewAdapter2 f12861p;

        ViewHolder(TvCategoryListRecyclerViewAdapter2 tvCategoryListRecyclerViewAdapter2, View view) {
            super(view);
            this.f12861p = tvCategoryListRecyclerViewAdapter2;
            this.f12859n = (TextView) view.findViewById(R.id.category_name);
            this.f12860o = (ImageView) view.findViewById(R.id.favorite_icon);
            LayoutUtils.setImageSource(this.f12860o, R.drawable.livetv_favorite_star);
        }
    }

    public TvCategoryListRecyclerViewAdapter2(Fragment fragment, List<TvCategory> list, OnSelected<TvCategory> onSelected, int i) {
        this.tvCategories = list;
        this.liveTvFragment = (LiveTvFragment) fragment;
        this.f12872k = i;
        this.tvCategoryOnSelected = onSelected;
        this.userManager = new UserManager(fragment.m10890o().getApplicationContext());
        this.f12870i = (TextView) this.liveTvFragment.f14940d.findViewById(R.id.infobar_category_name);
        this.f12871j = (TextView) this.liveTvFragment.f14940d.findViewById(R.id.live_tv_information_bar_info_group);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.livetv2_category_content, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ImageView imageView;
        int i2;
        LinearLayout linearLayout = (LinearLayout) viewHolder.f12859n.getParent();
        TvCategory tvCategory = (TvCategory) this.tvCategories.get(i % this.tvCategories.size());
        viewHolder.f12859n.setText(tvCategory.m17508b());
        if (tvCategory.m17508b().equalsIgnoreCase("favorites")) {
            imageView = viewHolder.f12860o;
            i2 = 0;
        } else {
            imageView = viewHolder.f12860o;
            i2 = 8;
        }
        imageView.setVisibility(i2);
        linearLayout.setOnClickListener(this.onClickListener);
        linearLayout.setTag(Integer.valueOf(i % this.tvCategories.size()));
    }

    /* renamed from: a */
    public int getItemCount() {
        return this.tvCategories.size() * IjkMediaCodecInfo.RANK_MAX;
    }

    /* renamed from: a */
    public long mo36a(int i) {
        return super.mo36a(i % this.tvCategories.size());
    }

    /* renamed from: a */
    public void mo2043a(RecyclerView recyclerView) {
        super.mo2043a(recyclerView);
        this.linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    }

    /* renamed from: b */
    public /* synthetic */ android.support.v7.widget.RecyclerView.ViewHolder mo40b(ViewGroup viewGroup, int i) {
        return m17461a(viewGroup, i);
    }

    /* renamed from: b */
    public void m17466b() {
        this.f12869h = this.linearLayoutManager.mo566c(this.linearLayoutManager.m11965p() - this.f12872k);
        if (this.f12869h != null && this.f12865d != (Integer) this.f12869h.getTag()) {
            this.f12870i.setText(((TextView) this.f12869h.findViewById(R.id.category_name)).getText());
        }
    }

    /* renamed from: c */
    public void m17467c() {
        this.f12869h = this.linearLayoutManager.mo566c(this.linearLayoutManager.m11965p() - this.f12872k);
        if (this.f12869h != null) {
            int intValue = (Integer) this.f12869h.getTag();
            if (this.f12865d != intValue) {
                CharSequence text = ((TextView) this.f12869h.findViewById(R.id.category_name)).getText();
                this.f12870i.setText(text);
                this.f12871j.setText(text);
                this.f12865d = intValue;
                TvCategory tvCategory = (TvCategory) this.tvCategories.get(intValue);
                this.userManager.m10041d("user_cat", Integer.toString(tvCategory.m17505a()));
                this.liveTvFragment.m21521g(tvCategory.m17505a());
                if (this.tvCategoryOnSelected != null) {
                    this.tvCategoryOnSelected.mo2747a(tvCategory);
                }
            }
        }
    }

    /* renamed from: g */
    public View m17468g() {
        return this.f12869h;
    }
}
