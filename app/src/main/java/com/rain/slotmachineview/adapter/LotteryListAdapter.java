package com.rain.slotmachineview.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.rain.slotmachineview.R;
import com.rain.slotmachineview.bean.LotteryInfo;
import com.rain.slotmachineview.view.SlotMachineView;

import java.util.List;


/**
 * author ：rain
 * date : 2020/12/18 16:39
 * package：com.rain.slotmachineview.adapter
 * description :
 */
public class LotteryListAdapter extends BaseQuickAdapter<LotteryInfo, BaseViewHolder> {
    private List<LotteryInfo> data;
    private Activity context;
    private SlotScrollListener mSlotScrollListener;
    private int startPosition = -1;
    private int stopPosition = -1;
    private boolean isWin = false;
    int emptyPosition;
    boolean empty;
    boolean isStop;


    public LotteryListAdapter(Activity context, int layoutResId, @Nullable List<LotteryInfo> data) {
        super(layoutResId, data);
        this.context = context;
        this.data = data;
    }

    public void upDateUI(int position,boolean empty){
        this.emptyPosition = position;
        this.empty = empty;
    }

    public void stopLottery(int position,boolean isStop){
        this.stopPosition = position;
        this.isStop = isStop;
    }

    @Override
    protected void convert(BaseViewHolder helper, LotteryInfo item) {
        ImageView img = helper.getView(R.id.iv_product);
//        Glide.with(context).load(item.getGoodsImagePath()).into(img);
        helper.setText(R.id.tv_product_name,item.getGoodsName());
        helper.setText(R.id.tv_product_price,item.getGoodsPrice());
        helper.addOnClickListener(R.id.tv_lottery);
        helper.addOnClickListener(R.id.tv_continuous_lottery);
        helper.addOnClickListener(R.id.iv_right_arrow);
        helper.addOnClickListener(R.id.layout_goods_item);
        SlotMachineView slotMachineView = helper.getView(R.id.slotMachineView);
        TextView tv_empty_goods = helper.getView(R.id.tv_empty_goods);
        final RelativeLayout layout_expend_number = helper.getView(R.id.layout_expend_number);
        TextView tv_lottery = helper.getView(R.id.tv_lottery);
        TextView tv_continuous_lottery = helper.getView(R.id.tv_continuous_lottery);

        if (item.getLeftGoodsCount()>0){
            tv_empty_goods.setVisibility(View.GONE);
            slotMachineView.setVisibility(View.VISIBLE);
            tv_lottery.setClickable(true);
            tv_continuous_lottery.setClickable(true);
            tv_lottery.setBackground(context.getResources().getDrawable(R.drawable.btn_lottery_bg));
            tv_continuous_lottery.setBackground(context.getResources().getDrawable(R.drawable.btn_continuous_lottery_bg));
        }else {
            tv_empty_goods.setVisibility(View.VISIBLE);
            slotMachineView.setVisibility(View.GONE);
            tv_lottery.setClickable(false);
            tv_continuous_lottery.setClickable(false);
            tv_lottery.setBackground(context.getResources().getDrawable(R.drawable.btn_stop_lottery_bg));
            tv_continuous_lottery.setBackground(context.getResources().getDrawable(R.drawable.btn_stop_lottery_bg));
        }

        slotMachineView.setOnScrollListener(new SlotMachineView.OnScrollListener() {
            @Override
            public void onStop(int bingoIndex) {
                layout_expend_number.setVisibility(View.GONE);
            }
        });

        if (startPosition==helper.getLayoutPosition()){
            slotMachineView.startSm(isWin);
            layout_expend_number.setVisibility(View.VISIBLE);
            startPosition = -1;
        }

        if (stopPosition==helper.getAdapterPosition()){
            if (isStop){
                tv_continuous_lottery.setText("连续抽签");
                tv_continuous_lottery.setBackground(context.getResources().getDrawable(R.drawable.btn_continuous_lottery_bg));
            }else {
                tv_continuous_lottery.setText("暂停抽签");
                tv_continuous_lottery.setBackground(context.getResources().getDrawable(R.drawable.btn_stop_lottery_bg));
            }
        }
    }


    public void startScroll(Integer position, boolean isWin) {
        this.startPosition = position;
        this.notifyItemChanged(position);
        this.isWin = isWin;
    }

    public void stopScroll(Integer position) {
        this.stopPosition = position;
        this.notifyItemChanged(position);
    }




    public void setOnSlotScrollListener(SlotScrollListener slotScrollListener) {
        this.mSlotScrollListener = slotScrollListener;
    }



    public interface SlotScrollListener {
        void onItemScroll(int result, int SPIN_TIME);
    }


}
