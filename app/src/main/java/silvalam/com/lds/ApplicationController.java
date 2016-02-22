package silvalam.com.lds;

// ApplicationController initializes RequestQueue.

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

// Initialize RequestQueue.
public class ApplicationController extends Application {
    // Log or request TAG.
    public static final String TAG = ApplicationController.class.getSimpleName();

    // A singleton instance of the application class for easy access in other places.
    private static ApplicationController sInstance;

    // Initialize queue.
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize the singleton.
        sInstance = this;
    }

    // @return ApplicationController singleton instance.
    public static synchronized ApplicationController getsInstance(){
        return sInstance;
    }

    // @return the Volley Request queue, the queue will be created if it is null.
    public RequestQueue getRequestQueue(){
        // Lazy initialize the request queue, the queue instance will be created
        // when it is accessed for the first time.
        if(mRequestQueue == null){
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }

    // Adds the specified request to the global queue, if tag is specified
    // then it is used else Default TAG is used.
    public <T> void addToRequestQueue(Request<T> req, String tag){
        // Set the default tag if tag is empty.
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    // Adds the specified request to the global queue using the default TAG.
    public <T> void addToRequestQueue(Request<T> req){
        // Set the default tag if tag is empty.
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    // Cancels all pending requests by the specified TAG.
    // Specified TAG will allow the pending/ongoing requests to be cancelled.
    public void cancelPendingRequests(Object tag){
        if (mRequestQueue != null){
            mRequestQueue.cancelAll(tag);
        }
    }
}