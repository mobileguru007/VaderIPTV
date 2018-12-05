package zhe.it_tech613.com.vaderiptv.models;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.graphics.Color;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.json.JSONArray;
import org.json.JSONObject;
import tv.danmaku.ijk.media.player.IjkMediaMeta;
import zhe.it_tech613.com.vaderiptv.R;
import zhe.it_tech613.com.vaderiptv.activity.MainActivity;
import zhe.it_tech613.com.vaderiptv.utils.IStreamable;

public class TvProgram implements IStreamable {
    /* renamed from: a */
    private TvChannel tvChannel;
    /* renamed from: b */
    private DateTime startTime;
    /* renamed from: c */
    private DateTime endTime;
    /* renamed from: d */
    private String f12926d;
    /* renamed from: e */
    private String f12927e;
    /* renamed from: f */
    private int f12928f = -1;
    /* renamed from: g */
    private String f12929g = null;
    /* renamed from: h */
    private boolean f12930h;

    /* renamed from: a */
    public static List<TvProgram> getTvProgramListFromArray(TvChannel tvChannel, JSONArray jSONArray) {
        List<TvProgram> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                TvProgram a = getTvProgramFromJson(jSONArray.getJSONObject(i));
                if (a != null) {
                    a.m17543a(tvChannel);
                    arrayList.add(a);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    public static TvProgram getTvProgramFromJson(JSONObject jSONObject) {
        try {
            TvProgram tvProgram = new TvProgram();
            tvProgram.setStart(DateTime.parse(jSONObject.getString("start")));
            tvProgram.setEnd(DateTime.parse(jSONObject.getString("stop")));
            tvProgram.setDescription(jSONObject.getString("desc"));
            tvProgram.setTitle(jSONObject.getString("title"));
            if (!MainActivity.f15871o.isEmpty()) {
                int intValue;
                JSONArray jSONArray = jSONObject.getJSONArray("cats");
                if (jSONArray != null) {
                    if (jSONArray.length() != 0) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int i = 0; i < jSONArray.length(); i++) {
                            Integer num = (Integer) MainActivity.f15871o.get(jSONArray.getString(i).toLowerCase());
                            if (num != null) {
                                if (stringBuilder.length() != 0) {
                                    stringBuilder.append(", ");
                                }
                                stringBuilder.append(jSONArray.getString(i));
                                if (tvProgram.m17558l() == -1) {
                                    tvProgram.m17540a(num.intValue());
                                }
                            }
                        }
                        if (stringBuilder.length() > 0) {
                            tvProgram.m17541a(stringBuilder.toString());
                        } else {
                            intValue = ((Integer) MainActivity.f15871o.get("none")).intValue();
                            tvProgram.m17540a(intValue);
                        }
                    }
                }
                intValue = ((Integer) MainActivity.f15871o.get("none")).intValue();
                tvProgram.m17540a(intValue);
            }
            return tvProgram;
        } catch (Throwable e) {
            Log.e("TVPROG", "Exception converting tv program: ", e);
            return null;
        }
    }

    /* renamed from: b */
    public static Map<String, Integer> m17536b(final Context context) {
        final Map hashMap = new HashMap();
        final Map<String, Integer> hashMap2 = new HashMap();
        Thread thread = new Thread(new Runnable() {
            public void run() {
                XmlResourceParser xml;
                int i;
                int eventType;
                String text;
                try {
                    xml = context.getResources().getXml(R.xml.epg_colors);
                    i = -1;
                    for (eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                        if (eventType == 2) {
                            if ("epg".equals(xml.getName())) {
                            } else if ("genre".equals(xml.getName())) {
                                i = xml.getAttributeIntValue(null, IjkMediaMeta.IJKM_KEY_TYPE, -1);
                            }
                        }
                        if (eventType == 4) {
                            text = xml.getText();
                            if (!(i == -1 || text == null || text.isEmpty())) {
                                hashMap.put(Integer.valueOf(i), Integer.valueOf(Color.parseColor(text)));
                            }
                        }
                    }
                } catch (Exception e) {
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append("Parsing epg_color.xml failed: ");
                    stringBuilder.append(e.getMessage());
                    Log.d("EpgFragment", stringBuilder.toString());
                    hashMap.clear();
                }
                try {
                    xml = context.getResources().getXml(R.xml.epg_genres);
                    i = -1;
                    for (eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                        if (eventType == 2) {
                            if ("genres".equals(xml.getName())) {
                            } else if ("genre".equals(xml.getName())) {
                                i = xml.getAttributeIntValue(null, IjkMediaMeta.IJKM_KEY_TYPE, -1);
                            }
                        }
                        if (eventType == 4) {
                            text = xml.getText();
                            if (!(i == -1 || text == null || text.isEmpty())) {
                                hashMap2.put(text, (Integer) hashMap.get(Integer.valueOf(i)));
                            }
                        }
                    }
                } catch (Exception e2) {
                    StringBuilder stringBuilder2 = new StringBuilder();
                    stringBuilder2.append("Parsing genres.xml failed: ");
                    stringBuilder2.append(e2.getMessage());
                    Log.d("EpgFragment", stringBuilder2.toString());
                    hashMap2.clear();
                }
            }
        });
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hashMap2;
    }

