package cn.wxhyi.coverflow;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.LinkedList;

/**
 * Created by yichao on 16/2/25.
 */
public class CoverFlowAdapter extends BaseCoverFlowAdapter<CoverFlowAdapter.ViewHolder> {

    private static String TAG = "CoverFlowAdapter";



    private ImageLoader loader;
    private DisplayImageOptions displayImageOptions;

    public CoverFlowAdapter(LinkedList<CardModel> cardModels, Context context){
        data.clear();
        data.addAll(cardModels);
        total = data.size() * factor;
        loader = ImageLoader.getInstance();
        loader.init(ImageLoaderConfiguration.createDefault(context));
        displayImageOptions = new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(1000))
                .build();
    }

    @Override
    public CoverFlowAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.card_item, null);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        position = position % data.size();
        holder.card_layout.setVisibility(View.VISIBLE);
        CardModel cardModel= (CardModel) data.get(position);
        showPic(holder.card_image, "drawable://" + cardModel.getImg());
        if (!cardModel.isBorder){
            holder.card_title.setText(cardModel.getTitle());
        }
        if (position < border_position || position > getItemCount() - border_position - 1){
            holder.card_layout.setVisibility(View.GONE);
        }
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        public LinearLayout card_layout;
        public ImageView card_image;
        public TextView card_title;

        public ViewHolder(View v) {
            super(v);
            card_layout = (LinearLayout) v.findViewById(R.id.card_layout);
            card_image = (ImageView) v.findViewById(R.id.card_img);
            card_title = (TextView) v.findViewById(R.id.card_title);
        }
    }


    @Override
    protected void addModel() {
        data.addFirst(new CardModel(true));
        data.addLast(new CardModel(true));
    }

    private void showPic(ImageView imgView, String url) {
        if (url == null) {
            imgView.setVisibility(View.GONE);
        } else {
            if (imgView != null && !url.equals("drawable://0")){
                loader.displayImage(url, imgView, displayImageOptions);
            }
        }
    }


}
