package com.example.cloudinteractive_tangling.View;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cloudinteractive_tangling.Adapter.ContentAdapter;
import com.example.cloudinteractive_tangling.Data.ArrContentItem;
import com.example.cloudinteractive_tangling.Data.ContentItem;
import com.example.cloudinteractive_tangling.R;
import com.example.cloudinteractive_tangling.Tools.Function;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity implements View.OnTouchListener, View.OnClickListener, AdapterView.OnItemClickListener {

    private ArrContentItem arrContentItem;
    private Function function;
    private Context context;
    private Intent intent;
    private Bundle bundle;
    private GridView gvContent;
    private ContentAdapter contentAdapter;
    private ArrayList<ContentItem> arrListContent;
    private String strId, strTitle, strThumUrl, strThumColor, newStrPosition, oldStrPosition;
    private int i, gvPosition;
    private SharedPreferences.Editor spEditor;
    private SharedPreferences sP;
    private FloatingActionButton fabContent;
    private ArrayList<String> arrPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_activity);

        initView();

        setTouchClick();

        gvScrollChange();

        getData();

    }

    private void initView() {

        context = getApplicationContext();

        function = new Function();

        arrListContent = new ArrayList<>();

        sP = context.getSharedPreferences("gvScroll", MODE_PRIVATE);

        spEditor = sP.edit();

        gvContent = findViewById(R.id.gvContent);

        fabContent = findViewById(R.id.fabContent);

    }

    private void setTouchClick(){

        fabContent.setOnTouchListener(this);

        fabContent.setOnClickListener(this);

        gvContent.setOnItemClickListener(this);

    }

    private void getData() {

        bundle = new Bundle();

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String apiUrl = "https://jsonplaceholder.typicode.com/photos";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                new Response.Listener<JSONArray>() {

                    @Override
                    public void onResponse(final JSONArray response) {

                        //    Log.d(function.TAG,"取得Response =" + response.toString().trim());

                        try {

                            for (i = 0; i < response.length(); i++) {

                                //    Log.d(function.TAG,"取得圈數 ="+ i);

                                JSONObject jsonObjContent = response.getJSONObject(i);

                                //    Log.d(function.TAG,"取得JSONObj ="+ jsonObjContent.toString().trim());

                                strId = jsonObjContent.get("id").toString().trim();
                                strTitle = jsonObjContent.get("title").toString().trim();
                                strThumUrl = jsonObjContent.get("thumbnailUrl").toString().trim();
                                strThumColor = function.getBackgroudColor(strThumColor, strThumUrl);
                                strThumUrl = function.getUrl(strThumUrl);

                                arrListContent.add(new ContentItem(strId, strTitle, strThumUrl, strThumColor));
                                arrContentItem = new ArrContentItem(strId, strTitle, strThumUrl, strThumColor, i, arrListContent);

                                //      Log.d(function.TAG,"取得strThumColor =" + strThumColor.toString().trim());

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                            //     Log.d(function.TAG,"錯誤 JsonE");
                        }

                        function.setGvAdapter(contentAdapter, ContentActivity.this, context, arrListContent, gvContent);

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, "載入資料錯誤, 請檢查網路或聯絡資訊相關人員, 謝謝", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }

    private void gvScrollChange() {

        gvContent.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

                switch (scrollState) {

                    case 0:      //靜止

                        fabContent.show();
                        //    function.fabShow(fabContent);

                        break;

                    case 1:      //手滑

                        fabContent.hide();
                        //    function.fabHide(fabContent);

                        gvPosition = view.getFirstVisiblePosition() + 4;

                        newStrPosition = String.valueOf(gvPosition);

                        if(!sP.getString("gvPosition", "").equals("")){

                            oldStrPosition = sP.getString("gvPosition", "");

                            arrPosition = new ArrayList<>(Arrays.asList(oldStrPosition));

                        }else {

                            arrPosition = new ArrayList<>();

                        }

                        arrPosition.add(newStrPosition);

                        newStrPosition = arrPosition.toString();

                        spEditor.putString("gvPosition", newStrPosition).apply();

                        arrPosition.clear();

                        break;

                    case 2:      //自動滑

                        break;

                }

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            /*    if ((firstVisibleItem % 32) == 0 && firstVisibleItem > 31){ // 仿ig效果

                    gvContent.smoothScrollToPosition(firstVisibleItem);

                }*/

            }

        });

        function.scrollGvPosition(gvPosition, gvContent);

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK) {

            if(event.getRepeatCount() == 0){

                if(gvContent.getFirstVisiblePosition() == 0){

                }else{

                    oldStrPosition = sP.getString("gvPosition", "");

                    arrPosition = new ArrayList<String>(Arrays.asList(oldStrPosition.split(",")));

                    oldStrPosition = arrPosition.get(arrPosition.size() - 2);
                    oldStrPosition = oldStrPosition.replace("[", "").replace("]", "").replace(",", "").trim();

                    gvPosition = Integer.parseInt(oldStrPosition);

                    if(gvPosition == 4){

                        gvContent.smoothScrollToPosition(0, 0);

                    }else {

                        gvContent.smoothScrollToPosition(gvPosition);

                    }
                    return false;

                }

            }

        }

        spEditor.clear().apply();

        return super.onKeyDown(keyCode, event);

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        switch (v.getId()){

            case R.id.fabContent:
                function.scrollGvPosition(0, gvContent);
                break;

        }

        return false;

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.fabContent:
                function.scrollGvPosition(0, gvContent);
                break;

        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        strId = arrContentItem.getArrStrId(arrListContent, position, strId);
        strTitle = arrContentItem.getArrStrTitle(arrListContent, position, strTitle);
        strThumUrl = arrContentItem.getArrStrthumbnailUrl(arrListContent, position, strThumUrl);
        strThumColor = arrContentItem.getArrStrThumColor(arrListContent, position, strThumColor);

        intent = new Intent(ContentActivity.this, ShowActivity.class);

        bundle.putString("idKey", strId);
        bundle.putString("titleKey", strTitle);
        bundle.putString("urlKey", strThumUrl);
        bundle.putString("colorKey", strThumColor);

        intent.putExtras(bundle);
        startActivity(intent);

    }

}
