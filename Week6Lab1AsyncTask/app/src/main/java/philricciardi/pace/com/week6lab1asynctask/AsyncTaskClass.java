package philricciardi.pace.com.week6lab1asynctask;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by phil on 10/15/14.
 */
public class AsyncTaskClass extends AsyncTask<String, Integer, Bitmap> {

    public static final int CONNECTION_SUCCESS = 200;

    private Activity activity;

    public AsyncTaskClass(FragmentActivity activity){
        this.activity = activity;
    }

    @Override
    protected Bitmap doInBackground(String... params){
        Bitmap bitmap = null;
        publishProgress(1);
        try{
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if(con.getResponseCode() != CONNECTION_SUCCESS){
                throw new Exception("Failed to connect");
            }
            InputStream is = con.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            publishProgress(0);
            is.close();
            Log.v("doneLoadingImage", "done getting bitmap");
        }
        catch(Exception e){
            Log.e("Image", "Failed to load image", e);
        }
        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        ImageView image = (ImageView) activity.findViewById(R.id.image);
        image.setImageBitmap(bitmap);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        ProgressBar pb = (ProgressBar) activity.findViewById(R.id.progress_bar);
        if(values[0]== 1){
            pb.setVisibility(View.VISIBLE);
        }
        else{
            pb.setVisibility(View.GONE);
        }

    }
}
