package renitto.com.copper_table_menu.Main.Presenters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.view.MenuInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import renitto.com.copper_table_menu.Main.Utilities.GlobalVariables;
import renitto.com.copper_table_menu.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    TextView mTitle;
    FloatingActionButton fab;
    RelativeLayout RL_Cart,RL_cart_badge_circle;
    TextView txtCounter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // hide statusbar of Android
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // setting toolbar header with custom font

        mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
        mTitle.setText("Offer Zone");
        Typeface tf = Typeface.createFromAsset(getAssets(),"fonts/Ailerons.otf");
        mTitle.setTypeface(tf, Typeface.BOLD);








        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // calling menu fragment

                FragmentMenus menufragments = new FragmentMenus();
                getFragmentManager().beginTransaction().replace(R.id.fragment_container, menufragments).addToBackStack("home").commit();


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // setting custom font for navigation drawer header
        View headerLayout = navigationView.inflateHeaderView(R.layout.nav_header_main);
        TextView ClientName = (TextView) headerLayout.findViewById(R.id.tv_client_name);
        TextView TV_welcome = (TextView) headerLayout.findViewById(R.id.tv_welcome);
        ClientName.setTypeface(tf, Typeface.BOLD);

        Typeface jenna = Typeface.createFromAsset(getAssets(),"fonts/JennaSue.ttf");
        TV_welcome.setTypeface(jenna, Typeface.BOLD);




        GlobalVariables globalVariables = new GlobalVariables();
        if (globalVariables.checkmenudetailback == 1) {


            // calling menu fragment on menu detail back pressed

            FragmentMenus menufragments = new FragmentMenus();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, menufragments).addToBackStack("home").commit();
        }
        else {

            // calling offer fragment initially
            FragmentOffers fragmentOffers = new FragmentOffers();
            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentOffers).addToBackStack("home").commit();

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        View v = (View) menu.findItem(R.id.action_cart).getActionView();
        txtCounter = (TextView) v.findViewById(R.id.cart_counter);

        RL_Cart=(RelativeLayout)v.findViewById(R.id.rl_cart_badge);
        RL_cart_badge_circle   =(RelativeLayout)v.findViewById(R.id.rl_cart_badge_circle);


      //cart click listener
        final MenuItem item = menu.findItem(R.id.action_cart);
        item.getActionView().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                mTitle.setText("My Orders");
                // Handle orders here
                replaceFragment(new FragmentMyorders());

            }
        });


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_cart) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menus) {

            mTitle.setText("Menus");
            // Handle menu here
            replaceFragment(new FragmentMenus());
        } else if (id == R.id.nav_offers) {

            mTitle.setText("Offer Zone");
            // Handle offer here
            replaceFragment(new FragmentOffers());

        } else if (id == R.id.nav_orders) {

            mTitle.setText("My Orders");
            // Handle orders here
            replaceFragment(new FragmentMyorders());

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    // for checking each fragmnet in back stack
    public void replaceFragment (Fragment fragment){
        String backStateName = fragment.getClass().getName();

        FragmentManager manager = getFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped){ //fragment not in back stack, create it.

            getFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).setCustomAnimations(R.anim.fragmnet_slide_left, R.anim.fragment_slide_right).addToBackStack(backStateName).commit();

        }
    }

    // fab hide

    public void fabHide()
    {
        fab.hide();
    }

    // fab show

    public void fabShow()
    {
        fab.show();
    }


    // animate cart
    public void cartAnimate()
    {
        // Do something after 5s = 5000ms
        YoYo.with(Techniques.Tada)
                .duration(1000)
                .playOn(RL_Cart);
    }

    // animate cart
    public void setToolbarTitle(String title)
    {

        mTitle.setText(title);

    }
}
