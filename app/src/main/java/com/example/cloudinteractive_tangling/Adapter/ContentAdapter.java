package com.example.cloudinteractive_tangling.Adapter;

        import android.app.Activity;
        import android.content.Context;
        import android.graphics.Color;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.ImageView;
        import android.widget.RelativeLayout;
        import android.widget.TextView;

        import com.example.cloudinteractive_tangling.Data.ArrContentItem;
        import com.example.cloudinteractive_tangling.Data.ContentItem;
        import com.example.cloudinteractive_tangling.R;
        import com.example.cloudinteractive_tangling.Tools.Function;

        import java.util.ArrayList;

public class ContentAdapter extends BaseAdapter {

    private Activity activity;
    private Context context;
    private ArrayList<ContentItem> arrListContent;
    private String strId, strTitle, strThumUrl, strThumColor;
    private TextView tvId,tvTitle,tvUrl;
    private ImageView ivColor;
    private RelativeLayout rltColor;

    public ContentAdapter(Activity activity, Context context, ArrayList<ContentItem> arrListContent) {

        this.activity = activity;
        this.context = context;
        this.arrListContent = arrListContent;

    }

    @Override
    public int getCount() {
        return arrListContent.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Function function = new Function();

        convertView = function.getView(convertView, context, R.layout.item_content);
        rltColor = function.getRelativeLayout(convertView, context, R.id.rltColor, rltColor);
        tvId = function.getTextView(convertView, context, R.id.tvId, tvId);
        tvTitle = function.getTextView(convertView, context, R.id.tvTitle, tvTitle);
        tvUrl = function.getTextView(convertView, context, R.id.tvUrl, tvUrl);
        ivColor = function.getImageView(convertView, context, R.id.ivColor, ivColor);

        ArrContentItem arrContentItem = new ArrContentItem(strId, strTitle, strThumUrl, strThumColor, position, arrListContent);

        if (arrContentItem != null) {

            strId = arrContentItem.getArrStrId(arrListContent, position, strId);
            strTitle = arrContentItem.getArrStrTitle(arrListContent, position, strTitle);
            strThumUrl = arrContentItem.getArrStrthumbnailUrl(arrListContent, position, strThumUrl);
            strThumColor = arrContentItem.getArrStrThumColor(arrListContent, position, strThumColor);

            function.setField(tvId, tvTitle, tvUrl, ivColor, strId, strTitle, strThumUrl, strThumColor);

            rltColor.setBackgroundColor(Color.parseColor(strThumColor));// 暫時用Layout來補

        }

    //        notifyDataSetChanged();

        return convertView;

    }

}

