package zhe.it_tech613.com.vaderiptv.decompile;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class WrappedScrollView extends RecyclerView {
    /* renamed from: J */
    private LinearLayoutManager linearLayoutManager;
    /* renamed from: K */
    private EventListener eventListener;

    public interface EventListener {
    }

    private class OnScrollListener extends android.support.v7.widget.RecyclerView.OnScrollListener {
        /* renamed from: a */
        final /* synthetic */ WrappedScrollView wrappedScrollView;
        /* renamed from: b */
        private LinearLayoutManager layoutManager;

        public OnScrollListener(WrappedScrollView wrappedScrollView, int i, LinearLayoutManager linearLayoutManager) {
            this.wrappedScrollView = wrappedScrollView;
            this.layoutManager = linearLayoutManager;
        }

        /* renamed from: a */
        public void onScrollStateChanged(RecyclerView recyclerView, int i) {
            super.onScrollStateChanged(recyclerView, i);
        }

        /* renamed from: a */
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            this.layoutManager.findFirstCompletelyVisibleItemPosition();
            this.layoutManager.findLastCompletelyVisibleItemPosition();
            this.layoutManager.getItemCount();
            super.onScrolled(recyclerView, i, i2);
        }
    }

    public WrappedScrollView(Context context) {
        super(context);
        this.linearLayoutManager = new KeyboardLinearLayoutManager(context, VERTICAL, false);
    }

    public WrappedScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.linearLayoutManager = new KeyboardLinearLayoutManager(context, VERTICAL, false);
    }

    public WrappedScrollView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.linearLayoutManager = new KeyboardLinearLayoutManager(context, VERTICAL, false);
    }

    /* renamed from: k  m23205k */
    public void findViewHolder(final int i) {
        new Handler().postDelayed(new Runnable() {
            /* renamed from: b */
            final WrappedScrollView wrappedScrollView = null;

            public void run() {
                ViewHolder viewHolder = this.wrappedScrollView.findViewHolderForLayoutPosition(i);//findViewHolderForPosition//findViewHolderForLayoutPosition//findViewHolderForAdapterPosition
                if (viewHolder != null) {
                    viewHolder.itemView.requestFocus();
                }
            }
        }, 1);
    }

    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        setHasFixedSize(true);
        setLayoutManager(linearLayoutManager);
        if (adapter.getItemCount() > 1) {
            addOnScrollListener(new OnScrollListener(this, adapter.getItemCount(), linearLayoutManager));
            scrollToPosition(0);
        }
    }

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }
}