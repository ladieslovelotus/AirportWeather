package cn.airportweather;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import cn.airportweather.model.AirportInfo;
import cn.airportweather.model.Currentobservation;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.converter.GsonConverter;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Query;

interface APIService{
    @Headers({"Accept: application/json",
              "User-Agent: Lotus"})
    @GET("/MapClick.php")
    void getInfo(@Query("lat") double lat, @Query("lon") double lon,
                 @Query("unit") int unit, @Query("lg") String lang,
                 @Query("FcstType") String type, retrofit.Callback<AirportInfo> info);
}

public class NavDrawActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks, AddLocDialogFrag.AddLocDialogListener{

    //region variables
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;
    private static ArrayAdapter<String> listAdapter;
    private static List<AirportInfo> listInfoArray = new ArrayList();
    private static GoogleMap mMap;         // Might be null if Google Play services APK is not available.
    //region fragments
    private Fragment m_Visible;
    private SupportMapFragment m_MapFragment;
    private Fragment m_ListviewFragment;
    //endregion
    //region retrofit set up
    // URL starting point
    private static String stURLBase = "http://forecast.weather.gov";
    // create new Gson object for Json handling
    private static Gson gson = new Gson();
    // create an retrofit adapter with the base url
    private static RestAdapter rest = new RestAdapter.Builder().setEndpoint(stURLBase).setConverter(new GsonConverter(gson)).build();
    // create a service for the adapter with out GET class
    private static APIService service = rest.create(APIService.class);
    //endregion
    //endregion

