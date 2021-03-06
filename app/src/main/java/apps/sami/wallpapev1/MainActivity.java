package apps.sami.wallpapev1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.EditText;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import apps.sami.wallpapev1.Adapter.AdapterRVWallpaper;
import apps.sami.wallpapev1.Models.WallpaperModel;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AdapterRVWallpaper adapterRVWallpaper;
    List<WallpaperModel> wallpaperModelList;
    int pageNumber = 1;
    Boolean isScrolling = false;
    int currentItems,totalItems,scrollOutItems;


   String url = "https://api.pexels.com/v1/curated/?page="+pageNumber+"&per_page=80";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        wallpaperModelList = new ArrayList<>();
        adapterRVWallpaper = new AdapterRVWallpaper(this,wallpaperModelList);

        recyclerView.setAdapter(adapterRVWallpaper);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if(newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL){
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                currentItems =gridLayoutManager.getChildCount();
                totalItems = gridLayoutManager.getItemCount();
                scrollOutItems =gridLayoutManager.findFirstVisibleItemPosition();

                if(isScrolling && (currentItems+scrollOutItems==totalItems)){
                    isScrolling = false;
                    fetchWallpaper();
                }
            }
        });

        fetchWallpaper();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.nav_search){
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            final EditText editText = new EditText(this);
            editText.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            alert.setMessage("Search Wallpaper Here");
            alert.setTitle("Search Wallpaper");
            alert.setView(editText);
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    String query = editText.getText().toString().toLowerCase();
                    // url = "https://api.pexels.com/v1/search/?page="+pageNumber+"&per_page=80&query="+query;
                wallpaperModelList.clear();
                fetchWallpaper();
                }
            });
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    public void fetchWallpaper(){
        StringRequest request = new StringRequest(Request.Method.GET,url
                , new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("photos");
                    int lenght = jsonArray.length();

                    for (int c=0;c<jsonArray.length();c++){
                        JSONObject object = jsonArray.getJSONObject(c);
                        int id =object.getInt("id");
                        JSONObject objectSrc = object.getJSONObject("src");
                        String originalUrl = objectSrc.getString("original");
                        String large2xUrl = objectSrc.getString("large2x");
                        String largeUrl = objectSrc.getString("large");
                        String mediumUrl = objectSrc.getString("medium");
                        String smallUrl = objectSrc.getString("small");
                        String portraitUrl = objectSrc.getString("portrait");
                        String landscapeUrl = objectSrc.getString("landscape");
                        String tinyUrl = objectSrc.getString("tiny");

                        WallpaperModel wallpaperModel = new WallpaperModel(id,originalUrl,large2xUrl,largeUrl,mediumUrl,smallUrl,portraitUrl,landscapeUrl,tinyUrl);
                        wallpaperModelList.add(wallpaperModel);

                    }

                    adapterRVWallpaper.notifyDataSetChanged();
                    pageNumber++;

                }catch (JSONException e){

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> para = new HashMap<>();
                para.put( "Authorization","563492ad6f91700001000001d359b5bf46ae4b6a92dde8df391e839b");
                return para;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(request);

    }
}