package zhe.it_tech613.com.vaderiptv.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;

import zhe.it_tech613.com.vaderiptv.VadersAPI;
import zhe.it_tech613.com.vaderiptv.fragment.LiveTvLegacyFragment;
import zhe.it_tech613.com.vaderiptv.models.TvChannel;
import zhe.it_tech613.com.vaderiptv.models.TvProgram;
import zhe.it_tech613.com.vaderiptv.utils.FavoritesSuccessCallBack;
import zhe.it_tech613.com.vaderiptv.utils.IStreamable;

public class TvChannelListRecyclerViewAdapter extends Adapter<TvChannelListRecyclerViewAdapter.ViewHolder> {
    /* renamed from: a */
    private final LiveTvLegacyFragment liveTvLegacyFragment;
    /* renamed from: b */
    private final List<TvChannel> tvChannels;
    /* renamed from: c */
    private final boolean f12909c;
    /* renamed from: d */
    private final OnLongClickListener onLongClickListener = new LongClickListener(this);
    /* renamed from: e */
    private final OnClickListener f12911e = new C06842(this);

    /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter$1 */
    class LongClickListener implements OnLongClickListener {
        /* renamed from: a */
        final /* synthetic */ TvChannelListRecyclerViewAdapter f8199a;

        /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter$1$1 */
        class C12571 extends FavoritesSuccessCallBack {
            /* renamed from: a */
            final /* synthetic */ LongClickListener f12879a;

            C12571(LongClickListener longClickListener) {
                this.f12879a = longClickListener;
            }

            /* renamed from: a */
            public void mo2042a(boolean z, TvChannel tvChannel, VodItem vodItem) {
                this.f12879a.f8199a.liveTvLegacyFragment.m21540a(z, tvChannel);
            }
        }

        LongClickListener(TvChannelListRecyclerViewAdapter tvChannelListRecyclerViewAdapter) {
            this.f8199a = tvChannelListRecyclerViewAdapter;
        }

        public boolean onLongClick(View view) {
            TvChannelContextManager.m10018a((VaderActivity) this.f8199a.liveTvLegacyFragment.m10890o()).m10021a((VaderActivity) this.f8199a.liveTvLegacyFragment.m10890o(), (TvChannel) this.f8199a.tvChannels.get(((Integer) view.getTag()).intValue()), new C12571(this), null, null, true);
            return true;
        }
    }

    /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter$2 */
    class C06842 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ TvChannelListRecyclerViewAdapter f8203a;

        /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter$2$1 */
        class C06811 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C06842 f8200a;

            C06811(C06842 c06842) {
                this.f8200a = c06842;
            }

            public void run() {
                this.f8200a.f8203a.liveTvLegacyFragment.mo2107b();
            }
        }

        /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter$2$2 */
        class C06832 implements Runnable {
            /* renamed from: a */
            final /* synthetic */ C06842 f8202a;

            /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter$2$2$1 */
            class C06821 implements Runnable {
                /* renamed from: a */
                final /* synthetic */ C06832 f8201a;

                C06821(C06832 c06832) {
                    this.f8201a = c06832;
                }

                public void run() {
                    this.f8201a.f8202a.f8203a.liveTvLegacyFragment.f14947a.m1791i(this.f8201a.f8202a.f8203a.liveTvLegacyFragment.f14949c);
                }
            }

            C06832(C06842 c06842) {
                this.f8202a = c06842;
            }

            public void run() {
                this.f8202a.f8203a.liveTvLegacyFragment.m10890o().runOnUiThread(new C06821(this));
            }
        }

        C06842(TvChannelListRecyclerViewAdapter tvChannelListRecyclerViewAdapter) {
            this.f8203a = tvChannelListRecyclerViewAdapter;
        }

        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            IStreamable iStreamable = (TvChannel) this.f8203a.tvChannels.get(intValue);
            if (this.f8203a.f12909c) {
                FullScreenVideoActivity.m24060a((VaderActivity) this.f8203a.liveTvLegacyFragment.m10890o(), iStreamable);
                Handler handler = new Handler();
                handler.post(new C06811(this));
                handler.postDelayed(new C06832(this), 3000);
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, FullScreenVideoActivity.class);
                intent.putExtra("videoUrl", VadersAPI.m9981a(this.f8203a.liveTvLegacyFragment.m10890o().getApplicationContext(), iStreamable.m17529f()).m9977b());
                context.startActivity(intent);
            }
            this.f8203a.liveTvLegacyFragment.m21548e(intValue);
        }
    }

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        /* renamed from: n */
        final SimpleDraweeView f12880n;
        /* renamed from: o */
        final TextView f12881o;
        /* renamed from: p */
        final TextView f12882p;
        /* renamed from: q */
        final TextView f12883q;
        /* renamed from: r */
        final /* synthetic */ TvChannelListRecyclerViewAdapter f12884r;

        ViewHolder(TvChannelListRecyclerViewAdapter tvChannelListRecyclerViewAdapter, View view) {
            super(view);
            this.f12884r = tvChannelListRecyclerViewAdapter;
            this.f12880n = (SimpleDraweeView) view.findViewById(R.id.channel_icon);
            this.f12881o = (TextView) view.findViewById(R.id.channel_name);
            this.f12882p = (TextView) view.findViewById(R.id.currently_playing);
            this.f12883q = (TextView) view.findViewById(R.id.channel_number);
        }
    }

    public TvChannelListRecyclerViewAdapter(LiveTvLegacyFragment liveTvLegacyFragment, List<TvChannel> list, boolean z) {
        this.tvChannels = list;
        this.liveTvLegacyFragment = liveTvLegacyFragment;
        this.f12909c = z;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.livetv_channel_content, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

    }

    /* renamed from: a */
    public int getItemCount() {
        return this.tvChannels.size();
    }

    /* renamed from: a */
    public long mo36a(int i) {
        return super.mo36a(i);
    }

    /* renamed from: a */
    public void m17502a(ViewHolder viewHolder, int i) {
        LinearLayout linearLayout = (LinearLayout) viewHolder.f12880n.getParent();
        TvChannel tvChannel = (TvChannel) this.tvChannels.get(i);
        String g = tvChannel.m17530g();
        if (g == null || g.isEmpty()) {
            g = "http://bogus";
        }
        viewHolder.f12883q.setText(String.format("%03d", new Object[]{Integer.valueOf(i + 1)}));
        viewHolder.f12880n.setImageURI(g);
        viewHolder.f12881o.setText(tvChannel.m17528e());
        viewHolder.f12882p.setText("");
        TvProgram j = tvChannel.m17533j();
        if (j != null) {
            viewHolder.f12882p.setText(j.m17556j());
        }
        linearLayout.setOnClickListener(this.f12911e);
        linearLayout.setOnLongClickListener(this.onLongClickListener);
        linearLayout.setTag(Integer.valueOf(i));
    }

    /* renamed from: b */
    public /* synthetic */ android.support.v7.widget.RecyclerView.ViewHolder mo40b(ViewGroup viewGroup, int i) {
        return m17500a(viewGroup, i);
    }
}
