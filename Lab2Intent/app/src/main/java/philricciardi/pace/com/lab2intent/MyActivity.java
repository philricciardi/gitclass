package philricciardi.pace.com.lab2intent;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class MyActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new MapFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class MapFragment extends Fragment {


        public MapFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            Button mapButton = (Button) rootView.findViewById(R.id.map_button);
            Button webButton = (Button) rootView.findViewById(R.id.web_button);
            Button smsButton = (Button) rootView.findViewById(R.id.sms_button);
            Button telButton = (Button) rootView.findViewById(R.id.tel_button);
            Button shareButton = (Button) rootView.findViewById(R.id.share_button);


            mapButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    String geoUri = String.format("geo:38.899533, -77.036476");
                    Uri geo = Uri.parse(geoUri);
                    Intent geoMap = new Intent(Intent.ACTION_VIEW, geo);
                    startActivity(geoMap);
                    /*
                    Context context = getActivity();
                    CharSequence text = "Activity Starts Here";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    */
                }
            });

            smsButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent sms = new Intent(Intent.ACTION_SENDTO);
                    sms.setData(Uri.parse("smsto:" + Uri.encode("19144208287")));
                    sms.putExtra("sms_body", "hello");
                    startActivity(sms);
                }
            });

            webButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Uri web = Uri.parse("http://google.com");
                    Intent webBrowser = new Intent(Intent.ACTION_VIEW, web);
                    startActivity(webBrowser);
                }
            });

            telButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent dial = new Intent(Intent.ACTION_CALL);
                    dial.setData(Uri.parse("tel:19144208287"));
                    startActivity(dial);
                }
            });

            shareButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent send = new Intent(Intent.ACTION_SEND);
                    send.setType("text/plain");
                    send.putExtra(Intent.EXTRA_SUBJECT, "PHIL");
                    send.putExtra(Intent.EXTRA_TEXT, "SAY WHATS UP");
                    startActivity(send);
                }
            });

            return rootView;
        }
    }

}
