package zhe.it_tech613.com.vaderiptv.adapter;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.List;

import zhe.it_tech613.com.vaderiptv.fragment.LiveTvLegacyFragment;
import zhe.it_tech613.com.vaderiptv.models.TvCategory;
import zhe.it_tech613.com.vaderiptv.utils.OnSelected;

public class TvCategoryListRecyclerViewAdapter extends Adapter<TvCategoryListRecyclerViewAdapter.ViewHolder> {
    /* renamed from: a */
    private final Fragment fragment;
    /* renamed from: b */
    private final List<TvCategory> tvCategories;
    /* renamed from: c */
    private final OnSelected<TvCategory> tvCategoryOnSelected;
    /* renamed from: d */
    private UserManager userManager;
    /* renamed from: e */
    private final OnClickListener f12878e = new C06781(this);

    /* renamed from: tv.vaders.apk.livetv.adapter.TvCategoryListRecyclerViewAdapter$1 */
    class C06781 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ TvCategoryListRecyclerViewAdapter tvCategoryListRecyclerViewAdapter;

        C06781(TvCategoryListRecyclerViewAdapter tvCategoryListRecyclerViewAdapter) {
            this.tvCategoryListRecyclerViewAdapter = tvCategoryListRecyclerViewAdapter;
        }

        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            TvCategory tvCategory = (TvCategory) this.tvCategoryListRecyclerViewAdapter.tvCategories.get(intValue);
            this.tvCategoryListRecyclerViewAdapter.userManager.m10041d("user_cat", Integer.toString(tvCategory.m17505a()));
            if (this.tvCategoryListRecyclerViewAdapter.fragment instanceof LiveTvLegacyFragment) {
                LiveTvLegacyFragment liveTvLegacyFragment = (LiveTvLegacyFragment) this.tvCategoryListRecyclerViewAdapter.fragment;
                liveTvLegacyFragment.m21537a(tvCategory.m17505a(), false);
                liveTvLegacyFragment.f14947a.m1790h(liveTvLegacyFragment.f14949c);
                liveTvLegacyFragment.f14947a.m1791i(liveTvLegacyFragment.f14948b);
                liveTvLegacyFragment.f14949c.requestFocus();
            } else if (this.tvCategoryListRecyclerViewAdapter.fragment instanceof CatchupFragment) {
                ((CatchupFragment) this.tvCategoryListRecyclerViewAdapter.fragment).m10892q().mo193a().mo176a((int) R.anim.slide_in_left, (int) R.anim.slide_out_left).mo183b(R.id.catchup_sidebar_layout, ChannelSelectFragment.m21401d(tvCategory.m17505a())).mo180a(null).mo182b();
                ((CatchupFragment) this.tvCategoryListRecyclerViewAdapter.fragment).m21390h(true);
            }
            if (this.tvCategoryListRecyclerViewAdapter.tvCategoryOnSelected != null) {
                this.tvCategoryListRecyclerViewAdapter.tvCategoryOnSelected.mo2747a(tvCategory);
            }
            if (this.tvCategoryListRecyclerViewAdapter.fragment instanceof LiveTvLegacyFragment) {
                ((LiveTvLegacyFragment) this.tvCategoryListRecyclerViewAdapter.fragment).m21550f(intValue);
            }
        }
    }

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        /* renamed from: n */
        final TextView f12857n;
        /* renamed from: o */
        final /* synthetic */ TvCategoryListRecyclerViewAdapter f12858o;

        ViewHolder(TvCategoryListRecyclerViewAdapter tvCategoryListRecyclerViewAdapter, View view) {
            super(view);
            this.f12858o = tvCategoryListRecyclerViewAdapter;
            this.f12857n = (TextView) view.findViewById(R.id.category_name);
        }
    }

    public TvCategoryListRecyclerViewAdapter(Fragment fragment, List<TvCategory> list, OnSelected<TvCategory> onSelected) {
        this.tvCategories = list;
        this.fragment = fragment;
        this.tvCategoryOnSelected = onSelected;
        this.userManager = new UserManager(fragment.getContext());
    }

    /* renamed from: a */
    public int getItemCount() {
        return this.tvCategories.size();
    }

    /* renamed from: a */
    public long mo36a(int i) {
        return super.mo36a(i & this.tvCategories.size());
    }

    /* renamed from: a */
    public ViewHolder m17475a(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tv_category_content, viewGroup, false));
    }

    /* renamed from: a */
    public void m17477a(ViewHolder viewHolder, int i) {
        LinearLayout linearLayout = (LinearLayout) viewHolder.f12857n.getParent();
        viewHolder.f12857n.setText(((TvCategory) this.tvCategories.get(i)).m17508b());
        linearLayout.setOnClickListener(this.f12878e);
        linearLayout.setTag(Integer.valueOf(i));
    }

    /* renamed from: b */
    public /* synthetic */ android.support.v7.widget.RecyclerView.ViewHolder mo40b(ViewGroup viewGroup, int i) {
        return m17475a(viewGroup, i);
    }
}
