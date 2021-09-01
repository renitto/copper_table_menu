package renitto.com.copper_table_menu.Main.Presenters;

import android.app.ActionBar;
import android.app.DialogFragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;



import jp.wasabeef.recyclerview.animators.adapters.ScaleInAnimationAdapter;
import renitto.com.copper_table_menu.R;

/**
 * Created by Renitto on 2/10/2016.
 */
public class FragmentItemSelection extends DialogFragment {

    RecyclerView RV_preference;
    RecyclerView.LayoutManager mLayoutManager;
    TextView TV_detail_item_name,TV_detail_item_desc;
    Button BT_add_to_cart;

    String [] Pref_name = {"Spinach","Mushroom","Veg","Chicken","Tomato"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        View view = inflater.inflate(R.layout.item_selection,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        ActionBar actionBar = getActivity().getActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        RV_preference = (RecyclerView)view.findViewById(R.id.recycler_item_preference);
        mLayoutManager = new LinearLayoutManager(getActivity());
        RV_preference.setLayoutManager(mLayoutManager);

        TV_detail_item_name = (TextView)view.findViewById(R.id.tv_detail_item_name);
        TV_detail_item_desc = (TextView)view.findViewById(R.id.tv_detail_item_desc);

        BT_add_to_cart = (Button)view.findViewById(R.id.bt_add_to_order);


        TV_detail_item_desc.setText("\""+"sample"+"\"");


        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(),"fonts/Aller_Rg.ttf");
        TV_detail_item_name.setTypeface(tf, Typeface.NORMAL);


        // calling item preference recyclers

        ItemPreferenceAdapter itemPreferenceAdapter = new ItemPreferenceAdapter(getActivity(), Pref_name);

        // animating recyclers
        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(itemPreferenceAdapter);
        scaleAdapter.setInterpolator(new BounceInterpolator());
        scaleAdapter.setDuration(1000);
        RV_preference.setAdapter(scaleAdapter);


        BT_add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: 2/16/2016  add to cart here

                // animate cart
                ((MenuDetailActivity)getActivity()).cartAnimate();
            }
        });


        return view;
    }




    public class ItemPreferenceAdapter
            extends RecyclerView.Adapter<ItemPreferenceAdapter.ViewHolder> {

        String [] pref_name;



        public class ViewHolder extends RecyclerView.ViewHolder {




            public final View mView;


            public final CheckBox CB_preference;


            public ViewHolder(View view) {
                super(view);
                mView = view;


                CB_preference = (CheckBox) view.findViewById(R.id.cb_item_preference);

            }


        }


        public ItemPreferenceAdapter(Context context, String [] pref_name) {

            this.pref_name = pref_name;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_preference_elements, parent, false);



            return new ViewHolder(view);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {




            holder.CB_preference.setText(pref_name[position]);


        }

        @Override
        public int getItemCount() {
            return pref_name.length;
        }






    }



//    public interface OnOfferlistener
//    {
//        public MODELCateringDiscount[] getcateringdiscounts();
//
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        OnOfferlistener mylistener= (OnOfferlistener) getTargetFragment();
//        cateringDiscounts = new MODELCateringDiscount[mylistener.getcateringdiscounts().length];
//        cateringDiscounts = mylistener.getcateringdiscounts();
//
//
//    }

}


