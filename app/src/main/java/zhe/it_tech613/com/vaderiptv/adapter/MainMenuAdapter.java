package zhe.it_tech613.com.vaderiptv.adapter;

import android.content.res.Resources;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import zhe.it_tech613.com.vaderiptv.R;
import zhe.it_tech613.com.vaderiptv.activity.MainActivity;
import zhe.it_tech613.com.vaderiptv.models.MenuItem;

public class MainMenuAdapter extends Adapter<MainMenuAdapter.ViewHolder> {
    /* renamed from: a */
    private MainActivity mainActivity;
    /* renamed from: b */
    private ArrayList<MenuItem> menuItems;
    /* renamed from: c */
    private UserManager userManager;
    /* renamed from: d */
    private float density;
    /* renamed from: e */
    private float scaler = 1.0f;

    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder {
        /* renamed from: n */
        ImageView imageView;
        /* renamed from: o */
        CoordinatorLayout coordinatorLayout;
        /* renamed from: p */
        final /* synthetic */ MainMenuAdapter mainMenuAdapter;

        ViewHolder(MainMenuAdapter mainMenuAdapter, View view) {
            super(view);
            this.mainMenuAdapter = mainMenuAdapter;
            this.imageView = (ImageView) view.findViewById(R.id.menu_btn);
            this.coordinatorLayout = (CoordinatorLayout) view.findViewById(R.id.main_menu_container);
        }
    }

    public MainMenuAdapter(MainActivity mainMenuActivity, ArrayList<MenuItem> arrayList) {
        this.mainActivity = mainMenuActivity;
        this.menuItems = arrayList;
        this.userManager = new UserManager(mainMenuActivity);
        this.density = Resources.getSystem().getDisplayMetrics().density;
        this.scaler = Float.parseFloat(this.userManager.getStrFromPref("main_menu_scale_factor", "1"));
    }

    /* renamed from: a */
    public int getItemCount() {
        return this.menuItems.size();
    }

    /* renamed from: a */
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(this, LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_menu_item, viewGroup, false));
    }

    /* renamed from: a */
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final MenuItem menuItem = (MenuItem) this.menuItems.get(i);
        ImageView imageView = viewHolder.imageView;
//        if (VERSION.SDK_INT < 21) {
//            imageView.setBackgroundResource(R.drawable.compat_transparent_selector);
//        }
        LayoutParams layoutParams = viewHolder.coordinatorLayout.getLayoutParams();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("height: ");
        stringBuilder.append(layoutParams.height);
        stringBuilder.append(" width: ");
        stringBuilder.append(layoutParams.width);
        Log.d("MAIN", stringBuilder.toString());
        imageView.setImageDrawable(mainActivity.getResources().getDrawable(menuItem.getIcon()));
        imageView.setOnClickListener(new OnClickListener() {
            /* renamed from: b */

            public void onClick(View view) {
                if (menuItem.getFragment() != null) {
                    mainActivity.setContent(menuItem.getFragment());
                } else {
                    Toast.makeText(mainActivity, "This feature is not available yet.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
