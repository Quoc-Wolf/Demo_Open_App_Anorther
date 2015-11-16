package asiantech.phucquoc.demo_open_app_anorther;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewById(R.id.btnCall)
    protected Button mBtnCall;
    @Click(R.id.btnCall)
    void doClickCall(){
        launchCall();
    }

    /**
     * check app have in device?
     * @param context       getApplicationContext()
     * @param packageName   PACKAGE_NAME
     * @return              true :false
     */

    private static boolean isApplicationEnable(Context context, String packageName) {
        try {
            ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_ACTIVITIES);
            return (applicationInfo != null && applicationInfo.enabled);
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * check if have app on device open app else go to app store and installs
     */
    private void launchCall() {
       if (isApplicationEnable(getApplicationContext(),Url.PACKAGE_NAME)) {
            Intent intentPresent = getPackageManager().getLaunchIntentForPackage(Url.PACKAGE_NAME);
            try {
                startActivity(intentPresent);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Intent intentCHPlay = new Intent(Intent.ACTION_VIEW, Uri.parse(Url.URL_CH_PLAY));
            startActivity(intentCHPlay);
        }
    }
}