    /* renamed from: a */
    public Object mo2044a() {
        return this.tvChannel.mo2044a();
    }

    /* renamed from: a */
    public VadersApiUrl mo2045a(Context context) {
        return this.tvChannel.mo2045a(context);
    }

    /* renamed from: a */
    public VadersApiUrl mo2046a(Context context, int i) {
        return this.tvChannel.mo2046a(context, i);
    }

    /* renamed from: a */
    public void m17540a(int i) {
        this.f12928f = i;
    }

    /* renamed from: a */
    public void m17541a(String str) {
        this.f12929g = str;
    }

    /* renamed from: a */
    public void setStart(DateTime dateTime) {
        this.startTime = dateTime;
    }

    /* renamed from: a */
    public void m17543a(TvChannel tvChannel) {
        this.tvChannel = tvChannel;
    }

    /* renamed from: a */
    public void m17544a(boolean z) {
        this.f12930h = z;
    }

    /* renamed from: b */
    public TvChannel m17545b() {
        return this.tvChannel;
    }

    /* renamed from: b */
    public void setTitle(String str) {
        this.f12926d = str;
    }

    /* renamed from: b */
    public void setEnd(DateTime dateTime) {
        this.endTime = dateTime;
    }

    /* renamed from: c */
    public DateTime getStart() {
        return this.startTime;
    }

    /* renamed from: c */
    public void setDescription(String str) {
        this.f12927e = str;
    }

    /* renamed from: d */
    public DateTime m17550d() {
        return new DateTime(this.startTime, DateTimeZone.forID(DateTimeZone.getDefault().toString()));
    }

    /* renamed from: e */
    public String m17551e() {
        return this.f12929g;
    }

    public boolean equals(Object obj) {
        boolean z = true;
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TvProgram)) {
            return false;
        }
        TvProgram tvProgram = (TvProgram) obj;
        if (m17545b().m17529f() != tvProgram.m17545b().m17529f() || !getStart().equals(tvProgram.getStart()) || !getEndTime().equals(tvProgram.getEndTime()) || !m17563q().equals(tvProgram.m17563q())) {
            z = false;
        }
        return z;
    }

    /* renamed from: f */
    public long getStartMilli() {
        return this.startTime.getMillis();
    }

    /* renamed from: g */
    public long getEndMilli() {
        return this.endTime.getMillis();
    }

    /* renamed from: h */
    public DateTime getEndTime() {
        return this.endTime;
    }

    /* renamed from: i */
    public DateTime m17555i() {
        return new DateTime(this.endTime, DateTimeZone.forID(DateTimeZone.getDefault().toString()));
    }

    /* renamed from: j */
    public String m17556j() {
        return this.f12926d;
    }

    /* renamed from: k */
    public String m17557k() {
        return this.f12927e;
    }

    /* renamed from: l */
    public int m17558l() {
        return this.f12928f;
    }

    /* renamed from: m */
    public boolean is_loading_now() {
        DateTime dateTime = DateTime.now(DateTimeZone.UTC);
        if (dateTime.isAfter(this.startTime) || dateTime.isEqual(this.startTime)) {
            if (!dateTime.isAfter(this.endTime)) {
                if (dateTime.isBefore(this.endTime)) {
                }
            }
            return true;
        }
        return false;
    }

    /* renamed from: n */
    public TvProgram m17560n() {
        int a = this.tvChannel.m17511a(this);
        int size = this.tvChannel.m17531h().size() - 1;
        if (a != -1) {
            a++;
            if (a <= size) {
                return (TvProgram) this.tvChannel.m17531h().get(a);
            }
        }
        return null;
    }

    /* renamed from: o */
    public TvProgram m17561o() {
        int a = this.tvChannel.m17511a(this) - 1;
        int size = this.tvChannel.m17531h().size() - 1;
        if (a >= 0) {
            if (a < size) {
                return (TvProgram) this.tvChannel.m17531h().get(a);
            }
        }
        return null;
    }

    /* renamed from: p */
    public boolean m17562p() {
        return this.f12930h;
    }

    /* renamed from: q */
    public String m17563q() {
        return this.f12926d;
    }
}
