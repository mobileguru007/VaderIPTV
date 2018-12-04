package zhe.it_tech613.com.vaderiptv.async;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import org.joda.time.DateTime;

import java.util.List;
import java.util.Locale;

import zhe.it_tech613.com.vaderiptv.R;
import zhe.it_tech613.com.vaderiptv.communicator.ServiceCommunicator;
import zhe.it_tech613.com.vaderiptv.models.ChannelModel;
import zhe.it_tech613.com.vaderiptv.utils.Constant;
import zhe.it_tech613.com.vaderiptv.utils.Utils;

public class LiveStreamAsync extends AsyncTask<String[], Integer, Void> {

    private int progressIndicator;
    @SuppressLint("StaticFieldLeak")
    private Activity callingActivity;
    private List<ChannelModel> result;
    private int category_id;
    private DateTime start;
    private DateTime end;

    public LiveStreamAsync(Activity act, int category_id, DateTime start, DateTime end) {
        progressIndicator = 0;
        this.callingActivity = act;
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
            if (new Utils().isNetworkAvailable(callingActivity)) {
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
        if (result == null)
            Toast.makeText(callingActivity.getApplicationContext(), callingActivity.getString(R.string.connection_error), Toast.LENGTH_LONG).show();
        else {
            for (int i = 0; i< Constant.categoryModels.size(); i++){
                if (category_id==Constant.categoryModels.get(i).getId()) {
                    Constant.categoryModels.get(i).setChannelModels(result);
                    break;
                }
            }

        }
    }
}