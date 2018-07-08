package com.lavawong.worldcup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";
    private TextView mTextMessage;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.navigation, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                Toast.makeText(MainActivity.this, "navigation_home clicked", Toast.LENGTH_LONG).show();
                return true;
            case R.id.navigation_dashboard:
                Toast.makeText(MainActivity.this, "navigation_dashboard clicked", Toast.LENGTH_LONG).show();
                return true;
            case R.id.navigation_notifications:
                Toast.makeText(MainActivity.this, "navigation_notifications clicked", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");

        setContentView(R.layout.activity_main);

        Toolbar topToolbar = findViewById(R.id.top_toolbar);
        setSupportActionBar(topToolbar);



        mTextMessage = findViewById(R.id.message);

        Enumeration<NetworkInterface> networkInterfaces = null;
        try {
            networkInterfaces = NetworkInterface.getNetworkInterfaces();
            StringBuilder ipAddress = new StringBuilder();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                String displayName = networkInterface.getDisplayName();
                List<InterfaceAddress> interfaceAddresses = networkInterface.getInterfaceAddresses();
                List<String> ips = new ArrayList<String>();
                for (InterfaceAddress address : interfaceAddresses) {
                    ips.add(address.getAddress().getHostAddress());
                }
                ipAddress.append(displayName).append(" --- ").append(ips.toString()).append("\r\n");
            }
            TextView ipAdressView = (TextView) findViewById(R.id.ipAddress);
            ipAdressView.setText(ipAddress);
        } catch (SocketException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onDestroy(){
        System.out.println("onDestroy");
        super.onDestroy();

    }
    /**
     * Called when the user taps the Send button
     * @param view
     */
    public void sendMessage(View view) {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = findViewById(R.id.editText);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

}
