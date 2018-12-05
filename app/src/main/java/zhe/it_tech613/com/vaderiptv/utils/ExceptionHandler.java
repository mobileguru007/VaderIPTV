package zhe.it_tech613.com.vaderiptv.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.support.v7.app.AlertDialog.Builder;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Date;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import zhe.it_tech613.com.vaderiptv.VadersAPI;
import zhe.it_tech613.com.vaderiptv.models.VadersApiUrl;

public class ExceptionHandler implements UncaughtExceptionHandler {
    /* renamed from: a */
    private Context f8453a;
    /* renamed from: b */
    private UserManager f8454b;
    /* renamed from: c */
    private OkHttpClient okHttpClient = HttpUtils.getOkHttpClient(null);

    public ExceptionHandler(Context context) {
        this.f8453a = context;
        this.f8454b = new UserManager(context);
    }

    /* renamed from: a */
    private String m10248a() {
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
        r4 = this;
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "##########################################################################\n";
        r0.append(r1);
        r1 = "Version: 2.0.21\n";
        r0.append(r1);
        r1 = "Package Name: tv.vaders.apk\n";
        r0.append(r1);
        r1 = new java.lang.StringBuilder;
        r1.<init>();
        r2 = "Android Version: ";
        r1.append(r2);
        r2 = android.os.Build.VERSION.RELEASE;
        r1.append(r2);
        r2 = "\n";
        r1.append(r2);
        r1 = r1.toString();
        r0.append(r1);
        r1 = "##########################################################################\n";
        r0.append(r1);
        r1 = java.lang.Runtime.getRuntime();	 Catch:{ IOException -> 0x006c }
        r2 = "logcat -t 5000";	 Catch:{ IOException -> 0x006c }
        r1 = r1.exec(r2);	 Catch:{ IOException -> 0x006c }
        r2 = new java.io.BufferedReader;	 Catch:{ IOException -> 0x006c }
        r3 = new java.io.InputStreamReader;	 Catch:{ IOException -> 0x006c }
        r1 = r1.getInputStream();	 Catch:{ IOException -> 0x006c }
        r3.<init>(r1);	 Catch:{ IOException -> 0x006c }
        r2.<init>(r3);	 Catch:{ IOException -> 0x006c }
    L_0x004c:
        r1 = r2.readLine();	 Catch:{ IOException -> 0x006c }
        if (r1 == 0) goto L_0x0067;	 Catch:{ IOException -> 0x006c }
    L_0x0052:
        r3 = new java.lang.StringBuilder;	 Catch:{ IOException -> 0x006c }
        r3.<init>();	 Catch:{ IOException -> 0x006c }
        r3.append(r1);	 Catch:{ IOException -> 0x006c }
        r1 = "\n";	 Catch:{ IOException -> 0x006c }
        r3.append(r1);	 Catch:{ IOException -> 0x006c }
        r1 = r3.toString();	 Catch:{ IOException -> 0x006c }
        r0.append(r1);	 Catch:{ IOException -> 0x006c }
        goto L_0x004c;
    L_0x0067:
        r0 = r0.toString();
        return r0;
    L_0x006c:
        r1 = move-exception;
        m10250a(r1);	 Catch:{ all -> 0x0078 }
        r1.printStackTrace();	 Catch:{ all -> 0x0078 }
        r0 = r0.toString();
        return r0;
    L_0x0078:
        r0 = r0.toString();
        return r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.vaders.apk.utils.ExceptionHandler.a():java.lang.String");
    }

    /* renamed from: a */
    public static void m10249a(final Activity activity, final String str, final String str2, final OnClickListener onClickListener) {
        activity.runOnUiThread(new Runnable() {

            /* renamed from: tv.vaders.apk.utils.ExceptionHandler$1$1 */
            class C07941 implements OnClickListener {
                /* renamed from: a */
                final /* synthetic */ C07951 f8448a;

                C07941(C07951 c07951) {
                    this.f8448a = c07951;
                }

                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    activity.onBackPressed();
                }
            }

            public void run() {
                Builder builder = new Builder(activity);
                builder.m1933a(str);
                builder.m1941b(str2);
                builder.m1935a(false);
                OnClickListener clickListener = onClickListener;
                if (clickListener == null) {
                    clickListener = new C07941(this);
                }
                builder.m1934a((CharSequence) "OK", clickListener);
                builder.m1943b().show();
            }
        });
    }

    /* renamed from: a */
    public static void m10250a(Exception r2) {
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
        if (r2 == 0) goto L_0x003b;
    L_0x0002:
        r0 = r2.getCause();
        if (r0 == 0) goto L_0x0010;
    L_0x0008:
        r2 = r2.getCause();
        com.crashlytics.android.Crashlytics.m12318a(r2);
        goto L_0x0040;
    L_0x0010:
        r0 = new java.io.StringWriter;	 Catch:{ Exception -> 0x0025 }
        r0.<init>();	 Catch:{ Exception -> 0x0025 }
        r1 = new java.io.PrintWriter;	 Catch:{ Exception -> 0x0025 }
        r1.<init>(r0);	 Catch:{ Exception -> 0x0025 }
        r2.printStackTrace(r1);	 Catch:{ Exception -> 0x0025 }
        r0 = r0.toString();	 Catch:{ Exception -> 0x0025 }
        com.crashlytics.android.Crashlytics.m12317a(r0);	 Catch:{ Exception -> 0x0025 }
        goto L_0x0040;
    L_0x0025:
        r0 = new java.lang.StringBuilder;
        r0.<init>();
        r1 = "Error capturing stacktrace from exception: ";
        r0.append(r1);
        r2 = r2.toString();
        r0.append(r2);
        r2 = r0.toString();
        goto L_0x003d;
    L_0x003b:
        r2 = "Exception caught is null.";
    L_0x003d:
        com.crashlytics.android.Crashlytics.m12317a(r2);
    L_0x0040:
        return;
        */
        throw new UnsupportedOperationException("Method not decompiled: tv.vaders.apk.utils.ExceptionHandler.a(java.lang.Exception):void");
    }

    /* renamed from: a */
    public void m10251a(String str, RequestBody requestBody, Callback callback) {

        this.okHttpClient.mo1786a(new Request.Builder().m9042a(str).m9049a(requestBody).m9053b()).mo1789a(callback);
    }

    /* renamed from: a */
    public void m10252a(VadersAPICallBack vadersAPICallBack, String str) {
        String a = m10248a();
        String c = this.f8454b.m10039c("username", "NONE");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str);
        stringBuilder.append("APK_");
        stringBuilder.append(c);
        stringBuilder.append("_");
        stringBuilder.append("2.0.21");
        stringBuilder.append("_");
        stringBuilder.append(new Date().getTime());
        RequestBody a2 = new FormBody.Builder().add("logfile", a).add("username", stringBuilder.toString()).build();
        VadersApiUrl a3 = VadersAPI.m9979a();
        vadersAPICallBack.m17420a(a3);
        m10251a(a3.m9977b(), a2, vadersAPICallBack);
    }

    public void uncaughtException(Thread thread, Throwable th) {
        th.printStackTrace();
        Crashlytics.m12318a(th);
        Intent intent = new Intent(this.f8453a, BugReportActivity.class);
        intent.setFlags(268435456);
        this.f8453a.startActivity(intent);
        System.exit(1);
    }
}
