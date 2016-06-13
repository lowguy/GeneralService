package hyp.generalservice;

import android.app.ActivityManager;
import android.content.Context;
import android.media.AudioManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

/**
 * 系统服务
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) MainActivity.this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main,null);
        setContentView(R.layout.activity_main);
    }
    public void doClick(View v){
        switch (v.getId()){
            case R.id.network:{
                if(isNetWorkConnected(MainActivity.this) == true){
                    Toast.makeText(MainActivity.this,"网络已打开",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"网络已断开",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.enableOrDisable_WIFI:{
                //WIFI服务
                WifiManager wifiManager = (WifiManager) MainActivity.this.getSystemService(WIFI_SERVICE);
                if(wifiManager.isWifiEnabled()){
                    wifiManager.setWifiEnabled(false);
                    Toast.makeText(MainActivity.this,"WIFI已关闭",Toast.LENGTH_SHORT).show();
                }else{
                    wifiManager.setWifiEnabled(true);
                    Toast.makeText(MainActivity.this,"WIFI已打开",Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.getvoice:{
                //音量服务
                AudioManager audioManager = (AudioManager) MainActivity.this.getSystemService(AUDIO_SERVICE);
                int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM);
                int current = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM);
                Toast.makeText(MainActivity.this,"系统音量的最大值："+max+",当前音量："+current,Toast.LENGTH_SHORT).show();
                break;
            }
            case R.id.getPackagname:{
                ActivityManager activityManager = (ActivityManager) MainActivity.this.getSystemService(ACTIVITY_SERVICE);
                String packagename = activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
                Toast.makeText(MainActivity.this,"当前包名："+packagename,Toast.LENGTH_SHORT).show();
                break;
            }
        }

    }

    /**
     * 网络是否连接
     * @param context
     * @return
     */
    public Boolean isNetWorkConnected(Context context){
        if(context != null){
            //网络连接服务
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            if(networkInfo != null){
                return networkInfo.isAvailable();
            }
        }
        return false;
    }
}
