package com.rain.slotmachineview.view;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;

import com.rain.slotmachineview.R;


public class SlotMachineView extends LinearLayout {

    public static final int INDEX_GIFT_PACKAGE = 0;
    public static final int INDEX_COIN = 1;
    public static final int INDEX_RED_PACKAGE = 2;
    public static final int INDEX_RED_PACKAGE1 = 3;
    public static final int INDEX_RED_PACKAGE2 = 4;

    private int bingoIndex = INDEX_RED_PACKAGE;

    private long wheelDelayMillis = 200;
    private int wheelTime = 3000;
    private int wheelBaseIndex = 5* 200;
    private boolean isBingo;
    private boolean running;

    private WheelView wheel1, wheel2, wheel3;
    boolean isWin = false;

    public SlotMachineView(Context context) {
        super(context);

        initData();
        initView();
    }

    public SlotMachineView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initData();
        initView();
    }

    public SlotMachineView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initData();
        initView();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public SlotMachineView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        initData();
        initView();
    }

    private void initData() {
    }

    private void initView() {
        LinearLayout layout = (LinearLayout) View.inflate(getContext(), R.layout.layout_slot_machine, null);
        layout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

        wheel1 = layout.findViewById(R.id.slot_1);
        wheel2 = layout.findViewById(R.id.slot_2);
        wheel3 = layout.findViewById(R.id.slot_3);

        wheel1.addScrollingListener(scrolledListener1);
        wheel2.addScrollingListener(scrolledListener2);
        wheel3.addScrollingListener(scrolledListener3);

        int defaultIndex = (int) (Math.random() * 10);
        initWheel1(wheel1, defaultIndex);
        initWheel2(wheel2, defaultIndex);
        initWheel3(wheel3, defaultIndex);

        addView(layout);
    }

    private OnWheelScrollListener scrolledListener1 = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {
            running = true;
        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
        }
    };

    private OnWheelScrollListener scrolledListener2 = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {
        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
        }
    };

    private OnWheelScrollListener scrolledListener3 = new OnWheelScrollListener() {
        @Override
        public void onScrollingStarted(WheelView wheel) {
        }

        @Override
        public void onScrollingFinished(WheelView wheel) {
            running = false;

            if (onScrollListener != null) {
                onScrollListener.onStop(bingoIndex);
            }
        }
    };

    public boolean isRunning() {
        return running;
    }

    private void initWheel1(WheelView wheel, int defaultIndex) {
        wheel.setViewAdapter(new SlotMachineAdapter1(getContext()));
        wheel.setCurrentItem(defaultIndex);
        wheel.setCyclic(true);
        wheel.setEnabled(false);
    }

    private void initWheel2(WheelView wheel, int defaultIndex) {
        wheel.setViewAdapter(new SlotMachineAdapter2(getContext()));
        wheel.setCurrentItem(defaultIndex);
        wheel.setCyclic(true);
        wheel.setEnabled(false);
    }

    private void initWheel3(WheelView wheel, int defaultIndex) {
        wheel.setViewAdapter(new SlotMachineAdapter3(getContext()));
        wheel.setCurrentItem(defaultIndex);
        wheel.setCyclic(true);
        wheel.setEnabled(false);
    }

    public boolean isBingo() {
        int value1 = wheel1.getCurrentItem();
        int value2 = wheel2.getCurrentItem();
        int value3 = wheel3.getCurrentItem();
        return value1 == value2 && value1 == value3;
    }

    public void startSm(boolean isWin) {
        this.isWin = isWin;
        if (!running && bingoIndex >= INDEX_GIFT_PACKAGE && bingoIndex <= INDEX_RED_PACKAGE2) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    moveWheel(wheel1);
                }
            },300);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    moveWheel(wheel2);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            moveWheel(wheel3);
                        }
                    }, wheelDelayMillis);
                }
            }, wheelDelayMillis);
        }
    }

    private void moveWheel(WheelView wheelView) {
        if (wheelView != null) {
            if (isWin){
                int currentIndex = wheelView.getCurrentItem();
                int itemsToScroll = wheelBaseIndex + 5 - currentIndex + bingoIndex;
                wheelView.scroll(itemsToScroll, wheelTime);
            }else {
                int currentIndex = wheelView.getCurrentItem();
                int itemsToScroll = wheelBaseIndex + 3 - currentIndex + bingoIndex;
                wheelView.scroll(itemsToScroll, wheelTime);
            }

        }
    }

    // 设置中奖坐标
    public void setBingoIndex(int index) {
        this.bingoIndex = index;
    }

    public void setBingo(boolean bingo) {
        this.isBingo = bingo;
    }

    public boolean getBingo() {
        return isBingo;
    }

    private OnScrollListener onScrollListener;

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        this.onScrollListener = onScrollListener;
    }

    public interface OnScrollListener {
        void onStop(int bingoIndex);
    }
}


