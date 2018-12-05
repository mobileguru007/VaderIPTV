package zhe.it_tech613.com.vaderiptv.models;

import android.content.Context;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import zhe.it_tech613.com.vaderiptv.VadersAPI;
import zhe.it_tech613.com.vaderiptv.utils.IStreamable;

public abstract class VodItem extends VaderModel implements IStreamable {
    /* renamed from: a */
    public static List<String> f13073a = new LinkedList();
    /* renamed from: b */
    private static SimpleDateFormat f13074b = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    /* renamed from: c */
    private String f13075c = "No title available";
    /* renamed from: d */
    private String f13076d = "No poster available";
    /* renamed from: e */
    private String f13077e = "No TV poster available";
    /* renamed from: f */
    private String f13078f = "Description not available";
    /* renamed from: g */
    private List<Integer> f13079g;
    /* renamed from: h */
    private String f13080h;
    /* renamed from: i */
    private float f13081i = 0.0f;
    /* renamed from: j */
    private float f13082j = 0.0f;
    /* renamed from: k */
    private int f13083k = -1;
    /* renamed from: l */
    private String f13084l = null;
    /* renamed from: m */
    private String f13085m;
    /* renamed from: n */
    private Date f13086n;
    /* renamed from: o */
    private int f13087o = -1;
    /* renamed from: p */
    private int f13088p = -1;
    /* renamed from: q */
    private String f13089q = "No show name available";
    /* renamed from: r */
    private int f13090r = -1;
    /* renamed from: s */
    private int f13091s = -1;
    /* renamed from: t */
    private boolean f13092t = false;

    /* renamed from: tv.vaders.apk.vod.model.VodItem$1 */
    static class C08361 implements Comparator<String> {
        C08361() {
        }

//        /* renamed from: a */
//        public int m10297a(String str, String str2) {
//            return -str.compareTo(str2);
//        }
//
//        public /* synthetic */ int compare(Object obj, Object obj2) {
//            return m10297a((String) obj, (String) obj2);
//        }

        @Override
        public int compare(String s, String t1) {
            return -s.compareTo(t1);
        }
    }

    static {
        f13074b.setTimeZone(TimeZone.getTimeZone("UTC"));
    }

    /* renamed from: a */
    private static Date m17772a(JSONObject jSONObject, VodType vodType) throws JSONException {
        if (!vodType.equals(VodType.tvepisode)) {
            if (!vodType.equals(VodType.movie)) {
                return new Date(jSONObject.getLong("added") * 1000);
            }
        }
        return new Date(jSONObject.getLong("added") * 1000);
    }

