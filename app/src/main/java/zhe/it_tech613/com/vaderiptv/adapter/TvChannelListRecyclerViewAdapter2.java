package zhe.it_tech613.com.vaderiptv.adapter;

import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import tv.danmaku.ijk.media.player.IjkMediaCodecInfo;
import zhe.it_tech613.com.vaderiptv.fragment.LiveTvFragment;
import zhe.it_tech613.com.vaderiptv.models.TvChannel;
import zhe.it_tech613.com.vaderiptv.models.TvProgram;
import zhe.it_tech613.com.vaderiptv.utils.IStreamable;
import zhe.it_tech613.com.vaderiptv.utils.LayoutUtils;

public class TvChannelListRecyclerViewAdapter2 extends Adapter<TvChannelListRecyclerViewAdapter2.ViewHolder> {
    /* renamed from: a */
    SimpleDraweeView simpleDraweeView;
    /* renamed from: b */
    private final LiveTvFragment liveTvFragment;
    /* renamed from: c */
    private final List<TvChannel> tvChannels;
    /* renamed from: d */
    private LinearLayoutManager linearLayoutManager;
    /* renamed from: e */
    private UserManager userManager;
    /* renamed from: f */
    private int f12895f = -1;
    /* renamed from: g */
    private TextView f12896g;
    /* renamed from: h */
    private TextView f12897h;
    /* renamed from: i */
    private TextView f12898i;
    /* renamed from: j */
    private TextView f12899j;
    /* renamed from: k */
    private TextView f12900k;
    /* renamed from: l */
    private ProgressBar progressBar;
    /* renamed from: m */
    private ImageView imageView;
    /* renamed from: n */
    private int f12903n;
    /* renamed from: o */
    private DateTimeFormatter f12904o = DateTimeFormat.forPattern("hh:mm");
    /* renamed from: p */
    private final OnLongClickListener f12905p = new C06851(this);
    /* renamed from: q */
    private final OnClickListener f12906q = new C06862(this);

    /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter2$1 */
    class C06851 implements OnLongClickListener {
        /* renamed from: a */
        final /* synthetic */ TvChannelListRecyclerViewAdapter2 f8204a;

        /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter2$1$1 */
        class C12581 extends FavoritesSuccessCallBack {
            /* renamed from: a */
            final /* synthetic */ C06851 f12885a;

            C12581(C06851 c06851) {
                this.f12885a = c06851;
            }

            /* renamed from: a */
            public void mo2042a(boolean z, TvChannel tvChannel, VodItem vodItem) {
                this.f12885a.f8204a.liveTvFragment.m21510a(z, tvChannel);
            }
        }

        C06851(TvChannelListRecyclerViewAdapter2 tvChannelListRecyclerViewAdapter2) {
            this.f8204a = tvChannelListRecyclerViewAdapter2;
        }

        public boolean onLongClick(View view) {
            view = this.f8204a.m17485g();
            if (view == null) {
                return false;
            }
            TvChannelContextManager.m10018a((VaderActivity) this.f8204a.liveTvFragment.m10890o()).m10021a((VaderActivity) this.f8204a.liveTvFragment.m10890o(), (TvChannel) this.f8204a.tvChannels.get(((Integer) view.getTag()).intValue()), new C12581(this), null, null, true);
            return true;
        }
    }

    /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter2$2 */
    class C06862 implements OnClickListener {
        /* renamed from: a */
        final /* synthetic */ TvChannelListRecyclerViewAdapter2 f8205a;

        C06862(TvChannelListRecyclerViewAdapter2 tvChannelListRecyclerViewAdapter2) {
            this.f8205a = tvChannelListRecyclerViewAdapter2;
        }

