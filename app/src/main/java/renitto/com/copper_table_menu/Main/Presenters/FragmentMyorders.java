package renitto.com.copper_table_menu.Main.Presenters;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import renitto.com.copper_table_menu.Main.Utilities.CustomLinearLayoutManager;
import renitto.com.copper_table_menu.R;

/**
 * Created by Renitto on 2/15/2016.
 */
public class FragmentMyorders extends Fragment {

    RecyclerView RV_myorders;
    RecyclerView.LayoutManager mLayoutManager;


    Drawable drawable;

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

    String [] Pref_name = {"Spinach","Mushroom","Veg","Chicken","Tomato"};

    String [] Qty = {"1","5","10","7","3"};

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // for starting animation for button


        View view = inflater.inflate(R.layout.myorders,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

//       // hiding  fab.
//        ((MainActivity) getActivity()).fabHide();



        RV_myorders = (RecyclerView)view.findViewById(R.id.recycler_myorders);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_myorders.setLayoutManager(mLayoutManager);

        MyorderAdapter myorderAdapter = new MyorderAdapter(getActivity(), MenuItemNames);
//        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(searchresultadapter);
//        scaleAdapter.setInterpolator(new BounceInterpolator());
//        scaleAdapter.setDuration(1000);
        RV_myorders.setAdapter(myorderAdapter);




        return view;

    }

    public class MyorderAdapter
            extends RecyclerView.Adapter<MyorderAdapter.ViewHolder> {

        String [] menu_item_name;
        OrderItemPreferenceAdapter orderItemPreferenceAdapter;


        public class ViewHolder extends RecyclerView.ViewHolder {




            public final View mView;


            public final TextView TV_order_item_item_name,TV_remove_btn,TV_edit_btn;
            public final ImageView IV_item_image;
            public final RecyclerView RV_myorder_prefrence;



            public ViewHolder(View view) {
                super(view);
                mView = view;


                TV_order_item_item_name = (TextView) view.findViewById(R.id.tv_order_item_item_name);

                TV_remove_btn = (TextView) view.findViewById(R.id.tv_remove_btn);

                TV_edit_btn = (TextView) view.findViewById(R.id.tv_edit_btn);

                IV_item_image=(ImageView)view.findViewById(R.id.iv_item_image);

                RV_myorder_prefrence = (RecyclerView)view.findViewById(R.id.recycler_item_preference);


            }


        }


        public MyorderAdapter(Context context, String [] menu_item_name) {

            this.menu_item_name = menu_item_name;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.order_item_card, parent, false);



            return new ViewHolder(view);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




            holder.TV_order_item_item_name.setText(menu_item_name[position]);




            Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Rg.ttf");
            holder.TV_order_item_item_name.setTypeface(tf, Typeface.NORMAL);




            orderItemPreferenceAdapter = new OrderItemPreferenceAdapter(getActivity(), Pref_name);

            orderItemPreferenceAdapter.notifyDataSetChanged();

            holder.RV_myorder_prefrence.setLayoutManager(new CustomLinearLayoutManager(getActivity(),CustomLinearLayoutManager.VERTICAL,false));
            holder.RV_myorder_prefrence.setAdapter(orderItemPreferenceAdapter);
            holder.RV_myorder_prefrence.setNestedScrollingEnabled(false);









        }

        @Override
        public int getItemCount() {
            return menu_item_name.length;
        }






    }


    public class OrderItemPreferenceAdapter
            extends RecyclerView.Adapter<OrderItemPreferenceAdapter.ViewHolder> {

        String [] pref_name;



        public class ViewHolder extends RecyclerView.ViewHolder {




            public final View mView;


            public final TextView TV_item_preference;




            public ViewHolder(View view) {
                super(view);
                mView = view;


                TV_item_preference = (TextView) view.findViewById(R.id.tv_item_preference);




            }


        }


        public OrderItemPreferenceAdapter(Context context, String [] pref_name) {

            this.pref_name = pref_name;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.myorder_item_preference_elements, parent, false);



            return new ViewHolder(view);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




            holder.TV_item_preference.setText(pref_name[position]);













        }

        @Override
        public int getItemCount() {
            return pref_name.length;
        }






    }


}