    /* renamed from: a */
    public static List<VodItem> m17773a(JSONObject jSONObject, VodType vodType, String str, List<String> list) {
        List<VodItem> linkedList = new LinkedList();
        if (jSONObject.has(str)) {
            try {
                f13073a.clear();
                String string = jSONObject.getString(str);
                if (string != null) {
                    JSONArray jSONArray = new JSONArray(string);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        try {
                            linkedList.add(m17774a(new JSONObject(jSONArray.getString(i)), vodType, list));
                        } catch (Throwable e) {
                            Log.e("VodItem:fromJsonObj", e.getMessage(), e);
                        }
                    }
                    Collections.sort(f13073a, new C08361());
                    f13073a.add(0, "All Years");
                    return linkedList;
                }
            } catch (Throwable e2) {
                Log.e("VodItem:fromJsonObj", e2.getMessage(), e2);
                return Collections.emptyList();
            }
        }
        return Collections.emptyList();
    }

    /* renamed from: a */
    public static VodItem m17774a(JSONObject r5, VodType vodtype, List<String> r7) {
        /* JADX: method processing error */
/*
Error: java.lang.NullPointerException
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.searchTryCatchDominators(ProcessTryCatchRegions.java:75)
	at jadx.core.dex.visitors.regions.ProcessTryCatchRegions.process(ProcessTryCatchRegions.java:45)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.postProcessRegions(RegionMakerVisitor.java:63)
	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:58)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1139814130.run(Unknown Source)
*/
        /*
        r0 = tv.vaders.apk.vod.model.VodType.movie;
        r0 = vodtype.equals(r0);
        if (r0 == 0) goto L_0x000e;
    L_0x0008:
        r0 = new tv.vaders.apk.vod.model.VodMovie;
        r0.<init>();
        goto L_0x005a;
    L_0x000e:
        r0 = tv.vaders.apk.vod.model.VodType.tv;
        r0 = vodtype.equals(r0);
        if (r0 == 0) goto L_0x0055;
    L_0x0016:
        r0 = new tv.vaders.apk.vod.model.VodTvShow;
        r0.<init>();
        r1 = r0;	 Catch:{ JSONException -> 0x003a }
        r1 = (tv.vaders.apk.vod.model.VodTvShow) r1;	 Catch:{ JSONException -> 0x003a }
        r2 = "tvdbid";	 Catch:{ JSONException -> 0x003a }
        r2 = r5.getInt(r2);	 Catch:{ JSONException -> 0x003a }
        r1.m21704f(r2);	 Catch:{ JSONException -> 0x003a }
        r1 = r0;	 Catch:{ JSONException -> 0x003a }
        r1 = (tv.vaders.apk.vod.model.VodTvShow) r1;	 Catch:{ JSONException -> 0x003a }
        r1 = r1.m21705t();	 Catch:{ JSONException -> 0x003a }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ JSONException -> 0x003a }
        r1 = r7.contains(r1);	 Catch:{ JSONException -> 0x003a }
        r0.m17783a(r1);	 Catch:{ JSONException -> 0x003a }
        goto L_0x005a;
    L_0x003a:
        r1 = "VodItem";
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r3 = "TVDBID not found in tv show: ";
        r2.append(r3);
        r3 = r0.m17788c();
        r2.append(r3);
        r2 = r2.toString();
        android.util.Log.e(r1, r2);
        goto L_0x005a;
    L_0x0055:
        r0 = new tv.vaders.apk.vod.model.VodTvEpisode;
        r0.<init>();
    L_0x005a:
        r1 = "title";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x01d7 }
        r0.m17780a(r1);	 Catch:{ Exception -> 0x01d7 }
        r1 = "poster";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x01d7 }
        r0.m17790c(r1);	 Catch:{ Exception -> 0x01d7 }
        r1 = "tvposter";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.has(r1);	 Catch:{ Exception -> 0x01d7 }
        if (r1 == 0) goto L_0x007d;	 Catch:{ Exception -> 0x01d7 }
    L_0x0074:
        r1 = "tvposter";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x01d7 }
        r0.m17787b(r1);	 Catch:{ Exception -> 0x01d7 }
    L_0x007d:
        r1 = "desc";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x01d7 }
        r0.m17793d(r1);	 Catch:{ Exception -> 0x01d7 }
        r1 = "year";	 Catch:{ Exception -> 0x00ad }
        r1 = r5.getInt(r1);	 Catch:{ Exception -> 0x00ad }
        r0.m17789c(r1);	 Catch:{ Exception -> 0x00ad }
        r1 = r0.m17807m();	 Catch:{ Exception -> 0x00ad }
        r1 = java.lang.String.valueOf(r1);	 Catch:{ Exception -> 0x00ad }
        r2 = f13073a;	 Catch:{ Exception -> 0x00ad }
        r1 = r2.contains(r1);	 Catch:{ Exception -> 0x00ad }
        if (r1 != 0) goto L_0x00c7;	 Catch:{ Exception -> 0x00ad }
    L_0x009f:
        r1 = f13073a;	 Catch:{ Exception -> 0x00ad }
        r2 = r0.m17807m();	 Catch:{ Exception -> 0x00ad }
        r2 = java.lang.String.valueOf(r2);	 Catch:{ Exception -> 0x00ad }
        r1.add(r2);	 Catch:{ Exception -> 0x00ad }
        goto L_0x00c7;
    L_0x00ad:
        r1 = "VodItem";	 Catch:{ Exception -> 0x01d7 }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x01d7 }
        r2.<init>();	 Catch:{ Exception -> 0x01d7 }
        r3 = "Year not found for ";	 Catch:{ Exception -> 0x01d7 }
        r2.append(r3);	 Catch:{ Exception -> 0x01d7 }
        r3 = r0.m17788c();	 Catch:{ Exception -> 0x01d7 }
        r2.append(r3);	 Catch:{ Exception -> 0x01d7 }
        r2 = r2.toString();	 Catch:{ Exception -> 0x01d7 }
        android.util.Log.e(r1, r2);	 Catch:{ Exception -> 0x01d7 }
    L_0x00c7:
        r1 = "language";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x01d7 }
        r0.m17796e(r1);	 Catch:{ Exception -> 0x01d7 }
        r1 = "genres";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.has(r1);	 Catch:{ Exception -> 0x01d7 }
        if (r1 == 0) goto L_0x00fe;	 Catch:{ Exception -> 0x01d7 }
    L_0x00d8:
        r1 = "genres";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getJSONArray(r1);	 Catch:{ Exception -> 0x01d7 }
        r2 = new java.util.LinkedList;	 Catch:{ Exception -> 0x01d7 }
        r2.<init>();	 Catch:{ Exception -> 0x01d7 }
        if (r1 == 0) goto L_0x00fa;	 Catch:{ Exception -> 0x01d7 }
    L_0x00e5:
        r3 = 0;	 Catch:{ Exception -> 0x01d7 }
    L_0x00e6:
        r4 = r1.length();	 Catch:{ Exception -> 0x01d7 }
        if (r3 >= r4) goto L_0x00fa;	 Catch:{ Exception -> 0x01d7 }
    L_0x00ec:
        r4 = r1.getInt(r3);	 Catch:{ Exception -> 0x01d7 }
        r4 = java.lang.Integer.valueOf(r4);	 Catch:{ Exception -> 0x01d7 }
        r2.add(r4);	 Catch:{ Exception -> 0x01d7 }
        r3 = r3 + 1;	 Catch:{ Exception -> 0x01d7 }
        goto L_0x00e6;	 Catch:{ Exception -> 0x01d7 }
    L_0x00fa:
        r0.m17782a(r2);	 Catch:{ Exception -> 0x01d7 }
        goto L_0x0105;	 Catch:{ Exception -> 0x01d7 }
    L_0x00fe:
        r1 = java.util.Collections.emptyList();	 Catch:{ Exception -> 0x01d7 }
        r0.m17782a(r1);	 Catch:{ Exception -> 0x01d7 }
    L_0x0105:
        r1 = "rating";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x01d7 }
        r2 = tv.vaders.apk.utils.StringUtils.m10272a(r1);	 Catch:{ Exception -> 0x01d7 }
        if (r2 != 0) goto L_0x0118;	 Catch:{ Exception -> 0x01d7 }
    L_0x0111:
        r1 = java.lang.Float.parseFloat(r1);	 Catch:{ Exception -> 0x01d7 }
        r0.m17778a(r1);	 Catch:{ Exception -> 0x01d7 }
    L_0x0118:
        r1 = "popularity";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x01d7 }
        r2 = tv.vaders.apk.utils.StringUtils.m10272a(r1);	 Catch:{ Exception -> 0x01d7 }
        if (r2 != 0) goto L_0x012b;	 Catch:{ Exception -> 0x01d7 }
    L_0x0124:
        r1 = java.lang.Float.parseFloat(r1);	 Catch:{ Exception -> 0x01d7 }
        r0.m17785b(r1);	 Catch:{ Exception -> 0x01d7 }
    L_0x012b:
        r1 = "ext";	 Catch:{ Exception -> 0x01d7 }
        r1 = r5.getString(r1);	 Catch:{ Exception -> 0x01d7 }
        r0.m17798f(r1);	 Catch:{ Exception -> 0x01d7 }
        vodtype = m17772a(r5, vodtype);	 Catch:{ Exception -> 0x01d7 }
        r0.m17781a(vodtype);	 Catch:{ Exception -> 0x01d7 }
        vodtype = "category";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.getInt(vodtype);	 Catch:{ Exception -> 0x01d7 }
        r0.m17779a(vodtype);	 Catch:{ Exception -> 0x01d7 }
        vodtype = "vodItemId";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.has(vodtype);	 Catch:{ Exception -> 0x01d7 }
        if (vodtype == 0) goto L_0x0155;	 Catch:{ Exception -> 0x01d7 }
    L_0x014c:
        vodtype = "vodItemId";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.getInt(vodtype);	 Catch:{ Exception -> 0x01d7 }
        r0.m17795e(vodtype);	 Catch:{ Exception -> 0x01d7 }
    L_0x0155:
        vodtype = "vodHash";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.has(vodtype);	 Catch:{ Exception -> 0x01d7 }
        if (vodtype == 0) goto L_0x0183;
    L_0x015d:
        vodtype = "vodHash";	 Catch:{ JSONException -> 0x0176 }
        vodtype = r5.getString(vodtype);	 Catch:{ JSONException -> 0x0176 }
        r0.m17802h(vodtype);	 Catch:{ JSONException -> 0x0176 }
        vodtype = r0 instanceof tv.vaders.apk.vod.model.VodMovie;	 Catch:{ JSONException -> 0x0176 }
        if (vodtype == 0) goto L_0x0183;	 Catch:{ JSONException -> 0x0176 }
    L_0x016a:
        vodtype = r0.m17813s();	 Catch:{ JSONException -> 0x0176 }
        vodtype = r7.contains(vodtype);	 Catch:{ JSONException -> 0x0176 }
        r0.m17783a(vodtype);	 Catch:{ JSONException -> 0x0176 }
        goto L_0x0183;
    L_0x0176:
        vodtype = move-exception;
        r7 = "VodItem";	 Catch:{ Exception -> 0x01d7 }
        r1 = r0.m17788c();	 Catch:{ Exception -> 0x01d7 }
        android.util.Log.e(r7, r1);	 Catch:{ Exception -> 0x01d7 }
        vodtype.printStackTrace();	 Catch:{ Exception -> 0x01d7 }
    L_0x0183:
        vodtype = "parentCategory";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.has(vodtype);	 Catch:{ Exception -> 0x01d7 }
        if (vodtype == 0) goto L_0x0194;	 Catch:{ Exception -> 0x01d7 }
    L_0x018b:
        vodtype = "parentCategory";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.getInt(vodtype);	 Catch:{ Exception -> 0x01d7 }
        r0.m17786b(vodtype);	 Catch:{ Exception -> 0x01d7 }
    L_0x0194:
        vodtype = "season";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.has(vodtype);	 Catch:{ Exception -> 0x01d7 }
        if (vodtype == 0) goto L_0x01b4;	 Catch:{ Exception -> 0x01d7 }
    L_0x019c:
        vodtype = r0;	 Catch:{ Exception -> 0x01d7 }
        vodtype = (tv.vaders.apk.vod.model.VodTvEpisode) vodtype;	 Catch:{ Exception -> 0x01d7 }
        r7 = "season";	 Catch:{ Exception -> 0x01d7 }
        r7 = r5.getInt(r7);	 Catch:{ Exception -> 0x01d7 }
        vodtype.m21699f(r7);	 Catch:{ Exception -> 0x01d7 }
        vodtype = r0;	 Catch:{ Exception -> 0x01d7 }
        vodtype = (tv.vaders.apk.vod.model.VodTvEpisode) vodtype;	 Catch:{ Exception -> 0x01d7 }
        r7 = "episode";	 Catch:{ Exception -> 0x01d7 }
        r7 = r5.getInt(r7);	 Catch:{ Exception -> 0x01d7 }
        vodtype.m21700g(r7);	 Catch:{ Exception -> 0x01d7 }
    L_0x01b4:
        vodtype = "showName";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.has(vodtype);	 Catch:{ Exception -> 0x01d7 }
        if (vodtype == 0) goto L_0x01c5;	 Catch:{ Exception -> 0x01d7 }
    L_0x01bc:
        vodtype = "showName";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.getString(vodtype);	 Catch:{ Exception -> 0x01d7 }
        r0.m17800g(vodtype);	 Catch:{ Exception -> 0x01d7 }
    L_0x01c5:
        vodtype = "id";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.has(vodtype);	 Catch:{ Exception -> 0x01d7 }
        if (vodtype == 0) goto L_0x01d6;	 Catch:{ Exception -> 0x01d7 }
    L_0x01cd:
        vodtype = "id";	 Catch:{ Exception -> 0x01d7 }
        vodtype = r5.getInt(vodtype);	 Catch:{ Exception -> 0x01d7 }
        r0.m17792d(vodtype);	 Catch:{ Exception -> 0x01d7 }
    L_0x01d6:
        return r0;
    L_0x01d7:
        vodtype = move-exception;
        vodtype.printStackTrace();
        tv.vaders.apk.utils.ExceptionHandler.m10250a(vodtype);
        vodtype = "VodItem:fromJson";
        r5 = r5.toString();
        android.util.Log.d(vodtype, r5);
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.vaders.apk.vod.model.VodItem.a(org.json.JSONObject, tv.vaders.apk.vod.model.VodType, java.util.List):tv.vaders.apk.vod.model.VodItem");
    }

    /* renamed from: a */
    public Object mo2044a() {
        return m17794e();
    }

    /* renamed from: a */
    public VadersApiUrl mo2045a(Context context) {
        return VadersAPI.getVodMovieStreamUrl(context, m17812r(), m17804j());
    }

    /* renamed from: a */
    public VadersApiUrl mo2046a(Context context, int i) {
        return mo2045a(context);
    }

    /* renamed from: a */
    public void m17778a(float f) {
        this.f13081i = f;
    }

    /* renamed from: a */
    public void m17779a(int i) {
        this.f13087o = i;
    }

    /* renamed from: a */
    public void m17780a(String str) {
        this.f13075c = str;
    }

    /* renamed from: a */
    public void m17781a(Date date) {
        this.f13086n = date;
    }

    /* renamed from: a */
    public void m17782a(List<Integer> list) {
        this.f13079g = list;
    }

    /* renamed from: a */
    public void m17783a(boolean z) {
        this.f13092t = z;
    }

    /* renamed from: b */
    public abstract VodType mo2753b();

    /* renamed from: b */
    public void m17785b(float f) {
        this.f13082j = f;
    }

    /* renamed from: b */
    public void m17786b(int i) {
        this.f13088p = i;
    }

    /* renamed from: b */
    public void m17787b(String str) {
        this.f13077e = str;
    }

    /* renamed from: c */
    public String m17788c() {
        return this.f13075c;
    }

    /* renamed from: c */
    public void m17789c(int i) {
        this.f13083k = i;
    }

    /* renamed from: c */
    public void m17790c(String str) {
        this.f13076d = str;
    }

    /* renamed from: d */
    public String m17791d() {
        return this.f13077e;
    }

    /* renamed from: d */
    public void m17792d(int i) {
        this.f13090r = i;
    }

    /* renamed from: d */
    public void m17793d(String str) {
        this.f13078f = str;
    }

    /* renamed from: e */
    public String m17794e() {
        return this.f13076d;
    }

    /* renamed from: e */
    public void m17795e(int i) {
        this.f13091s = i;
    }

    /* renamed from: e */
    public void m17796e(String str) {
        this.f13080h = str;
    }

    /* renamed from: f */
    public String m17797f() {
        return this.f13078f;
    }

    /* renamed from: f */
    public void m17798f(String str) {
        this.f13085m = str;
    }

    /* renamed from: g */
    public List<Integer> m17799g() {
        return this.f13079g;
    }

    /* renamed from: g */
    public void m17800g(String str) {
        this.f13089q = str;
    }

    /* renamed from: h */
    public float m17801h() {
        return this.f13081i;
    }

    /* renamed from: h */
    public void m17802h(String str) {
        this.f13084l = str;
    }

    /* renamed from: i */
    public float m17803i() {
        return this.f13082j;
    }

    /* renamed from: j */
    public String m17804j() {
        return this.f13085m;
    }

    /* renamed from: k */
    public int m17805k() {
        return this.f13087o;
    }

    /* renamed from: l */
    public int m17806l() {
        return this.f13088p;
    }

    /* renamed from: m */
    public int m17807m() {
        return this.f13083k;
    }

    /* renamed from: n */
    public String m17808n() {
        return this.f13089q;
    }

    /* renamed from: o */
    public int m17809o() {
        return this.f13090r;
    }

    /* renamed from: p */
    public boolean m17810p() {
        return this.f13092t;
    }

    /* renamed from: q */
    public String m17811q() {
        return mo2753b().equals(VodType.movie) ? m17788c() : m17808n();
    }

    /* renamed from: r */
    public int m17812r() {
        return this.f13091s;
    }

    /* renamed from: s */
    public String m17813s() {
        return this.f13084l;
    }
}