    //region overrides
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_draw);

        //region NavDrawer set up
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
        //endregion

        // set up the fragments
        setUpFragments();

        // save what fragment we are current in
        m_Visible = m_MapFragment;

        // start the information download process
        DownloadWeatherInformation();
        //endregion
    }

    @Override
    protected void onResume(){
        super.onResume();
        setUpMapIfNeeded();
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        switch (position){
            case 0:
                if(mMap != null)
                    setUpMap();
                showFragment(m_MapFragment);
                mTitle = "Map View";
                break;

            case 1:
                PopulateVariables();
                showFragment(m_ListviewFragment);
                mTitle = "List View";
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.action_bar_menu, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // if the add location item is selected
        if (item.getItemId() == R.id.action_search) {
            showAddLocDialog();
        }

        return super.onOptionsItemSelected(item);
    }
    //endregion

    //region fragment classes
    public static class MapFragment extends SupportMapFragment{

        private static final String MAPVIEW_SECTION = "mapview_section";

        public static MapFragment newInstance(int sectionNumber){
            MapFragment fragment = new MapFragment();
            Bundle args = new Bundle();
            args.putInt(MAPVIEW_SECTION, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            super.onCreateView(inflater, container, savedInstanceState);
            // inflate the layout we are going to edit
            View rootView = inflater.inflate(R.layout.activity_maps, container, false);

            // Do a null check to confirm that we have not already instantiated the map.
            if (mMap == null) {
                // Try to obtain the map from the SupportMapFragment.
                mMap = ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();

                // Check if we were successful in obtaining the map.
                if (mMap != null) {
                    setUpMap();

                    // set the initial camera location
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.53, -99.66), 3.0f));
                }
            }

            return rootView;
        }

        @Override
        public void onAttach(Activity activity){
            super.onAttach(activity);
        }
    }

    public static class ListViewFragment extends Fragment{

        private static final String LISTVIEW_SECTION = "listview_section";

        public static ListViewFragment newInstance(int sectionNumber){
            ListViewFragment fragment = new ListViewFragment();
            Bundle args = new Bundle();
            args.putInt(LISTVIEW_SECTION, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
            super.onCreateView(inflater, container, savedInstanceState);
            // inflate the layout
            View rootView = inflater.inflate(R.layout.activity_list, container, false);

            // return the updated layout
            return rootView;
        }

        @Override
        public void onAttach(Activity activity){
            super.onAttach(activity);
        }
    }
    //endregion

    //region helper functions
    //region NavigationDrawer
    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }

    private void setUpFragments(){
        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        //region MapFrag set up
        m_MapFragment = (MapFragment)getSupportFragmentManager().findFragmentByTag(MapFragment.MAPVIEW_SECTION);
        if(m_MapFragment == null){
            m_MapFragment = MapFragment.newInstance(0);
            ft.add(R.id.container, m_MapFragment, MapFragment.MAPVIEW_SECTION);
        }
        ft.show(m_MapFragment);
        //endregion

        //region ListFrag set up
        m_ListviewFragment = (ListViewFragment)getSupportFragmentManager().findFragmentByTag(ListViewFragment.LISTVIEW_SECTION);
        if(m_ListviewFragment == null){
            m_ListviewFragment = ListViewFragment.newInstance(1);
            ft.add(R.id.container, m_ListviewFragment, ListViewFragment.LISTVIEW_SECTION);
        }
        ft.hide(m_ListviewFragment);
        //endregion

        ft.commit();
    }

    // function used to prep for fragment switching
    private void showFragment(Fragment frag){
        if(frag == null)
            return;

        final FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right);

        if(m_Visible != null)
            ft.hide(m_Visible);

        ft.show(frag).commit();
        m_Visible = frag;
    }
    //endregion
    //region MapView
    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();

            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private static void setUpMap() {
        // empty out the map
        mMap.clear();

        // local variables
        StringBuffer st = new StringBuffer();   // create a string to produce the label
        Currentobservation co = null;                  // holder AirportInfo to reduce the number of function calls
        double dbAvgLat = 0.0, dbAvgLon = 0.0;  // variables to calculate the average Lat/Lon

        // populate it
        for(int i = 0; i < listInfoArray.size(); i++){
            // set our temp variable
            co = listInfoArray.get(i).getCurrentobservation();

            // construct our label
            st.append("ID: " + co.getId() +
                    "\nWeather: " + co.getWeather() +
                    "\nTempature: " + co.getTemp() + "\0");

            // mark the map based on our new information
            mMap.addMarker(new MarkerOptions().position(new LatLng(
                    Double.parseDouble(co.getLatitude()),
                    Double.parseDouble(co.getLongitude()))).title(st.toString()));

            // update our average iterators
            dbAvgLat += Double.parseDouble(co.getLatitude());
            dbAvgLon += Double.parseDouble(co.getLongitude());

            // reset our label buffer
            st.delete(0, st.length());
        }

        // calcuate the average
        dbAvgLat /= 10;
        dbAvgLon /= 10;

        // realign the camera to show the average location based on the Lat/Long
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dbAvgLat, dbAvgLon), 3.0f));
    }

    // function used to download information
    public void DownloadWeatherInformation(){
        ////////////////////
        // local variables
        ////////////////////
        double dbLat = 0.0, dbLon = 0.0;        // latitude and longitude temps

        //region loop 10 times to load all of the airports' information
        for(int i = 0; i < 10; i++){
            //region based on what iteration we are on, update the variables
            switch (i){
                case 0:        // KATL
                    dbLat = 33.641;
                    dbLon = -84.4226;
                    break;

                case 1:        // KLAX
                    dbLat = 33.9425;
                    dbLon = -118.409;
                    break;

                case 2:        // KORD
                    dbLat = 41.9796;
                    dbLon = -87.9045;
                    break;

                case 3:        // KDFW
                    dbLat = 32.89;
                    dbLon = -97.05;
                    break;

                case 4:        // KDEN
                    dbLat = 39.8517;
                    dbLon = -104.6734;
                    break;

                case 5:        // KJFK
                    dbLat = 40.6398;
                    dbLon = -73.7787;
                    break;

                case 6:        // KSFO
                    dbLat = 37.6188;
                    dbLon = -122.3758;
                    break;

                case 7:        // KCLT
                    dbLat = 35.2207;
                    dbLon = -80.9441;
                    break;

                case 8:        // KLAS
                    dbLat = 36.0804;
                    dbLon = -115.1523;
                    break;

                case 9:        // KPHX
                    dbLat = 33.4342;
                    dbLon = -112.0081;
                    break;
            }
            //endregion

            // call the function to collect information from the constructed URL
            service.getInfo(dbLat, dbLon, 0, "english", "json", new retrofit.Callback<AirportInfo>() {
                @Override
                public void success(AirportInfo results, Response response) {
                    // store our result into the list
                    listInfoArray.add(results);
                }

                @Override
                public void failure(RetrofitError error) {
                    error.getMessage();
                }
            });

            // reset our variables
            dbLat = dbLon = 0.0;
        }
        //endregion
    }
    //endregion
    //region ListView
    // populate our listview and array
    public void PopulateVariables(){
        // Find the ListView resource
        ListView mainListView = (ListView)findViewById(R.id.listView);

        // create and populate a list of AirportInfo
        List<String> alAirInfo = new ArrayList<String>();

        // create a temp variable
        AirportInfo ai = null;

        // loop through the array and append to the list
        for(int i = 0; i < listInfoArray.size(); i++){
            ai = listInfoArray.get(i);
            alAirInfo.add(ai.GenerateInfoString());
        }

        // create ArrayAdapter
        listAdapter = new ArrayAdapter<String>(this, R.layout.airportinfo, alAirInfo);

        // set the ArrayAdapter as the ListView's adapter
        mainListView.setAdapter(listAdapter);
    }
    //endregion
    //endregion

    //region dialog functions
    // interface to handle dialog callbacks
    public interface AddLocDialogListener{
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    // function to create the AddLocDialog
    public void showAddLocDialog(){
        // create an instance of the fragment and show it
        DialogFragment dialog = new AddLocDialogFrag();
        dialog.show(getFragmentManager(), "AddLocDialogFrag");
    }

    // the dialog fragment receives a reference to this activity through onAttach() callback
    // to use the following methods defined in the AddLocDialogListener interface
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        ////////////////////
        //region local variables
        ////////////////////
        // hold the passed in dialog in temp
        Dialog dia = dialog.getDialog();
        // capture the latitude from the EditText
        final double dbLat = Double.parseDouble(((EditText) dia.findViewById(R.id.dialog_edittext_latitude)).getText().toString());
        // capture the longitude from the EditText
        final double dbLon = Double.parseDouble(((EditText) dia.findViewById(R.id.dialog_edittext_longitude)).getText().toString());
        // create a string to produce the label
        final StringBuffer st = new StringBuffer();
        //endregion

        //region pull json from the internet based on the user's request
        // call the function to collect information from the constructed URL
        service.getInfo(dbLat, dbLon, 0, "english", "json", new retrofit.Callback<AirportInfo>() {
            @Override
            public void success(AirportInfo results, Response response) {
                // construct our label
                st.append("ID: " + results.getCurrentobservation().getId() +
                        "\nWeather: " + results.getCurrentobservation().getWeather() +
                        "\nTempature: " + results.getCurrentobservation().getTemp() + "\0");

                // mark the map based on our new information and make it azure
                mMap.addMarker(new MarkerOptions().position(new LatLng(
                        Double.parseDouble(results.getCurrentobservation().getLatitude()),
                        Double.parseDouble(results.getCurrentobservation().getLongitude()))).title(st.toString())
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));

                // store our result into the list
                listInfoArray.add(results);

                // reset our label buffer
                st.delete(0, st.length());

                // if we are currently viewing the MapView
                if(m_Visible == m_MapFragment)
                    // realign the camera to show the location based on the Lat/Long
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(dbLat, dbLon), 3.0f));
                // if we are currently viewing the ListView
                else if(m_Visible == m_ListviewFragment)
                    // update the list
                    PopulateVariables();
            }

            @Override
            public void failure(RetrofitError error) {
                // display Toast message
                Toast.makeText(NavDrawActivity.this, "Desired Latitude and Longitude outside of the U.S.", Toast.LENGTH_SHORT).show();
            }
        });
        //endregion
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        // close the dialog without doing anything
        dialog.dismiss();
    }
    //endregion
}
