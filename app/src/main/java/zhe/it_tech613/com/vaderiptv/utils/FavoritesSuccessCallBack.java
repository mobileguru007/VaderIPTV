package zhe.it_tech613.com.vaderiptv.utils;

import zhe.it_tech613.com.vaderiptv.models.TvChannel;
import zhe.it_tech613.com.vaderiptv.models.VodItem;

public abstract class FavoritesSuccessCallBack implements Runnable {
    /* renamed from: a */
    private boolean f8039a = true;
    /* renamed from: b */
    private TvChannel f8040b = null;
    /* renamed from: c */
    private VodItem f8041c = null;

    /* renamed from: a */
    public void m10012a(TvChannel tvChannel) {
        this.f8040b = tvChannel;
    }

    /* renamed from: a */
    public void m10013a(VodItem vodItem) {
        this.f8041c = vodItem;
    }

    /* renamed from: a */
    public void m10014a(boolean z) {
        this.f8039a = z;
    }

    /* renamed from: a */
    public abstract void mo2042a(boolean z, TvChannel tvChannel, VodItem vodItem);

    public void run() {
        mo2042a(this.f8039a, this.f8040b, this.f8041c);
    }
}
