package zhe.it_tech613.com.vaderiptv.decompile;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import zhe.it_tech613.com.vaderiptv.adapter.TvChannelListRecyclerViewAdapter2;

public class LiveTvWrappedScrollView extends RecyclerView {
    /* renamed from: J */
    private LinearLayoutManager linearLayoutManager;

    public LiveTvWrappedScrollView(Context context) {
        super(context);
        setLayoutManager(context);
    }

    public LiveTvWrappedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        setLayoutManager(context);
    }

    public LiveTvWrappedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        setLayoutManager(context);
    }

    /* renamed from: a */
    private void setLayoutManager(Context context) {
        this.linearLayoutManager = new LinearLayoutManager(this, context, VERTICAL, false) {
            /* renamed from: a */
            final LiveTvWrappedScrollView liveTvWrappedScrollView;

            /* renamed from: c */
            public void onLayoutChildren(Recycler recycler, State state) {
                super.onLayoutChildren(recycler, state);
                if (this.liveTvWrappedScrollView.getAdapter() instanceof TvChannelListRecyclerViewAdapter2) {
                    ((TvChannelListRecyclerViewAdapter2) this.liveTvWrappedScrollView.getAdapter()).m17493b();
                } else if (this.liveTvWrappedScrollView.getAdapter() instanceof TvCategoryListRecyclerViewAdapter2) {
                    ((TvCategoryListRecyclerViewAdapter2) this.liveTvWrappedScrollView.getAdapter()).m17467c();
                }
            }
        };
        setLayoutManager(this.linearLayoutManager);
        setHasFixedSize(true);
    }

    /* renamed from: k */
    public void m23179k(final int i) {
        new Handler().postDelayed(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ LiveTvWrappedScrollView liveTvWrappedScrollView = null;

            public void run() {
                ViewHolder viewHolder = this.liveTvWrappedScrollView.m18525d(i);
                if (viewHolder != null) {
                    viewHolder.f2220a.requestFocus();
                }
            }
        }, 1);
    }
}
