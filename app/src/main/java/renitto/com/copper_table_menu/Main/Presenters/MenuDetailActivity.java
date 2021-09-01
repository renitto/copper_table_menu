/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package renitto.com.copper_table_menu.Main.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.squareup.picasso.Picasso;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import renitto.com.copper_table_menu.Main.Utilities.GlobalVariables;
import renitto.com.copper_table_menu.Main.Utilities.GridSpacingItemDecoration;
import renitto.com.copper_table_menu.R;

public class MenuDetailActivity extends AppCompatActivity {

    public static final String MENU_NAME = "cheese_name";
    public static final String MENU_URL = "http://www.pieworks.com/wp-content/uploads/2013/01/House-salad-small.jpg";
    String image_url;
    FloatingActionButton FAB_menu_id;
    RelativeLayout RL_Cart,RL_cart_badge_circle;
    TextView txtCounter;
    CollapsingToolbarLayout collapsingToolbar;

    RecyclerView RV_menu_item;
    RecyclerView.LayoutManager mLayoutManager;
    String[] MenuItemNames = {"Chicken Tikka",
            "Beef Biriyani",
            "Fish Fry",
            "Chemeen Roast",
            "Casatta",
            "French Fries",
            "Porotta",
            "Chilly Chicken",
            "Mutton Vendalu",
            "Paneer Masala"

    };

    String[] MenuItemPrices = {"120",
            "90",
            "150",
            "220",
            "80",
            "40",
            "12",
            "130",
            "95",
            "85"

    };

    String[] MenuItemUrls = {"http://realfood.tesco.com/media/images/chicken-tikka-hero-be32befa-4f56-433b-ab53-c7fb34deb3c4-0-472x310.jpg",
            "http://lazizbiryani.net/pages/catalogue/cat_beef_briyani.jpg",
            "http://ksmartstatic.sify.com/cmf-1.0.0/appflow/bawarchi.com/Image/oesuvGbgdfaah_bigger.jpg",
            "http://mariasmenu.com/wp-content/uploads/prawns-olathiyathu.jpg",
            "http://img.delicious.com.au/vCV99S_f/h506-w759-cfill/del/2015/10/cassata-10318-1.jpg",
            "http://www.texaschickenmalaysia.com/menu/sides-french-fries.png",
            "http://4.bp.blogspot.com/-6ZIirjMs3Hw/TwjzScUeXeI/AAAAAAAAN7g/K8kWIIXVCrc/s400/p+main+g.jpg",
            "http://www.tastycircle.com/wp-content/uploads/2014/07/chilli-chicken.jpg",
            "http://ksmartstatic.sify.com/cmf-1.0.0/appflow/bawarchi.com/Image/oestSTaijdjif_bigger.jpg",
            "https://myfancypantry.files.wordpress.com/2013/01/paneer-tikka-masala.jpg"
    };

    GlobalVariables globalVariables = new GlobalVariables();

    @Override
    public void onCreate(Bundle savedInstanceState) {

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_detail);

        // fab selection
        FAB_menu_id = (FloatingActionButton)findViewById(R.id.fab_menu_id);


        // setting shared preference for back button purpose
        globalVariables.checkmenudetailback=1;

        Intent intent = getIntent();
        final String menuname = intent.getStringExtra(MENU_NAME);
        image_url = intent.getStringExtra(MENU_URL);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

         collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(menuname);
        final Typeface tf = Typeface.createFromAsset(this.getAssets(), "fonts/Ikaros.ttf");
        collapsingToolbar.setCollapsedTitleTypeface(tf);
        collapsingToolbar.setExpandedTitleTypeface(tf);

        // setting  menu image here
        loadBackdrop();

        //for the gridlayout arrange ment
        int spanCount = 2;
        int spacing= 23 ;



        // setting Gridlayout for the menu items

        RV_menu_item = (RecyclerView)findViewById(R.id.recycler_menu_items);


        mLayoutManager = new GridLayoutManager(getApplicationContext(), spanCount);

        boolean includeEdge = true;
        RV_menu_item.addItemDecoration(new GridSpacingItemDecoration(spanCount, spacing, includeEdge));
        RV_menu_item.setLayoutManager(mLayoutManager);




        MenuItemsCardAdapter menuItemsCardAdapter = new MenuItemsCardAdapter(getApplicationContext(),MenuItemNames);

        // animating the recycler views
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(menuItemsCardAdapter);
        scaleAdapter.setInterpolator(new BounceInterpolator());
        scaleAdapter.setDuration(1000);
        RV_menu_item.setAdapter(scaleAdapter);


        // hide fab on scroll
        RV_menu_item.addOnScrollListener(new RecyclerView.OnScrollListener(){


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 1)
                    FAB_menu_id.hide();
                else
                    FAB_menu_id.show();

            }
        });


    }


    public class MenuItemsCardAdapter
            extends RecyclerView.Adapter<MenuItemsCardAdapter.ViewHolder> {

        String [] menu_item_names;



        public class ViewHolder extends RecyclerView.ViewHolder {




            public final View mView;


            public final TextView TV__menu_item_name;
            public final TextView TV__menu_item_price;
            public final ImageView IV_menu_item_image;


            public ViewHolder(View view) {
                super(view);
                mView = view;


                TV__menu_item_name = (TextView) view.findViewById(R.id.tv_item_name);

                TV__menu_item_price = (TextView) view.findViewById(R.id.tv_item_price);

                IV_menu_item_image=(ImageView)view.findViewById(R.id.iv_item_image);


            }


        }


        public MenuItemsCardAdapter(Context context, String [] menu_item_names) {

            this.menu_item_names = menu_item_names;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.menu_item_card, parent, false);



            return new ViewHolder(view);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




            holder.TV__menu_item_name.setText(MenuItemNames[position]);
            holder.TV__menu_item_price.setText("₹ "+MenuItemPrices[position]);
            Picasso.with(getApplicationContext()).load(MenuItemUrls[position]).into(holder.IV_menu_item_image);




//
//
//            Picasso.with(getActivity()).load(menu_image_urls[position]).into(holder.IV_menu_image);
//
////            // applying paralaxx for images
////            parallaxViewController.imageParallax(holder.IV_menu_image);
//
//
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentItemSelection dialog = new FragmentItemSelection();
                    dialog.setRetainInstance(true);
                    dialog.show(getFragmentManager(), "CateringOfferDialogFragment");



                }
            });
//
//





        }

        @Override
        public int getItemCount() {
            return menu_item_names.length;
        }






    }

    private void loadBackdrop() {
        final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
        Picasso.with(this).load(image_url).into(imageView);

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


             //// TODO: 2/16/2016  cart click listner here


                FAB_menu_id.hide();

                collapsingToolbar.setTitle("My Orders");


                // calling menu fragment on menu detail back pressed

                FragmentMyorders fragmentMyorders = new FragmentMyorders();
                getFragmentManager().beginTransaction().replace(R.id.frag_container, fragmentMyorders).addToBackStack("myorders").commit();
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_cart:



                return true;

            case android.R.id.home:
                    onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // animate cart
    public void cartAnimate()
    {
        // Do something after 5s = 5000ms
        YoYo.with(Techniques.Tada)
                .duration(1000)
                .playOn(this.RL_Cart);
    }

}
