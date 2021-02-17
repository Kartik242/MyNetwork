package ru.ifsoft.network;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;

import com.android.volley.toolbox.ImageLoader;
import com.balysv.materialripple.MaterialRippleLayout;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import ru.ifsoft.network.app.App;
import ru.ifsoft.network.constants.Constants;


public class MenuFragment extends Fragment implements Constants {

    ImageLoader imageLoader;

    private ImageView mFriendsIcon; //mGuestsIcon;

    SaveState saveState;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private int checkedItem;
    private String Selected;

    //to access shared preference
    private final  String CheckedItem = "checked_item";


    private ImageView mNavGalleryIcon,mNavThemeIcon, mNavGroupsIcon, mNavFriendsIcon, mNavGuestsIcon, mNavMarketIcon, mNavNearbyIcon, mNavFavoritesIcon, mNavStreamIcon, mNavPopularIcon, mNavUpgradesIcon, mNavSettingsIcon;

    private MaterialRippleLayout mNavGallery,mNAvTheme,mNavGroups, mNavStream, mNavFriends, mNavMarket, mNavGuests, mNavFavorites, mNavNearby, mNavPopular, mNavUpgrades, mNavSettings;

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        imageLoader = App.getInstance().getImageLoader();

        setHasOptionsMenu(false);

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        sharedPreferences = getActivity().getSharedPreferences("themes", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        switch (getCheckedItem()){
            case 0 :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case 1 :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case 2 :
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;

        }

        View rootView = inflater.inflate(R.layout.fragment_menu, container, false);

        getActivity().setTitle(R.string.nav_menu);

//        mNavGallery = (MaterialRippleLayout) rootView.findViewById(R.id.nav_gallery);
        mNavGroups = (MaterialRippleLayout) rootView.findViewById(R.id.nav_groups);
        mNavFriends = (MaterialRippleLayout) rootView.findViewById(R.id.nav_friends);
//        mNavGuests = (MaterialRippleLayout) rootView.findViewById(R.id.nav_guests);
        //mNavMarket = (MaterialRippleLayout) rootView.findViewById(R.id.nav_market);
        mNavNearby = (MaterialRippleLayout) rootView.findViewById(R.id.nav_nearby);
        mNavFavorites = (MaterialRippleLayout) rootView.findViewById(R.id.nav_favorites);
        //mNavStream = (MaterialRippleLayout) rootView.findViewById(R.id.nav_stream);
        //mNavPopular = (MaterialRippleLayout) rootView.findViewById(R.id.nav_popular);
//        mNavUpgrades = (MaterialRippleLayout) rootView.findViewById(R.id.nav_upgrades);
        mNavSettings = (MaterialRippleLayout) rootView.findViewById(R.id.nav_settings);
        mNAvTheme = (MaterialRippleLayout) rootView.findViewById(R.id.nav_themes);

        // Counters

        mFriendsIcon = (ImageView) rootView.findViewById(R.id.nav_friends_count_icon);
//        mGuestsIcon = (ImageView) rootView.findViewById(R.id.nav_guests_count_icon);

        // Icons

//        mNavGalleryIcon = (ImageView) rootView.findViewById(R.id.nav_gallery_icon);
        mNavGroupsIcon = (ImageView) rootView.findViewById(R.id.nav_groups_icon);
        mNavFriendsIcon = (ImageView) rootView.findViewById(R.id.nav_friends_icon);
//        mNavGuestsIcon = (ImageView) rootView.findViewById(R.id.nav_guests_icon);
//        mNavMarketIcon = (ImageView) rootView.findViewById(R.id.nav_market_icon);
        mNavNearbyIcon = (ImageView) rootView.findViewById(R.id.nav_nearby_icon);
        mNavFavoritesIcon = (ImageView) rootView.findViewById(R.id.nav_favorites_icon);
//        mNavStreamIcon = (ImageView) rootView.findViewById(R.id.nav_stream_icon);
//        mNavPopularIcon = (ImageView) rootView.findViewById(R.id.nav_popular_icon);
//        mNavUpgradesIcon = (ImageView) rootView.findViewById(R.id.nav_upgrades_icon);
        mNavSettingsIcon = (ImageView) rootView.findViewById(R.id.nav_settings_icon);
        mNavThemeIcon = (ImageView) rootView.findViewById(R.id.nav_themes_icon);

        mNAvTheme.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    animateIcon(mNavThemeIcon);
                }

                return false;
            }
        });

        mNavThemeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });



//        if (saveState.getState() == true){
//            switch_btn.setChecked(true);
//        }



        if (!MARKETPLACE_FEATURE) {

            mNavMarket.setVisibility(View.GONE);
        }

        if (!UPGRADES_FEATURE) {

            mNavUpgrades.setVisibility(View.GONE);
        }