        public void onClick(View view) {
            view = this.f8205a.m17485g();
            if (view != null) {
                int intValue = ((Integer) view.getTag()).intValue();
                IStreamable iStreamable = (TvChannel) this.f8205a.tvChannels.get(intValue);
                this.f8205a.userManager.m10041d("user_channel", Integer.toString(iStreamable.m17529f()));
                FullScreenVideoActivity.m24060a(this.f8205a.liveTvFragment.mo2108c(), iStreamable);
                this.f8205a.liveTvFragment.m21519e(intValue);
                this.f8205a.liveTvFragment.m21522h(false);
            }
        }
    }

    /* renamed from: tv.vaders.apk.livetv.adapter.TvChannelListRecyclerViewAdapter2$3 */
    class C06873 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TvChannelListRecyclerViewAdapter2 f8206a;

        C06873(TvChannelListRecyclerViewAdapter2 tvChannelListRecyclerViewAdapter2) {
            this.f8206a = tvChannelListRecyclerViewAdapter2;
        }

        public void run() {
            View a = this.f8206a.m17485g();
            if (a != null) {
                a.performClick();
            }
        }
    }

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        /* renamed from: n */
        final SimpleDraweeView f12886n;
        /* renamed from: o */
        final TextView f12887o;
        /* renamed from: p */
        final TextView f12888p;
        /* renamed from: q */
        final /* synthetic */ TvChannelListRecyclerViewAdapter2 f12889q;

        ViewHolder(TvChannelListRecyclerViewAdapter2 tvChannelListRecyclerViewAdapter2, View view) {
            super(view);
            this.f12889q = tvChannelListRecyclerViewAdapter2;
            this.f12886n = (SimpleDraweeView) view.findViewById(R.id.channel_icon);
            this.f12887o = (TextView) view.findViewById(R.id.channel_name);
            this.f12888p = (TextView) view.findViewById(R.id.channel_number);
        }
    }

    public TvChannelListRecyclerViewAdapter2(Fragment fragment, List<TvChannel> list, int i) {
        this.tvChannels = list;
        this.liveTvFragment = (LiveTvFragment) fragment;
        this.userManager = new UserManager(this.liveTvFragment.mo2108c().getApplicationContext());
        this.f12903n = i;
        this.simpleDraweeView = (SimpleDraweeView) this.liveTvFragment.f14940d.findViewById(R.id.infobar_channel_icon);
        this.f12896g = (TextView) this.liveTvFragment.f14940d.findViewById(R.id.infobar_channel_number);
        this.f12897h = (TextView) this.liveTvFragment.f14940d.findViewById(R.id.live_tv_information_bar_info_chanName);
        this.f12898i = (TextView) this.liveTvFragment.f14940d.findViewById(R.id.live_tv_information_bar_info_progName);
        this.f12899j = (TextView) this.liveTvFragment.f14940d.findViewById(R.id.live_tv_information_bar_info_startTime);
        this.f12900k = (TextView) this.liveTvFragment.f14940d.findViewById(R.id.live_tv_information_bar_info_endTime);
        this.progressBar = (ProgressBar) this.liveTvFragment.f14940d.findViewById(R.id.live_tv_information_bar_info_progress);
        this.imageView = (ImageView) this.liveTvFragment.f14940d.findViewById(R.id.infobar_info_navigation_arrow);
        LayoutUtils.setImageSource(this.imageView, R.drawable.livetv_front);
    }

    /* renamed from: g */
    private View m17485g() {
        int p = this.linearLayoutManager.m11965p();
        if (p == -1) {
            return null;
        }
        View c = this.linearLayoutManager.mo566c(p - this.f12903n);
        return c != null ? c : this.linearLayoutManager.mo566c(p);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.livetv2_channel_content, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LinearLayout linearLayout = (LinearLayout) viewHolder.f12886n.getParent();
        TvChannel tvChannel = (TvChannel) this.tvChannels.get(i % this.tvChannels.size());
        String g = tvChannel.m17530g();
        if (g == null || g.isEmpty()) {
            g = "http://bogus";
        }
        viewHolder.f12888p.setText(String.valueOf((i % this.tvChannels.size()) + 1));
        viewHolder.f12886n.setImageURI(g);
        viewHolder.f12887o.setText(tvChannel.m17528e());
        linearLayout.setOnClickListener(this.f12906q);
        if (!"vaders".equals("rogue")) {
            linearLayout.setOnLongClickListener(this.f12905p);
        }
        linearLayout.setTag(Integer.valueOf(i % this.tvChannels.size()));
    }

    /* renamed from: a */
    public int getItemCount() {
        return this.tvChannels.size() * IjkMediaCodecInfo.RANK_MAX;
    }

    /* renamed from: a */
    public long mo36a(int i) {
        return super.mo36a(i % this.tvChannels.size());
    }

    /* renamed from: a */
    public ViewHolder m17488a(ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.livetv2_channel_content, viewGroup, false));
    }

    /* renamed from: a */
    public void mo2043a(RecyclerView recyclerView) {
        super.mo2043a(recyclerView);
        this.linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
    }

