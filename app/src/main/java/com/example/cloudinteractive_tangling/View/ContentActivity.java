package com.example.cloudinteractive_tangling.View;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cloudinteractive_tangling.Adapter.ContentAdapter;
import com.example.cloudinteractive_tangling.Data.ContentItem;
import com.example.cloudinteractive_tangling.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {

    private Context context;
    private GridView gvContent;
    private ContentAdapter contentAdapter;
    private ArrayList<ContentItem> arrListItem;
    private String strId,strTitle,strthumbnailUrl;
    private int i;
    private static final String TAG = "取得Log";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);

        initContent();

        jsonContent();

    }

    private void initContent(){

        context = getApplicationContext();

        arrListItem = new ArrayList<>();

        gvContent = findViewById(R.id.gvContent);

    }

    private void jsonContent() {

        RequestQueue requestQueue= Volley.newRequestQueue(context);

        String apiUrl = "https://jsonplaceholder.typicode.com/photos";

        JsonArrayRequest jsonArrayRequest =new JsonArrayRequest(Request.Method.GET, apiUrl , null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(final JSONArray response) {

                    //    Log.d(TAG,"取得Response =" + response.toString().trim());

                        try {

                            for (i = 0; i < response.length(); i++) {

                            //    Log.d(TAG,"取得圈數 ="+ i);

                                JSONObject jsonObjContent = response.getJSONObject(i);

                            //    Log.d(TAG,"取得JSONObj ="+ jsonObjContent.toString().trim());

                                strId = jsonObjContent.get("id").toString().trim();
                                strTitle = jsonObjContent.get("title").toString().trim();
                                strthumbnailUrl = jsonObjContent.get("thumbnailUrl").toString().trim();

                                arrListItem.add(new ContentItem(strId, strTitle ,strthumbnailUrl));

                                //gvContent.smoothScrollToPosition(i); 滑動

                            //    Log.d(TAG,"取得Content =" + strId);

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                            Log.d(TAG,"錯誤 JsonE");
                        }

                        contentAdapter = new ContentAdapter(ContentActivity.this,context,arrListItem);

                        Log.d(TAG,"取得arrListItem =" + arrListItem.toString().trim());

                        gvContent.setAdapter(contentAdapter);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "載入資料錯誤, 請檢查網路或聯絡資訊相關人員, 謝謝", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

}