//        mNavGallery.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//
//                    animateIcon(mNavGalleryIcon);
//                }
//
//                return false;
//            }
//        });
//
//        mNavGallery.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getActivity(), GalleryActivity.class);
//                i.putExtra("profileId", App.getInstance().getId());
//                getActivity().startActivity(i);
//            }
//        });

//        switch_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    saveState.setState(true);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                }
//                else{
//                    saveState.setState(false);
//                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                }
//            }
//        });


        mNavGroups.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    animateIcon(mNavGroupsIcon);
                }

                return false;
            }
        });

        mNavGroups.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), GroupsActivity.class);
                startActivity(i);
            }
        });

        mNavFriends.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    animateIcon(mNavFriendsIcon);
                }

                return false;
            }
        });

        mNavFriends.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), FriendsActivity.class);
                i.putExtra("profileId", App.getInstance().getId());
                startActivity(i);
            }
        });

//        mNavGuests.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//
//                    animateIcon(mNavGuestsIcon);
//                }
//
//                return false;
//            }
//        });
//
//        mNavGuests.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getActivity(), GuestsActivity.class);
//                startActivity(i);
//            }
//        });

//        mNavMarket.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//
//                    animateIcon(mNavMarketIcon);
//                }
//
//                return false;
//            }
//        });
//
//        mNavMarket.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getActivity(), MarketActivity.class);
//                startActivity(i);
//            }
//        });

        mNavNearby.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    animateIcon(mNavNearbyIcon);
                }

                return false;
            }
        });

        mNavNearby.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), NearbyActivity.class);
                startActivity(i);
            }
        });

        mNavFavorites.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    animateIcon(mNavFavoritesIcon);
                }

                return false;
            }
        });

        mNavFavorites.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), FavoritesActivity.class);
                i.putExtra("profileId", App.getInstance().getId());
                startActivity(i);
            }
        });

//        mNavStream.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//
//                    animateIcon(mNavStreamIcon);
//                }
//
//                return false;
//            }
//        });
//
//        mNavStream.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getActivity(), StreamActivity.class);
//                startActivity(i);
//            }
//        });

//        mNavPopular.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//
//                    animateIcon(mNavPopularIcon);
//                }
//
//                return false;
//            }
//        });
//
//        mNavPopular.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getActivity(), PopularActivity.class);
//                startActivity(i);
//            }
//        });

//        mNavUpgrades.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//
//                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//
//                    animateIcon(mNavUpgradesIcon);
//                }
//
//                return false;
//            }
//        });
//
//        mNavUpgrades.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View view) {
//
//                Intent i = new Intent(getActivity(), UpgradesActivity.class);
//                startActivity(i);
//            }
//        });

        mNavSettings.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                    animateIcon(mNavSettingsIcon);
                }

                return false;
            }
        });


        mNavSettings.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), SettingsActivity.class);
                startActivity(i);
            }
        });

        updateView();

        // Inflate the layout for this fragment
        return rootView;
    }

    private void openDialog() {
        String[] themes = getActivity().getResources().getStringArray(R.array.theme);
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getActivity());
        builder.setTitle("Select theme");
        builder.setSingleChoiceItems(R.array.theme, getCheckedItem(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                Selected = themes[i];
                checkedItem = i;


            }
        });
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if(Selected == null){
                    Selected = themes[i];
                    checkedItem = i;

                }
                switch (Selected){
                    case "System Default" :
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                        break;
                    case "Dark" :
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        break;
                    case "Light" :
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        break;

                }
                setCheckedItem(checkedItem);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private int getCheckedItem(){
        return sharedPreferences.getInt(CheckedItem,0);
    }

    private void setCheckedItem(int i){
        editor.putInt(CheckedItem,i);
        editor.apply();
    }
    public void updateView() {

        // Counters

        mFriendsIcon.setVisibility(View.GONE);
        //mGuestsIcon.setVisibility(View.GONE);

        if (App.getInstance().getNewFriendsCount() != 0) {

            mFriendsIcon.setVisibility(View.VISIBLE);
        }

        if (App.getInstance().getGuestsCount() != 0) {

           // mGuestsIcon.setVisibility(View.VISIBLE);
        }
    }

    private void animateIcon(ImageView icon) {

        ScaleAnimation scale = new ScaleAnimation(1.0f, 0.8f, 1.0f, 0.8f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f,
                ScaleAnimation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(175);
        scale.setInterpolator(new LinearInterpolator());

        icon.startAnimation(scale);
    }

    @Override
    public void onResume() {

        super.onResume();

        updateView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}