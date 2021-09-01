package renitto.com.copper_table_menu.Main.Presenters;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AnticipateOvershootInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.CycleInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;



import com.squareup.picasso.Picasso;

import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import renitto.com.copper_table_menu.R;

/**
 * Created by Renitto on 2/4/2016.
 */
public class FragmentMenus extends Fragment {
    RecyclerView RV_menu;
    RecyclerView.LayoutManager mLayoutManager;
    String [] menu_names = {"SALAD", " SOUP ", "APPETIZER","SANDWICH","SEA FOOD SPECIAL","CONTINENTAL", "BURGER & CUTLET","PASTA","INIDIAN SPECIAL FOOD"};
//    ,"A MEAL BY ITSELF","INDIAN BREADS","RICE & NOODLES","FROM BEHIND THE GREAT WALL (Chinese)","FROM THE GOD'S OWN COUNTRY",
//            "NORTH INDIAN-Veg","NORTH INDIAN - Meat & Poultry","FROM THE CLAY OVEN","CHOICE OF ICE CREAM"};

    String[] menu_image_urls= {"http://www.pieworks.com/wp-content/uploads/2013/01/House-salad-small.jpg",
            "http://s.hswstatic.com/gif/soup-tips-1.jpg",
            "http://www.pillsbury.com/-/media/pb/images/recipes-hero/appetizers/spinach-artichoke-and-feta-bites_hero.ashx",
            "http://www.dailybreadcafe.ca/wp-content/uploads/2011/11/cheesesandwich.jpg",
            "http://d36nh2cr8jk3xy.cloudfront.net/rush49/images/lobster-cork-seafood-fest-website.jpg",
            "http://staff.titbit.com/Images/Cuisines/continental.jpg",
            "http://www.vegelicacy.com/upload/Vegetariankie%20burgery.jpg",
            "http://delightcaterer.com/continental_files/vlb_images1/1.jpg",
            "https://upload.wikimedia.org/wikipedia/commons/e/ea/Indian_Spices.jpg"
           };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.tablemenus,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // hiding fab here
        ((MainActivity)getActivity()).fabHide();


        RV_menu = (RecyclerView)view.findViewById(R.id.recycler_menu);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_menu.setLayoutManager(mLayoutManager);

        MenuCardAdapter menuCardAdapter = new MenuCardAdapter(getActivity(), menu_names);
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(menuCardAdapter);
        scaleAdapter.setInterpolator(new FastOutSlowInInterpolator());
        scaleAdapter.setDuration(1000);
        RV_menu.setAdapter(scaleAdapter);




        return view;

    }

    public class MenuCardAdapter
            extends RecyclerView.Adapter<MenuCardAdapter.ViewHolder> {

        String [] menu_name;



        public class ViewHolder extends RecyclerView.ViewHolder {




            public final View mView;


            public final TextView TV__menuname;
            public final ImageView IV_menu_image;
            public  final  View V_upper,V_lower;


            public ViewHolder(View view) {
                super(view);
                mView = view;


                TV__menuname = (TextView) view.findViewById(R.id.tv_menu_name);

                IV_menu_image=(ImageView)view.findViewById(R.id.iv_menu_image);

                V_upper = (View)view.findViewById(R.id.v_upper);
                V_lower = (View)view.findViewById(R.id.v_lower);

            }


        }


        public MenuCardAdapter(Context context, String [] menu_name ) {

            this.menu_name = menu_name;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.tablemenucard, parent, false);



            return new ViewHolder(view);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




        holder.TV__menuname.setText(menu_name[position]);

            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Rg.ttf");
            holder.TV__menuname.setTypeface(tf, Typeface.NORMAL);





            Picasso.with(getActivity()).load(menu_image_urls[position]).into(holder.IV_menu_image);

//            // applying paralaxx for images
//            parallaxViewController.imageParallax(holder.IV_menu_image);


            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, MenuDetailActivity.class);
                    intent.putExtra(MenuDetailActivity.MENU_NAME, menu_name[position]);
                    intent.putExtra(MenuDetailActivity.MENU_URL, menu_image_urls[position]);
                    context.startActivity(intent);
                    getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                }
            });







        }

        @Override
        public int getItemCount() {
            return menu_name.length;
        }






    }


}
