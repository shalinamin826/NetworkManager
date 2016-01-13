package com.example.IpFinder;


public class MainActivity extends Activity  {

	ListView lv;
   WifiManager wifi;
   String wifis[];
   String[] wificon;
   WifiScanReceiver wifiReciever;
   
   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);
      lv=(ListView)findViewById(R.id.listView);
     
      wifi=(WifiManager)getSystemService(Context.WIFI_SERVICE);
      wifiReciever = new WifiScanReceiver();
      wifi.startScan();
      getIp();
   }
   
   protected void onPause() {
      unregisterReceiver(wifiReciever);
      super.onPause();
   }
   
   protected void onResume() {
      registerReceiver(wifiReciever, new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
      super.onResume();
   }
   
   @Override
   public boolean onCreateOptionsMenu(Menu menu) {
      // Inflate the menu; this adds items to the action bar if it is present.
      getMenuInflater().inflate(R.menu.main, menu);
      return true;
   }
   @SuppressLint("NewApi")
public void getIp(){
	   
	String ip=Formatter.formatIpAddress(wifi.getConnectionInfo().getIpAddress());
		//String ssid=Formatter.formatIpAddress(wifi.getConnectionInfo().getSSID());
//		String fr=Formatter.formatIpAddress(wifi.getConnectionInfo().getFrequency());
		String nid=Formatter.formatIpAddress(wifi.getConnectionInfo().getNetworkId());
		String ls=Formatter.formatIpAddress(wifi.getConnectionInfo().getLinkSpeed());
		String rs=Formatter.formatIpAddress(wifi.getConnectionInfo().getRssi());
		//String bs=Formatter.formatIpAddress(wifi.getConnectionInfo().getBSSID());
		
		//String mac=Formatter.formatIpAddress(wifi.getConnectionInfo().getMacAddress());

WifiInfo info =wifi.getConnectionInfo();
String mac=info.getMacAddress();
String ssid=info.getSSID();
String bssid=info.getBSSID();


	   
       Log.i("GETIP",""+ ip);
       Log.i("GETBSSID",""+ bssid);
//       Log.i("GETIP",""+ fr);
       Log.i("GETLinkSpeed",""+ ls);
       Log.i("GETMAC",""+ mac);
       Log.i("GETNID",""+ nid);
       Log.i("GETRSSI",""+ rs);




	   

	   }
   @Override
   public boolean onOptionsItemSelected(MenuItem item) {
     
      int id = item.getItemId();
      
      //noinspection SimplifiableIfStatement
      if (id == R.id.action_settings) {
         return true;
      }
      return super.onOptionsItemSelected(item);
   }
   
   private class WifiScanReceiver extends BroadcastReceiver
   {
      public void onReceive(Context c, Intent intent) 
      {
         List<ScanResult> wifiScanList = wifi.getScanResults();
         List<WifiConfiguration> wifiConfig=wifi.getConfiguredNetworks();
         
         wifis = new String[wifiScanList.size()];
         wificon = new String[wifiConfig.size()];
         
         for(int i = 0; i < wifiScanList.size(); i++)
         {
            wifis[i] = ((wifiScanList.get(i)).toString());
            
            Log.i("WIFI",""+wifis[i]);
            
            wificon[i] = ((wifiConfig.get(i)).toString());
            
            Log.i("WIFICONFIG",""+wificon[i]);
         }
         
         for(int i = 0; i < wifiConfig.size(); i++)
         {
            
            wificon[i] = ((wifiConfig.get(i)).toString());
            
            Log.i("WIFICONFIG",""+wificon[i]);
         }
         //lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,wifis));
         lv.setAdapter(new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,wificon));

      }
   }
}
