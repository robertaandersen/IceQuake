package is.robertreynisson.icequake.presenter_layer;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import is.robertreynisson.icequake.IceQuake;
import is.robertreynisson.icequake.R;
import is.robertreynisson.icequake.data_layer.ServiceAdapter;
import is.robertreynisson.icequake.presenter_layer.barchart.BarChartFragment;
import is.robertreynisson.icequake.presenter_layer.map.MapFragment;

public class MainActivity extends AppCompatActivity {


    public static ServiceAdapter serviceAdapter;
    public static TextView updateTime;
    public static MainActivity mainActivity;
    private TabLayout tablayout;
    private static Fragment fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivity = this;

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        updateTime = (TextView) toolbar.findViewById(R.id.toolbar_time);
        setSupportActionBar(toolbar);

        serviceAdapter = new ServiceAdapter(IceQuake.getServerInfo());

        tablayout = (TabLayout) findViewById(R.id.tabs);
        tablayout.addTab(tablayout.newTab().setText("List"));
        tablayout.addTab(tablayout.newTab().setText("Map"));
        tablayout.setOnTabSelectedListener(tabChange());
        if (getSupportActionBar() != null) getSupportActionBar().setTitle("Quakes");
        fragment = new BarChartFragment();
        setFragment();


    }

    private TabLayout.OnTabSelectedListener tabChange() {
        return new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new BarChartFragment();
                        break;
                    case 1:
                        fragment = new MapFragment();
                        break;
                }
                setFragment();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        };
    }

    private void setFragment() {
        if (fragment != null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment, fragment.getTag())
                    .commit();
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
