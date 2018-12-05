package zhe.it_tech613.com.vaderiptv.utils;

import zhe.it_tech613.com.vaderiptv.models.TvChannel;
import zhe.it_tech613.com.vaderiptv.models.VodItem;

public abstract class FavoritesFailureCallBack implements Runnable {
    /* renamed from: a */
    private boolean f8036a;
    /* renamed from: b */
    private TvChannel f8037b;
    /* renamed from: c */
    private VodItem f8038c;

    /* renamed from: a */
    public void m10008a(TvChannel tvChannel) {
        this.f8037b = tvChannel;
    }

    /* renamed from: a */
    public void m10009a(VodItem vodItem) {
        this.f8038c = vodItem;
    }

    /* renamed from: a */
    public void m10010a(boolean z) {
        this.f8036a = z;
    }

    /* renamed from: a */
    public abstract void m10011a(boolean z, TvChannel tvChannel);

    public void run() {
        m10011a(this.f8036a, this.f8037b);
    }
}
