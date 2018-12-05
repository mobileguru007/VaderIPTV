package zhe.it_tech613.com.vaderiptv.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class Bouquet {
    /* renamed from: a */
    private int f8228a;
    /* renamed from: b */
    private String f8229b;

    /* renamed from: tv.vaders.apk.livetv.model.Bouquet$1 */
    static class Compare implements Comparator<Bouquet> {
        Compare() {
        }

        /* renamed from: a */
        public int m10114a(Bouquet bouquet, Bouquet bouquet2) {
            return bouquet.m10120b().compareTo(bouquet2.m10120b());
        }
        @Override
        public int compare(Bouquet bouquet, Bouquet t1) {
            return m10114a((Bouquet) bouquet, (Bouquet) t1);
        }
    }

    /* renamed from: a */
    public static List<Bouquet> getBouquetsFromJsonArray(JSONArray jSONArray) {
        List<Bouquet> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(getFromJson(jSONArray.getJSONObject(i)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Collections.sort(arrayList, new Compare());
        return arrayList;
    }

    /* renamed from: a */
    public static Bouquet getFromJson(JSONObject jSONObject) {
        Bouquet bouquet = new Bouquet();
        try {
            bouquet.m10118a(jSONObject.getInt("id"));
            bouquet.m10119a(jSONObject.getString("name"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bouquet;
    }

    /* renamed from: a */
    public int m10117a() {
        return this.f8228a;
    }

    /* renamed from: a */
    public void m10118a(int i) {
        this.f8228a = i;
    }

    /* renamed from: a */
    public void m10119a(String str) {
        this.f8229b = str;
    }

    /* renamed from: b */
    public String m10120b() {
        return this.f8229b;
    }
}