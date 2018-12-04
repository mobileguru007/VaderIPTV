package zhe.it_tech613.com.vaderiptv.activity;

import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.glomadrian.materialanimatedswitch.MaterialAnimatedSwitch;

import zhe.it_tech613.com.vaderiptv.R;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener{

    private MaterialAnimatedSwitch usable_switch,start_on_boot_switch,
            highest_quality_audio_switch,enable_internal_audio_switch,
            show_debug_overlay_vod_switch,show_debug_overlay_switch,
            auto_start_last_channel_switch,use_mpeg_switch,sort_channels_by_name_switch,
            enable_new_style_switch,stop_playback_switch,end_match_switch;
    private RelativeLayout lay_account,lay_version,lay_logout,lay_enabled_categories,
            lay_usable_sources,lay_end_match,lay_send_log,lay_server_setting,
            lay_sort_channel_by_name,lay_size,lay_stop_playback, lay_main_meny_icon_scale,
            lay_default_player,lay_enable_new_style,lay_use_mpeg,lay_auto_start_last_channel,
            lay_show_debug_overlay,lay_default_player_vod,lay_show_debug_overlay_vod,lay_abr_preference,
            lay_enable_internal_audio,lay_highest_quality_audio,lay_start_on_boot;
    private RelativeLayout[] itemList;
    private TextView username,expiry,version;
    private int selected_item=-1;
    private ScrollView scrollView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_settings);
        initWidget();
        writeContent();
    }

    private void initWidget() {
        lay_account=(RelativeLayout) findViewById(R.id.lay_account);
        lay_account.setOnClickListener(this);
        lay_version=(RelativeLayout) findViewById(R.id.lay_version);
        lay_version.setOnClickListener(this);
        lay_logout=(RelativeLayout) findViewById(R.id.lay_logout);
        lay_logout.setOnClickListener(this);
        lay_enabled_categories=(RelativeLayout) findViewById(R.id.lay_enabled_categories);
        lay_enabled_categories.setOnClickListener(this);
        lay_usable_sources=(RelativeLayout) findViewById(R.id.lay_usable_sources);
        lay_usable_sources.setOnClickListener(this);
        lay_end_match=(RelativeLayout) findViewById(R.id.lay_end_match);
        lay_end_match.setOnClickListener(this);
        lay_send_log=(RelativeLayout) findViewById(R.id.lay_send_log);
        lay_send_log.setOnClickListener(this);
        lay_server_setting=(RelativeLayout) findViewById(R.id.lay_server_setting);
        lay_server_setting.setOnClickListener(this);
        lay_sort_channel_by_name=(RelativeLayout) findViewById(R.id.lay_sort_channel_by_name);
        lay_sort_channel_by_name.setOnClickListener(this);
        lay_size=(RelativeLayout) findViewById(R.id.lay_size);
        lay_size.setOnClickListener(this);
        lay_stop_playback=(RelativeLayout) findViewById(R.id.lay_stop_playback);
        lay_stop_playback.setOnClickListener(this);
        lay_main_meny_icon_scale =(RelativeLayout) findViewById(R.id.lay_main_menu_icon_scale);
        lay_main_meny_icon_scale.setOnClickListener(this);
        lay_default_player=(RelativeLayout) findViewById(R.id.lay_default_player);
        lay_default_player.setOnClickListener(this);
        lay_enable_new_style=(RelativeLayout) findViewById(R.id.lay_enable_new_style);
        lay_enable_new_style.setOnClickListener(this);
        lay_use_mpeg=(RelativeLayout) findViewById(R.id.lay_use_mpeg);
        lay_use_mpeg.setOnClickListener(this);
        lay_auto_start_last_channel=(RelativeLayout) findViewById(R.id.lay_auto_start_last_channel);
        lay_auto_start_last_channel.setOnClickListener(this);
        lay_show_debug_overlay=(RelativeLayout) findViewById(R.id.lay_show_debug_overlay);
        lay_show_debug_overlay.setOnClickListener(this);
        lay_default_player_vod=(RelativeLayout) findViewById(R.id.lay_default_player_vod);
        lay_default_player_vod.setOnClickListener(this);
        lay_show_debug_overlay_vod=(RelativeLayout) findViewById(R.id.lay_show_debug_overlay_vod);
        lay_show_debug_overlay_vod.setOnClickListener(this);
        lay_abr_preference=(RelativeLayout) findViewById(R.id.lay_abr_preference);
        lay_abr_preference.setOnClickListener(this);
        lay_enable_internal_audio=(RelativeLayout) findViewById(R.id.lay_enable_internal_audio);
        lay_enable_internal_audio.setOnClickListener(this);
        lay_highest_quality_audio=(RelativeLayout) findViewById(R.id.lay_highest_quality_audio);
        lay_highest_quality_audio.setOnClickListener(this);
        lay_start_on_boot=(RelativeLayout) findViewById(R.id.lay_start_on_boot);
        lay_start_on_boot.setOnClickListener(this);

        itemList=new RelativeLayout[]{lay_account,lay_version,lay_logout,lay_enabled_categories,lay_usable_sources,
                lay_end_match,lay_send_log,lay_server_setting,lay_sort_channel_by_name,
                lay_size,lay_stop_playback,lay_main_meny_icon_scale,
                lay_default_player,lay_enable_new_style,lay_use_mpeg,lay_auto_start_last_channel,
                lay_show_debug_overlay,lay_default_player_vod,
                lay_show_debug_overlay_vod,lay_abr_preference,lay_enable_internal_audio,
                lay_highest_quality_audio,lay_start_on_boot};
        username=(TextView)findViewById(R.id.username);
        expiry=(TextView)findViewById(R.id.expiry);
        version=(TextView)findViewById(R.id.version);

        usable_switch=(MaterialAnimatedSwitch)findViewById(R.id.usable_switch);
        end_match_switch=(MaterialAnimatedSwitch)findViewById(R.id.end_match_switch);
        stop_playback_switch=(MaterialAnimatedSwitch)findViewById(R.id.stop_playback_switch);
        enable_new_style_switch=(MaterialAnimatedSwitch)findViewById(R.id.enable_new_style_switch);
        use_mpeg_switch=(MaterialAnimatedSwitch)findViewById(R.id.use_mpeg_switch);
        auto_start_last_channel_switch=(MaterialAnimatedSwitch)findViewById(R.id.auto_start_last_channel_switch);
        show_debug_overlay_switch=(MaterialAnimatedSwitch)findViewById(R.id.show_debug_overlay_switch);
        show_debug_overlay_vod_switch=(MaterialAnimatedSwitch)findViewById(R.id.show_debug_overlay_vod_switch);
        enable_internal_audio_switch=(MaterialAnimatedSwitch)findViewById(R.id.enable_internal_audio_switch);
        highest_quality_audio_switch=(MaterialAnimatedSwitch)findViewById(R.id.highest_quality_audio_switch);
        start_on_boot_switch=(MaterialAnimatedSwitch)findViewById(R.id.start_on_boot_switch);
        sort_channels_by_name_switch=(MaterialAnimatedSwitch)findViewById(R.id.sort_channels_by_name_switch);
        scrollView=(ScrollView)findViewById(R.id.scrollView);
        scrollView.setFocusable(false);
    }

    private void writeContent() {

    }

    public void focusAnim(int selected_item) {
        for (int i=0;i<itemList.length;i++){
            View view=itemList[i];
            if (selected_item==i) view.setBackgroundColor(getResources().getColor(R.color.ripple_material_dark));
            else view.setBackgroundColor(getResources().getColor(R.color.theme_background));
        }
        View view=itemList[selected_item];
        Rect scrollBounds = new Rect();
        scrollView.getHitRect(scrollBounds);
        if (!view.getLocalVisibleRect(scrollBounds)) {
            // Any portion of the imageView, even a single pixel, is within the visible window
            scrollView.scrollTo(0, (int) view.getY());
        }
        //        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.focus_anim);
        // Use bounce interpolator with amplitude 0.2 and frequency 20
        //        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        //        myAnim.setInterpolator(interpolator);
        //        view.startAnimation(myAnim);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        View view = getCurrentFocus();
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    onBackPressed();
                    break;
                case KeyEvent.KEYCODE_DPAD_UP:
                    if (selected_item==-1) {
                        selected_item=22;
                        itemList[selected_item].requestFocus();
                        focusAnim(selected_item);
                    }
                    else if (selected_item>0){
                        selected_item--;
                        itemList[selected_item].requestFocus();
                        focusAnim(selected_item);
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_DOWN:
                    if (selected_item==-1) {
                        selected_item=0;
                        itemList[selected_item].requestFocus();
                        focusAnim(selected_item);
                    }
                    else if (selected_item<22){
                        selected_item++;
                        itemList[selected_item].requestFocus();
                        focusAnim(selected_item);
                    }
                    break;
                case KeyEvent.KEYCODE_DPAD_CENTER:
                    itemList[selected_item].performClick();
                    break;}
        }
        return super.dispatchKeyEvent(event);
    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.lay_account:
                break;
            case R.id.lay_version:
                break;
            case R.id.lay_logout:
                break;
            case R.id.lay_enabled_categories:
                break;
            case R.id.lay_usable_sources:
                usable_switch.toggle();
                break;
            case R.id.lay_end_match:
                end_match_switch.toggle();
                break;
            case R.id.lay_send_log:
                break;
            case R.id.lay_server_setting:
                break;
            case R.id.lay_sort_channel_by_name:
                sort_channels_by_name_switch.toggle();
                break;
            case R.id.lay_size:
                break;
            case R.id.lay_stop_playback:
                stop_playback_switch.toggle();
                break;
            case R.id.lay_main_menu_icon_scale:
                break;
            case R.id.lay_default_player:
                break;
            case R.id.lay_enable_new_style:
                enable_new_style_switch.toggle();
                break;
            case R.id.lay_use_mpeg:
                use_mpeg_switch.toggle();
                break;
            case R.id.lay_auto_start_last_channel:
                auto_start_last_channel_switch.toggle();
                break;
            case R.id.lay_show_debug_overlay:
                show_debug_overlay_switch.toggle();
                break;
            case R.id.lay_default_player_vod:
                break;
            case R.id.lay_show_debug_overlay_vod:
                show_debug_overlay_vod_switch.toggle();
                break;
            case R.id.lay_abr_preference:
                break;
            case R.id.lay_enable_internal_audio:
                enable_internal_audio_switch.toggle();
                break;
            case R.id.lay_highest_quality_audio:
                highest_quality_audio_switch.toggle();
                break;
            case R.id.lay_start_on_boot:
                start_on_boot_switch.toggle();
                break;
        }
        Toast.makeText(this,"item clicked",Toast.LENGTH_LONG).show();
    }
}
