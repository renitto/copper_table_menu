package renitto.com.copper_table_menu.Main.Presenters;

import android.app.Fragment;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.squareup.picasso.Picasso;

import renitto.com.copper_table_menu.R;

/**
 * Created by Renitto on 2/7/2016.
 */
public class FragmentOffers extends Fragment  {
    RecyclerView RL_menu;
   StaggeredGridLayoutManager mLayoutManager;

    String[] offer_image_urls= {"http://13467564lvl2photography.blogs.lincoln.ac.uk/files/2015/10/KFC.jpeg",
            "https://farm9.staticflickr.com/8754/17025172915_dcd7bff5fb_b.jpg",
            "https://mir-s3-cdn-cf.behance.net/project_modules/disp/9f4a5b52720661.5608ec1b2c4ad.jpg",
            "https://nahmj.files.wordpress.com/2015/03/express-lunch.jpg",
            };

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {



        View view = inflater.inflate(R.layout.tablemenus,
                container, false);
        getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);


        // showing  fab.
        ((MainActivity) getActivity()).fabShow();


        RL_menu = (RecyclerView)view.findViewById(R.id.recycler_menu);
        mLayoutManager = new StaggeredGridLayoutManager(2,1);
        RL_menu.setLayoutManager(mLayoutManager);

        OfferCardAdapter offerCardAdapter = new OfferCardAdapter(getActivity(), offer_image_urls);
        RL_menu.setAdapter(offerCardAdapter);


        // hide fab on scroll
        RL_menu.addOnScrollListener(new RecyclerView.OnScrollListener(){


            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 1)
                    ((MainActivity)getActivity()).fabHide();
                else
                    ((MainActivity)getActivity()).fabShow();

            }
        });


        return view;

    }

    public class OfferCardAdapter
            extends RecyclerView.Adapter<OfferCardAdapter.ViewHolder> {

        String [] offer_image_urls;



        public class ViewHolder extends RecyclerView.ViewHolder {




            public final View mView;



            public final ImageView IV_offer_image;



            public ViewHolder(View view) {
                super(view);
                mView = view;




                IV_offer_image=(ImageView)view.findViewById(R.id.iv_offer_banner);


            }


        }


        public OfferCardAdapter(Context context, String [] offer_image_urls) {

            this.offer_image_urls = offer_image_urls;

        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.offer_card_item, parent, false);



            return new ViewHolder(view);
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);

        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {








            Picasso.with(getActivity()).load(offer_image_urls[position]).into(holder.IV_offer_image);

//            // applying paralaxx for images
//            parallaxViewController.imageParallax(holder.IV_menu_image);


            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });







        }

        @Override
        public int getItemCount() {
            return offer_image_urls.length;
        }






    }


}

