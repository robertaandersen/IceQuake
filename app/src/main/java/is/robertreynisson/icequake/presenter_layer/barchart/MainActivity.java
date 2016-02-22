package is.robertreynisson.icequake.presenter_layer.barchart;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import is.robertreynisson.icequake.IceQuake;
import is.robertreynisson.icequake.R;
import is.robertreynisson.icequake.data_layer.ServiceAdapter;
import is.robertreynisson.icequake.presenter_layer.map.MapFragment;

public class MainActivity extends AppCompatActivity {


    public static ServiceAdapter serviceAdapter;
    public static TextView updateTime;
    private TabLayout tablayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        updateTime = (TextView) toolbar.findViewById(R.id.toolbar_time);
        setSupportActionBar(toolbar);
        serviceAdapter = new ServiceAdapter(IceQuake.getServerInfo());

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tablayout = (TabLayout) findViewById(R.id.tabs);
        tablayout.setupWithViewPager(viewPager);

        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Quakes");


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new BarChartFragment(), "ONE");
        adapter.addFragment(new MapFragment(), "TWO");
        viewPager.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        serviceAdapter = new ServiceAdapter(IceQuake.getServerInfo());
    }

}
