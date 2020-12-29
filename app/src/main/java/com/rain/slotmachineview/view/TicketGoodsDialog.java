package com.rain.slotmachineview.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.rain.slotmachineview.R;
import com.rain.slotmachineview.bean.LotteryInfo;


public class TicketGoodsDialog extends Dialog {
    private String cid, mac, roomId, meetingId;
    private Activity context;


    public TicketGoodsDialog(@NonNull Activity context) {
        super(context);
        this.context = context;
        init(context);
    }

    public TicketGoodsDialog(@NonNull Activity context, int themeResId) {
        super(context, themeResId);
        this.context = context;
        init(context);
    }

    protected TicketGoodsDialog(@NonNull Activity context, boolean cancelable, @Nullable DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        this.context = context;
        init(context);
    }

    private SlotScrollListener mSlotScrollListener;
    private int position;

    private TextView tv_continue_draw,tv_check_codes;
    private ImageView ivClose,iv_ticket_goods_icon;

    private void init(final Context context){
        this.setContentView(R.layout.dialog_ticket_goods);
        this.getWindow().setBackgroundDrawableResource(R.color.transparent);//transparent
        ivClose = findViewById(R.id.iv_close);
        iv_ticket_goods_icon = findViewById(R.id.iv_ticket_goods_icon);
        tv_continue_draw = findViewById(R.id.tv_continue_draw);
        tv_check_codes = findViewById(R.id.tv_check_codes);
        tv_continue_draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSlotScrollListener.onItemScroll(position);
                dismiss();
            }
        });

        tv_check_codes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });

        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public void setData(final LotteryInfo info, int position){
        this.position = position;
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Glide.with(context).load(info.getGoodsImagePath()).into(iv_ticket_goods_icon);
            }
        });
    }


    public void setOnSlotScrollListener(SlotScrollListener slotScrollListener) {
        this.mSlotScrollListener = slotScrollListener;
    }



    public interface SlotScrollListener {
        void onItemScroll(int position);
    }


}
