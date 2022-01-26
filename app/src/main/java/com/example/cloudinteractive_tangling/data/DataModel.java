package com.example.cloudinteractive_tangling.data;

import android.content.Context;
import android.os.Handler;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.cloudinteractive_tangling.tools.Function;
import com.example.cloudinteractive_tangling.databinding.ContentActivityBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DataModel {

    private Function function;
    private String strId, strTitle, strUrl, strColor;
    private int i;
    private ArrayList<ContentBean> arrayList;

    public void addText(addText addText){

        addText.setTvt("Slash Tang");
        addText.setBtn("Require Api");

    }

    public interface addText{

        void setTvt(String name);
        void setBtn(String name);

    }

    public void getData(SetArrayList setArrayList, ContentActivityBinding binding, Context context, Handler handler, Runnable runnable) {

        arrayList = new ArrayList<>();

        function = new Function();

        RequestQueue requestQueue = Volley.newRequestQueue(context);

        String apiUrl = "https://jsonplaceholder.typicode.com/photos";

        runnable = () -> {

            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, apiUrl, null,
                    response -> {

                        try {

                            for (i = 0; i < response.length(); i++) {

                                JSONObject jsonObjContent = response.getJSONObject(i);

                                strId = jsonObjContent.get("id").toString().trim();
                                strTitle = jsonObjContent.get("title").toString().trim();
                                strUrl = jsonObjContent.get("thumbnailUrl").toString().trim();
                                strColor = function.getBackgroudColor(strUrl);
                                strUrl = function.getUrl(strUrl);

                                arrayList.add(new ContentBean(strId, strTitle, strUrl, strColor));

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();


                        }

                        setArrayList.getArrayList(arrayList);

                        function.setGvAdapter(context, arrayList, binding.gvContent);

                    }, error -> Toast.makeText(context, "載入資料錯誤, 請檢查網路或聯絡資訊相關人員, 謝謝", Toast.LENGTH_SHORT).show());

            requestQueue.add(jsonArrayRequest);

        };

        handler.post(runnable);

    }

    public interface SetArrayList{

        void getArrayList(ArrayList<ContentBean> arrayList);

    }

}
