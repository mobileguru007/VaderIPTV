package zhe.it_tech613.com.vaderiptv.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.List;

import zhe.it_tech613.com.vaderiptv.R;
import zhe.it_tech613.com.vaderiptv.adapter.LiveTVCategoryAdapter;
import zhe.it_tech613.com.vaderiptv.adapter.LiveTVChannelAdapter;
import zhe.it_tech613.com.vaderiptv.communicator.ServiceCommunicator;
import zhe.it_tech613.com.vaderiptv.models.CategoryModel;
import zhe.it_tech613.com.vaderiptv.models.ChannelModel;
import zhe.it_tech613.com.vaderiptv.utils.Constant;
import zhe.it_tech613.com.vaderiptv.utils.Utils;

public class LiveTvFragment extends Fragment {

    LiveTVCategoryAdapter liveTVCategoryAdapter;
    LiveTVChannelAdapter liveTVChannelAdapter;
    RecyclerView category_list,channel_list;
    ArrayList<CategoryModel> categoryModels;
    ArrayList<ChannelModel> channelRealmModels=new ArrayList<>();
    int height;
    TextView largeText,large_channel_no;
    ImageView channel_icon, arrow;
    private boolean loading = true;
    LinearLayout lay_0, lay_1;
    RelativeLayout lay_2;
    ProgressBar progress_bar;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    private enum Focus_state {
        CATEGORY_LIST("Category_list", 0),
        CHANNEL_LIST("Channel_list", 1),
        PROGRAM("Program",2);
        private String stringValue;
        private int intValue;
        private Focus_state(String toString, int value) {
            stringValue = toString;
            intValue = value;
        }

        @Override
        public String toString() {
            return stringValue;
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_live_tv_old, container, false);
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
//        FullScreencall();
        category_list=(RecyclerView) view.findViewById(R.id.category_list);
        channel_list=(RecyclerView) view.findViewById(R.id.channel_list);
        categoryModels= Constant.categoryModels;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        liveTVCategoryAdapter=new LiveTVCategoryAdapter(getActivity(), categoryModels,convertDpToPixel(200,getActivity()), height/12,
                new LiveTVCategoryAdapter.AdapterInterface() {
            @Override
            public void buttonPressed(int id) {

            }
        });
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        category_list.setLayoutManager(mLayoutManager);
        category_list.setAdapter(liveTVCategoryAdapter);
        largeText=(TextView)view.findViewById(R.id.large_text);
        lay_0 =(LinearLayout)view.findViewById(R.id.lay_0);
        lay_1 =(LinearLayout)view.findViewById(R.id.lay_1);
        lay_2 =(RelativeLayout)view.findViewById(R.id.lay_2);
        arrow=(ImageView)view.findViewById(R.id.arrow);
        channel_icon=(ImageView)view.findViewById(R.id.channel_icon);
        large_channel_no=(TextView)view.findViewById(R.id.large_channel);
        progress_bar=(ProgressBar)view.findViewById(R.id.progress_bar);
        RelativeLayout.LayoutParams rlp= new RelativeLayout.LayoutParams(convertDpToPixel(200,getActivity()), (int) (height*1.2/12));
        rlp.setMargins(0,height*11/12,0,0);
        lay_0.setLayoutParams(rlp);
        category_list.getLayoutManager().scrollToPosition(Integer.MAX_VALUE / 2);
        category_list.addOnScrollListener(new RecyclerView.OnScrollListener() {

            // for this tutorial, this is the ONLY method that we need, ignore the rest
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    // Recycle view scrolling downwards...
                    // this if statement detects when user reaches the end of recyclerView, this is only time we should load more
                    if (!recyclerView.canScrollVertically(RecyclerView.FOCUS_DOWN)) {
                        // remember "!" is the same as "== false"
                        // here we are now allowed to load more, but we need to be careful
                        // we must check if itShouldLoadMore variable is true [unlocked]
                        categoryModels.addAll(Constant.categoryModels);
                        liveTVCategoryAdapter.notifyDataSetChanged();
                    }

                }
            }
        });
        category_list.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                updateCategoryLargeText(mLayoutManager,1);
                return false;
            }
        });
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateCategoryLargeText(mLayoutManager,1);
            }
        },100);
        return view;
    }

    Focus_state focus_state=Focus_state.CATEGORY_LIST;

    public void FullScreencall() {
        if(Build.VERSION.SDK_INT < 19) { // lower api
            View v = getActivity().getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else  {
            //for new api versions.
            View decorView = getActivity().getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    private void updateCategoryLargeText(LinearLayoutManager mLayoutManager, int i){
        int lastVisibleItemPosition = mLayoutManager.findLastVisibleItemPosition();
        CategoryModel categoryModel=categoryModels.get(lastVisibleItemPosition-i);
        Log.e("firstposition", String.valueOf(lastVisibleItemPosition));
        largeText.setText(categoryModel.getName());
        int category_id=categoryModel.getId();
        for (int j=0;j<Constant.channelModels.size();j++){
            ChannelModel channelModel=Constant.channelModels.get(j);
            if (channelModel.getCategory_id()==category_id) channelRealmModels.add(channelModel);
        }
        int size=channelRealmModels.size();
        liveTVChannelAdapter=new LiveTVChannelAdapter(getActivity(), channelRealmModels,convertDpToPixel(200,getContext()),height/12,size,new LiveTVChannelAdapter.AdapterInterface(){
            @Override
            public void buttonPressed(long id) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        channel_list.setLayoutManager(linearLayoutManager);
        channel_list.setAdapter(liveTVChannelAdapter);
        RelativeLayout.LayoutParams rlp= new RelativeLayout.LayoutParams(convertDpToPixel(200,getActivity()), (int) (height*1.2/12));
        rlp.setMargins(convertDpToPixel(200,getContext()),height*11/12,0,0);
        lay_1.setLayoutParams(rlp);
        DateTime start=DateTime.now(DateTimeZone.UTC).minusHours(6);
        DateTime end=DateTime.now(DateTimeZone.UTC).plusHours(6);
        new LiveStreamAsync(category_id,start,end).execute();
    }

    @SuppressLint("CheckResult")
    private void updateChannelLargeText(LinearLayoutManager layoutManager, int i){
        ChannelModel channelModel=new ChannelModel();
        large_channel_no.setText("");
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.drawable.placeholder);
        requestOptions.error(R.drawable.television);
        Glide.with(this)
                .load(channelModel.getStream_icon())
                .apply(requestOptions)
                .into(channel_icon);
    }
    public static int convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        int px = (int) (dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        return px;
    }

    public static int convertPixelsToDp(float px, Context context){
        return (int) (px / ((float) context.getResources().getDisplayMetrics().densityDpi / DisplayMetrics.DENSITY_DEFAULT));
    }

//    @Override
//    public boolean dispatchKeyEvent(KeyEvent event) {
//        View view = getCurrentFocus();
//        if (event.getAction() == KeyEvent.ACTION_DOWN) {
//            LinearLayoutManager layoutManager = ((LinearLayoutManager)category_list.getLayoutManager());
//            int lastCompletelyVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
//            switch (event.getKeyCode()) {
//                case KeyEvent.KEYCODE_BACK:
//                    onBackPressed();
//                    break;
//                case KeyEvent.KEYCODE_DPAD_UP:
//                    int firstCompletelyVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//                    if (firstCompletelyVisibleItemPosition>0){
//                        updateCategoryLargeText(layoutManager,1);
//                        category_list.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                category_list.scrollToPosition(firstCompletelyVisibleItemPosition-2);
//                            }
//                        }, 5);
//                    }
//                    Log.e("livetv","upclicked");
//
//                    break;
//                case KeyEvent.KEYCODE_DPAD_DOWN:
//                    if (lastCompletelyVisibleItemPosition<categoryModels.size()-1){
//                        updateCategoryLargeText(layoutManager,0);
//                        category_list.postDelayed(new Runnable() {
//                            @Override
//                            public void run() {
//                                category_list.scrollToPosition(lastCompletelyVisibleItemPosition+1);
//                            }
//                        }, 5);
//                    }
//                    Log.e("livetv","downclicked");
//                    break;
//                case KeyEvent.KEYCODE_DPAD_CENTER:
//                    if (focus_state==Focus_state.CATEGORY_LIST){
//                        channel_list.requestFocus();
//                        focus_state=Focus_state.CHANNEL_LIST;
//                    }else {
//                        //TODO
//                        //play program(now channel)
//                    }
//                    break;
//                case KeyEvent.KEYCODE_ALT_RIGHT:
//                    channel_list.requestFocus();
//                    focus_state=Focus_state.CHANNEL_LIST;
//                    RelativeLayout.LayoutParams rlp= new RelativeLayout.LayoutParams(convertDpToPixel(200,this), (int) (height*1.2/12));
//                    rlp.setMargins(0,height*11/12,0,0);
//                    lay_0.setLayoutParams(rlp);
//                    break;
//                case KeyEvent.KEYCODE_ALT_LEFT:
//                    category_list.requestFocus();
//                    focus_state=Focus_state.CATEGORY_LIST;
//                    break;
//            }
//            category_list.setAdapter(liveTVCategoryAdapter);
//        }
//        return super.dispatchKeyEvent(event);
//    }

    public class LiveStreamAsync extends AsyncTask<String[], Integer, Void> {

        private int progressIndicator;
        private List<ChannelModel> result;
        private int category_id;
        private DateTime start;
        private DateTime end;

        public LiveStreamAsync(int category_id, DateTime start, DateTime end) {
            progressIndicator = 0;
            this.category_id = category_id;
            this.start = start;
            this.end = end;
        }

        /**
         * On pre execute.
         *
         * @see AsyncTask#onPreExecute()
         */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        /**
         * On progress update.
         *
         * @param values the values
         */
        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //  progressDialog.setProgress(progressIndicator);
        }

        /**
         * Do in background.
         *
         * @param params
         * @return
         * @see AsyncTask#doInBackground(Object[])
         */
        @Override
        protected Void doInBackground(String[]... params) {
            Thread getIncedintsThread = new Thread() {

                public void run() {
                    try {
                        result = new ServiceCommunicator().liveStream(category_id, start, end);

                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }
            };
            try {
                if (new Utils().isNetworkAvailable(getActivity())) {
                    getIncedintsThread.start();
                    /*
                     * While the getIncedintsThread thread is running , keep updating
                     * the UI with showing the dialog.
                     */
                    while (getIncedintsThread.isAlive()) {
                        progressIndicator++;
                        publishProgress(progressIndicator);
                    }
                } else {
                    this.result = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result1) {
            super.onPostExecute(result1);
            // progressDialog.dismiss();
            if (result == null) {
                Toast.makeText(getActivity().getApplicationContext(), getString(R.string.connection_error), Toast.LENGTH_LONG).show();
            } else {
                for (int i=0;i<Constant.categoryModels.size();i++){
                    if (category_id==Constant.categoryModels.get(i).getId()) {
                        Constant.categoryModels.get(i).setChannelModels(result);
                        break;
                    }
                }

            }
        }
    }
}