//    /* renamed from: a */
//    public void m17491a(ViewHolder viewHolder, int i) {
//        LinearLayout linearLayout = (LinearLayout) viewHolder.f12886n.getParent();
//        TvChannel tvChannel = (TvChannel) this.tvChannels.get(i % this.tvChannels.size());
//        String g = tvChannel.m17530g();
//        if (g == null || g.isEmpty()) {
//            g = "http://bogus";
//        }
//        viewHolder.f12888p.setText(String.valueOf((i % this.tvChannels.size()) + 1));
//        viewHolder.f12886n.setImageURI(g);
//        viewHolder.f12887o.setText(tvChannel.m17528e());
//        linearLayout.setOnClickListener(this.f12906q);
//        if (!"vaders".equals("rogue")) {
//            linearLayout.setOnLongClickListener(this.f12905p);
//        }
//        linearLayout.setTag(Integer.valueOf(i % this.tvChannels.size()));
//    }

    /* renamed from: b */
    public /* synthetic */ android.support.v7.widget.RecyclerView.ViewHolder mo40b(ViewGroup viewGroup, int i) {
        return m17488a(viewGroup, i);
    }

    /* renamed from: b */
    public void m17493b() {
        View g = m17485g();
        if (g != null) {
            int intValue = ((Integer) g.getTag()).intValue();
            if (this.f12895f != intValue) {
                TvChannel tvChannel = (TvChannel) this.tvChannels.get(intValue);
                String g2 = tvChannel.m17530g();
                if (g2 == null || g2.isEmpty()) {
                    g2 = "http://bogus";
                }
                this.simpleDraweeView.setImageURI(g2);
                ((TextView) this.liveTvFragment.f14940d.findViewById(R.id.infobar_channel_number)).setText(String.valueOf((intValue % this.tvChannels.size()) + 1));
                this.f12895f = intValue;
                this.f12897h.setText(tvChannel.m17528e());
                TvProgram j = tvChannel.getNow();
                int i = 0;
                if (j != null) {
                    this.f12898i.setText(j.m17563q());
                    float c = (float) (DateTime.m23695a(DateTimeZone.f7773a).mo2720c() - j.m17552f());
                    this.progressBar.setProgress((int) ((c / ((float) (j.m17553g() - j.m17552f()))) * 100.0f));
                    this.f12899j.setText(this.f12904o.m9738a(j.m17550d()));
                    this.f12900k.setText(this.f12904o.m9738a(j.m17555i()));
                } else {
                    this.f12898i.setText("Not Available");
                    DateTime.m23695a(DateTimeZone.f7773a);
                    this.progressBar.setProgress(0);
                    this.f12899j.setText("N/A");
                    this.f12900k.setText("N/A");
                }
                ImageView imageView = this.imageView;
                if (tvChannel.m17524c() <= 0) {
                    i = 8;
                }
                imageView.setVisibility(i);
                this.liveTvFragment.am();
            }
        }
    }

    /* renamed from: c */
    public void m17494c() {
        new Handler().postDelayed(new C06873(this), 1);
    }
}
