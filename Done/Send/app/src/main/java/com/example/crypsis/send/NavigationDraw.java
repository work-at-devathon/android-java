package com.example.crypsis.send;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NavigationDraw extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ListView listView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    String string = "";
    LinearLayout linearLayout;
    EditText invite, addphone;
    String id;
    Dialog addCustomerPhone, invitationDialog, customerSearchDialog;Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navdrawer);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu);
        retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.116:3001/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        string = (String) getTitle();

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        listView = (ListView) findViewById(R.id.left_drawer);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.menu, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                getSupportActionBar().setTitle(string);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Select a Planet");
                invalidateOptionsMenu();
            }
        };
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.drawer_list_item, getResources().getStringArray(R.array.planets_array));
        listView.setAdapter(arrayAdapter);
        ActionBar actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String[] planets = getResources().getStringArray(R.array.planets_array);
                string = planets[position];
                switch (position) {
                    case 0:
                        invitationDialog = new Dialog(NavigationDraw.this);
                        invitationDialog.setContentView(R.layout.invite_team_members);
                        invitationDialog.show();
                        break;
                    case 1:
                        Dialog d1 = new Dialog(NavigationDraw.this);
                        d1.setContentView(R.layout.customers_search);
                        d1.show();

                        break;
                    default:
                        Toast.makeText(NavigationDraw.this, "hello2", Toast.LENGTH_SHORT).show();

                }
//                PlanetFragment planetFragment=new PlanetFragment();
//                Bundle bundle=new Bundle();
//                bundle.putInt("position", position);
//                planetFragment.setArguments(bundle);
//                FragmentManager fragmentManager=getFragmentManager();
//                FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.content_frame,planetFragment);
//                fragmentTransaction.commit();
                drawerLayout.closeDrawer(listView);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        int id = item.getItemId();

        if (id == R.id.addPhone) {
            addCustomerPhone = new Dialog(NavigationDraw.this);
            addCustomerPhone.setContentView(R.layout.customer_search_add_customer);
            addCustomerPhone.show();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        boolean drawerOpen = drawerLayout.isDrawerOpen(listView);
        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    public void invite(View v) {



        invite = (EditText) findViewById(R.id.invitemember);
        id = "avinaash@devathon.com";
        InviteTeamMember inviteTeamMember = new InviteTeamMember("email", id);

        FileUploadInterface fileUploadInterface = retrofit.create(FileUploadInterface.class);

        Call<InviteTeamMember> call = fileUploadInterface.invite(inviteTeamMember);
        call.enqueue(new Callback<InviteTeamMember>() {
            @Override
            public void onResponse(Call<InviteTeamMember> call, Response<InviteTeamMember> response) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<InviteTeamMember> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
            }
        });
        invitationDialog.dismiss();
    }

    public void addCustomerNumber(View v) {



        addphone = (EditText)v.findViewById(R.id.phone);
        id = addphone.getText().toString();
        AddCustomerPhoneNumber addCustomerPhoneNumber = new AddCustomerPhoneNumber("phone", id);

        FileUploadInterface fileUploadInterface = retrofit.create(FileUploadInterface.class);

        Call<AddCustomerPhoneNumber> call = fileUploadInterface.addPhone(addCustomerPhoneNumber);
        call.enqueue(new Callback<AddCustomerPhoneNumber>() {
            @Override
            public void onResponse(Call<AddCustomerPhoneNumber> call, Response<AddCustomerPhoneNumber> response) {
                Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailure(Call<AddCustomerPhoneNumber> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();

            }
        });

addCustomerPhone.dismiss();
    }
}