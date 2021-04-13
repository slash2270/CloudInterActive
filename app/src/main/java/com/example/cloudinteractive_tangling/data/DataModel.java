package com.example.cloudinteractive_tangling.data;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cloudinteractive_tangling.adapter.ContentAdapter;
import com.example.cloudinteractive_tangling.tools.Function;
import com.example.cloudinteractive_tangling.databinding.ContentActivityBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataModel {

    private ContentAdapter contentAdapter;
    private Intent intent;
    private Function function;
    private String strId, strTitle, strUrl, strColor;
    private int i;
    private Handler handler;
    private Runnable runnable;
    private ArrayList<ContentBean> arrayList;

    public void addText(addText addText, Activity activity){

        runnable = new Runnable() {
            @Override
            public void run() {

                addText.setTvt("Slash Tang");
                addText.setBtn("Require Api");

            }
        };

       activity.runOnUiThread(runnable);

    }

    public interface addText{

        String setTvt(String name);
        String setBtn(String name);

    }

    public void getData(SetArrayList setArrayList, ContentActivityBinding binding, Context context, Activity activity) {

        arrayList = new ArrayList<>();

        function = new Function();

        handler = new Handler();

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String apiUrl = "https://jsonplaceholder.typicode.com/photos";

        runnable = new Runnable() {
            @Override
            public void run() {

                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                        new Response.Listener<JSONArray>() {

                            @Override
                            public void onResponse(final JSONArray response) {

                                try {

                                    for (i = 0; i < response.length(); i++) {

                                        JSONObject jsonObjContent = response.getJSONObject(i);

                                        strId = jsonObjContent.get("id").toString().trim();
                                        strTitle = jsonObjContent.get("title").toString().trim();
                                        strUrl = jsonObjContent.get("thumbnailUrl").toString().trim();
                                        strColor = function.getBackgroudColor(strColor, strUrl);
                                        strUrl = function.getUrl(strUrl);

                                        arrayList.add(new ContentBean(strId, strTitle, strUrl, strColor));

                                    }

                                } catch (JSONException e) {
                                    e.printStackTrace();


                                }

                                setArrayList.getArrayList(arrayList);

                                function.setGvAdapter(contentAdapter, activity, context, arrayList, binding.gvContent);

                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "載入資料錯誤, 請檢查網路或聯絡資訊相關人員, 謝謝", Toast.LENGTH_SHORT).show();
                    }
                });

                requestQueue.add(jsonArrayRequest);

            }
        };

        handler.post(runnable);

    }

    public interface SetArrayList{

        ArrayList<ContentBean> getArrayList(ArrayList<ContentBean> arrayList);

    }

}
