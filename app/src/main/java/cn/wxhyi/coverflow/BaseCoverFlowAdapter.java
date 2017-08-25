package cn.wxhyi.coverflow;

import android.support.v7.widget.RecyclerView;

import java.util.LinkedList;

/**
 * Created by 刘宇飞 on 2017/8/25.
 * 邮箱：3494576680@qq.com
 * 描述：
 */

public abstract class BaseCoverFlowAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    public int total = 0;
    public int factor = 100;
    public int border_position = 0;
    public LinkedList<BaseRecyclerModel> data=new LinkedList<>();
    @Override
    public int getItemCount() {
        return total;
    }

    public void setBorder_position(int border_position) {
        this.border_position = border_position;
        for (int i  = 0; i < border_position; i++){
            addModel();
            total = total + 2;
        }
        notifyDataSetChanged();
    }

    protected abstract void addModel();
    public void setFactor(int factor) {
        this.factor = factor;
        this.total = data.size() * factor;
    }

    public int getOriginDataSize() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }
}
