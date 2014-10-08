package philricciardi.pace.com.week3lab2;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class RelativeLayout extends ActionBarActivity {

    private Map<String, Integer> resourceMap = new HashMap<String, Integer>();
    private String[] carArray = {"Lamborghini Aventador", "Lamborghini Gallardo",
            "Chevrolet Corvette", "Ferrari la Ferrari"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        populateResourceMap();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relative_layout);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.relative_layout, menu);
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
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_relative_layout, container, false);
            return rootView;
        }
    }

    private void populateResourceMap(){
        this.resourceMap.put("Lamborghini Aventador", R.drawable.lamborghini_aventador);
        this.resourceMap.put("Lamborghini Gallardo", R.drawable.gallardo);
        this.resourceMap.put("Chevrolet Corvette", R.drawable.corvette);
        this.resourceMap.put("Ferrari la Ferrari", R.drawable.laferrari);
    }

    public void nextImage(View view){
        ImageView image = (ImageView) findViewById(R.id.image);
        TextView tv = (TextView) findViewById(R.id.text_view);
        String carTitle = getRandomArrayValue(tv.getText().toString());
        Drawable d = getResources().getDrawable(this.resourceMap.get(carTitle));
        image.setImageDrawable(d);
        tv.setText(carTitle);
    }

    public void changeImage(View view){
        ImageView image = (ImageView) findViewById(R.id.image);
        TextView tv = (TextView) findViewById(R.id.text_view);
        String carTitle = getRandomArrayValue(tv.getText().toString());
        Drawable d = getResources().getDrawable(this.resourceMap.get(carTitle));
        image.setImageDrawable(d);
        tv.setText(carTitle);
    }

    private String getRandomArrayValue(String carTitle){
        int random = new Random().nextInt(this.carArray.length);
        while((this.carArray[random]).equals(carTitle)){
            random = new Random().nextInt(this.carArray.length);
        }
        return this.carArray[random];
    }
}
