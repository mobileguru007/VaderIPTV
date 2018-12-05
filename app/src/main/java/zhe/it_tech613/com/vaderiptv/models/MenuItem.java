package zhe.it_tech613.com.vaderiptv.models;

public class MenuItem {
    /* renamed from: a */
    private String name;
    /* renamed from: b */
    private int icon;
    /* renamed from: c */
    private Class fragment;
    /* renamed from: d */
    private int height;
    /* renamed from: e */
    private int width;
    /* renamed from: f */
    private int spansize;

    public MenuItem(String name, int icon, Class fragment, int height, int width, int spansize) {
        this.name = name;
        this.icon = icon;
        this.fragment = fragment;
        this.height = height;
        this.width = width;
        this.spansize = spansize;
    }

    /* renamed from: a */
    public int getIcon() {
        return this.icon;
    }

    /* renamed from: a */
    public void setFragment(Class cls) {
        this.fragment = cls;
    }

    /* renamed from: b */
    public Class getFragment() {
        return this.fragment;
    }
}
