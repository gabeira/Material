package com.android.material;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.material.adapter.AdapterVivz;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerFragment extends Fragment {

    private RecyclerView recyclerView;
    private static final String PREF_FILE_NAME = "prefs";
    private static final String KEY_USER_LEARNED_DRAWER = "user_learned_drawer";
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mdrawerLayout;
    private boolean mUserLearnedDrawer;
    private boolean mFromSavedInstanceState;
    private View containerView;
    private AdapterVivz adapter;

    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    public static void saveToPreferences(Context context, String preferenceName, String preferenceValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(preferenceName, preferenceValue);
        editor.apply();


    }

    public static String readFromPreferences(Context context, String preferenceName, String defaultValue) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceName, defaultValue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = Boolean.getBoolean(readFromPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, "true"));
        if (savedInstanceState != null) {
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout =inflater.inflate(R.layout.fragment_navigation_drawer, container, false);
        recyclerView = (RecyclerView) layout.findViewById(R.id.drawerList);
        adapter = new AdapterVivz(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public static List<Information> getData(){
        List<Information> data = new ArrayList<>();
        String[] titles = {"Teste1","Teste2","Teste3","Teste4"};
        for (int i=0; i< titles.length; i++ ){
            Information current = new Information();
            current.iconId = R.drawable.abc_btn_check_material;
            current.title = titles[i];
            data.add(current);
        }
        return data;
    }

    public void setUp(int fragmentId, final DrawerLayout drawerLayout, final Toolbar toolbar) {
        containerView = getActivity().findViewById(fragmentId);
        mdrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    saveToPreferences(getActivity(), KEY_USER_LEARNED_DRAWER, mUserLearnedDrawer + "");
                }
                getActivity().invalidateOptionsMenu();

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();

            }

//            @Override
//            public void onDrawerSlide(View drawerView, float slideOffset) {
//                toolbar.setAlpha(1 - slideOffset);
//                super.onDrawerSlide(drawerView, slideOffset);
//            }
        };
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mdrawerLayout.openDrawer(containerView);
        }
        mdrawerLayout.setDrawerListener(mDrawerToggle);
        mdrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(Uri uri);
    }
}
