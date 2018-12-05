package zhe.it_tech613.com.vaderiptv.models;

import android.content.Context;
import android.util.Log;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;
import org.json.JSONArray;
import org.json.JSONObject;

import zhe.it_tech613.com.vaderiptv.VadersAPI;
import zhe.it_tech613.com.vaderiptv.utils.IStreamable;

public class TvChannel extends VaderModel implements IStreamable {
    /* renamed from: a */
    private TvChannel f12914a;
    /* renamed from: b */
    private TvChannel f12915b;
    /* renamed from: c */
    private String f12916c;
    /* renamed from: d */
    private int f12917d;
    /* renamed from: e */
    private int f12918e;
    /* renamed from: f */
    private String f12919f;
    /* renamed from: g */
    private int f12920g;
    /* renamed from: h */
    private List<TvProgram> f12921h;
    /* renamed from: i */
    private String f12922i;

    /* renamed from: a */
    public static List<TvChannel> m17509a(JSONArray jSONArray) {
        List<TvChannel> linkedList = new LinkedList();
        long currentTimeMillis = System.currentTimeMillis();
        int i = 0;
        while (i < jSONArray.length()) {
            try {
                TvChannel a = m17510a(jSONArray.getJSONObject(i));
                if (a != null) {
                    if (i > 0) {
                        int i2 = i - 1;
                        TvChannel tvChannel = (TvChannel) linkedList.get(i2);
                        a.m17519a(tvChannel);
                        tvChannel.m17523b(a);
                        linkedList.set(i2, tvChannel);
                    }
                    linkedList.add(a);
                }
                i++;
            } catch (Exception e) {
                return Collections.emptyList();
            }
        }
        if (linkedList.size() > 1) {
            ((TvChannel) linkedList.get(0)).m17519a((TvChannel) linkedList.get(linkedList.size() - 1));
            ((TvChannel) linkedList.get(linkedList.size() - 1)).m17523b((TvChannel) linkedList.get(0));
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Time to parse ");
        stringBuilder.append(linkedList.size());
        stringBuilder.append(" TvChannels: ");
        stringBuilder.append(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - currentTimeMillis));
        stringBuilder.append(" seconds.");
        Log.d("TvChannel", stringBuilder.toString());
        return linkedList;
    }

    /* renamed from: a */
    public static TvChannel m17510a(JSONObject jSONObject) {
        TvChannel tvChannel = new TvChannel();
        try {
            tvChannel.m17517a(jSONObject.getString("stream_display_name"));
            tvChannel.m17525c(jSONObject.getInt("category_id"));
            tvChannel.m17522b(jSONObject.getString("stream_icon"));
            tvChannel.m17526c(jSONObject.getString("channel_id"));
            tvChannel.m17516a(jSONObject.getInt("tv_archive_duration"));
            tvChannel.m17521b(jSONObject.getInt("id"));
            if (jSONObject.has("programs")) {
                try {
                    tvChannel.m17518a(TvProgram.getTvProgramListFromArray(tvChannel, jSONObject.getJSONArray("programs")));
                } catch (Throwable e) {
                    Log.e("TvChannel:fromJson", "Error extracting programs from TvChannel", e);
                    tvChannel.m17518a(Collections.emptyList());
                }
            }
            return tvChannel;
        } catch (Throwable e2) {
            Log.e("TvChannel:fromJson", "Exception while parsing TV Channel", e2);
            return null;
        }
    }

    /* renamed from: a */
    public int m17511a(TvProgram tvProgram) {
        for (int i = 0; i < this.f12921h.size(); i++) {
            if (tvProgram.equals(this.f12921h.get(i))) {
                return i;
            }
        }
        return -1;
    }

    /* renamed from: a */
    public Object mo2044a() {
        return m17530g();
    }

    /* renamed from: a */
    public VadersApiUrl mo2045a(Context context) {
        return VadersAPI.m9981a(context, m17529f());
    }

    /* renamed from: a */
    public VadersApiUrl mo2046a(Context context, int i) {
        return mo2045a(context);
    }

    /* renamed from: a */
    public TvProgram m17515a(DateTime dateTime) {
        if (this.f12921h.isEmpty()) {
            return null;
        }
        for (TvProgram tvProgram : this.f12921h) {
            if (tvProgram.getEndTime().isAfter((ReadableInstant) dateTime) && (tvProgram.getStart().isBefore((ReadableInstant) dateTime) || tvProgram.getStart().isEqual((ReadableInstant) dateTime))) {
                return tvProgram;
            }
        }
        return (TvProgram) this.f12921h.get(this.f12921h.size() - 1);
    }

    /* renamed from: a */
    public void m17516a(int i) {
        this.f12918e = i;
    }

    /* renamed from: a */
    public void m17517a(String str) {
        this.f12916c = str;
    }

    /* renamed from: a */
    public void m17518a(List<TvProgram> list) {
        this.f12921h = list;
    }

    /* renamed from: a */
    public void m17519a(TvChannel tvChannel) {
        this.f12914a = tvChannel;
    }

    /* renamed from: b */
    public TvChannel m17520b() {
        return this.f12914a;
    }

    /* renamed from: b */
    public void m17521b(int i) {
        this.f12917d = i;
    }

    /* renamed from: b */
    public void m17522b(String str) {
        this.f12919f = str;
    }

    /* renamed from: b */
    public void m17523b(TvChannel tvChannel) {
        this.f12915b = tvChannel;
    }

    /* renamed from: c */
    public int m17524c() {
        return this.f12918e;
    }

    /* renamed from: c */
    public void m17525c(int i) {
        this.f12920g = i;
    }

    /* renamed from: c */
    public void m17526c(String str) {
        this.f12922i = str;
    }

    /* renamed from: d */
    public TvChannel m17527d() {
        return this.f12915b;
    }

    /* renamed from: e */
    public String m17528e() {
        return this.f12916c;
    }

    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            if (m17528e() == null || !m17528e().equals(((TvChannel) obj).m17528e())) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: f */
    public int m17529f() {
        return this.f12917d;
    }

    /* renamed from: g */
    public String m17530g() {
        return this.f12919f;
    }

    /* renamed from: h */
    public List<TvProgram> m17531h() {
        return this.f12921h;
    }

    /* renamed from: i */
    public String m17532i() {
        return this.f12922i;
    }

    /* renamed from: j */
    public TvProgram getNow() {
        return m17515a(DateTime.now());
    }
}
