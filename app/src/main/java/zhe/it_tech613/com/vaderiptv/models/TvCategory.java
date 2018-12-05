package zhe.it_tech613.com.vaderiptv.models;

import android.util.Log;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

public class TvCategory extends VaderModel {
    /* renamed from: a */
    private int id;
    /* renamed from: b */
    private String name;

    /* renamed from: a */
    public static List<TvCategory> m17504a(JSONObject jSONObject) {
        List<TvCategory> linkedList = new LinkedList();
        Iterator keys = jSONObject.keys();
        long currentTimeMillis = System.currentTimeMillis();
        while (keys.hasNext()) {
            try {
                String str = (String) keys.next();
                TvCategory tvCategory = new TvCategory();
                tvCategory.m17506a(Integer.parseInt(str));
                tvCategory.m17507a(jSONObject.getString(str));
                linkedList.add(tvCategory);
            } catch (Exception e) {
                e.printStackTrace();
                return Collections.emptyList();
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Time to parse ");
        stringBuilder.append(linkedList.size());
        stringBuilder.append(" TvChannels: ");
        stringBuilder.append(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - currentTimeMillis));
        stringBuilder.append(" seconds.");
        Log.d("TvCategory", stringBuilder.toString());
        return linkedList;
    }

    /* renamed from: a */
    public int m17505a() {
        return this.id;
    }

    /* renamed from: a */
    public void m17506a(int i) {
        this.id = i;
    }

    /* renamed from: a */
    public void m17507a(String str) {
        this.name = str;
    }

    /* renamed from: b */
    public String m17508b() {
        return this.name;
    }

    public String toString() {
        return m17508b();
    }
}
