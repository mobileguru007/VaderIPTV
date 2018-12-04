package zhe.it_tech613.com.vaderiptv.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ScrollView;

import zhe.it_tech613.com.vaderiptv.R;
import zhe.it_tech613.com.vaderiptv.activity.MainActivity;
import zhe.it_tech613.com.vaderiptv.activity.SettingsActivity;
import zhe.it_tech613.com.vaderiptv.utils.Constant;
import zhe.it_tech613.com.vaderiptv.utils.MyBounceInterpolator;

public class MenuFragment extends Fragment {

    ImageView[] imageViews;
    int selected_item=-1;
    ScrollView scrollView;
    public static MenuFragment newInstance() {
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_menu, container, false);
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        scrollView.setFocusable(false);
        ImageView epg = (ImageView)findViewById(R.id.epg);
        epg.setFocusable(true);
        ImageView liveTv = (ImageView)findViewById(R.id.liveTv);
        liveTv.setFocusable(true);
        ImageView catchup = (ImageView)findViewById(R.id.catchup);
        catchup.setFocusable(true);
        ImageView match_center = (ImageView)findViewById(R.id.match_center);
        match_center.setFocusable(true);
        ImageView vod = (ImageView)findViewById(R.id.vod);
        vod.setFocusable(true);
        ImageView setting = (ImageView)findViewById(R.id.setting);
        setting.setFocusable(true);
        imageViews= new ImageView[]{liveTv, epg, catchup, match_center, vod, setting};
        epg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                didTapButton(epg);
                Intent intent=new Intent(MainActivity.this,EpgFragment.class);
                startActivity(intent);
            }
        });
        liveTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Constant.fetch_finished){
                    didTapButton(liveTv);
                    Intent intent=new Intent(MainActivity.this,LiveTVFragment.class);
                    startActivity(intent);
                }
            }
        });
        catchup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                didTapButton(catchup);
                Intent intent=new Intent(MainActivity.this,CatchUpFragment.class);
                startActivity(intent);
            }
        });
        match_center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                didTapButton(match_center);
                Intent intent=new Intent(MainActivity.this,MatchCenterFragment.class);
                startActivity(intent);
            }
        });
        vod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                didTapButton(vod);
                Intent intent=new Intent(MainActivity.this,VodFragment.class);
                startActivity(intent);
            }
        });
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                didTapButton(setting);
                Intent intent=new Intent(MainActivity.this,SettingsActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    public void didTapButton(View view) {
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);
        view.startAnimation(myAnim);
    }

    public void focusAnim(View view) {
        view.getLayoutParams().height += 20;
        view.getLayoutParams().width += 20;
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.focus_anim);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
//        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
//        myAnim.setInterpolator(interpolator);
        view.startAnimation(myAnim);
        //Scroll scrollview
        Rect scrollBounds = new Rect();
        scrollView.getHitRect(scrollBounds);
        if (!view.getLocalVisibleRect(scrollBounds)) {
            // Any portion of the imageView, even a single pixel, is within the visible window
            scrollView.scrollTo(0, (int) view.getY());
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        View view = getCurrentFocus();
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    if (selected_item==-1 || selected_item==0) {
                        selected_item=5;
                        imageViews[selected_item].requestFocus();
                        focusAnim(imageViews[selected_item]);
                    }
                    else if (selected_item>0){
                        selected_item--;
                        imageViews[selected_item].requestFocus();
                        focusAnim(imageViews[selected_item]);
                    }
                    Log.e("main","upclicked"+selected_item);
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    if (selected_item==-1 || selected_item==5) {
                        selected_item=0;
                        imageViews[selected_item].requestFocus();
                        focusAnim(imageViews[selected_item]);
                    }
                    else if (selected_item<5){
                        selected_item++;
                        imageViews[selected_item].requestFocus();
                        focusAnim(imageViews[selected_item]);
                    }
                    Log.e("main","downclicked"+selected_item);
                    break;
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    imageViews[selected_item].performClick();
                    Log.e("main","centerclicked"+selected_item);
                    break;
            }
        }
        return super.dispatchKeyEvent(event);
    }
}
