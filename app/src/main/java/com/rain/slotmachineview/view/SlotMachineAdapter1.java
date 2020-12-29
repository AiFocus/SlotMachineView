package com.rain.slotmachineview.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;


import com.rain.slotmachineview.R;
import com.rain.slotmachineview.utils.ScreenUtils;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * author ：rain
 * date : 2020/12/21 1:18
 * package：com.rain.slotmachineview.view.adapters
 * description :
 */
public class SlotMachineAdapter1  extends AbstractWheelAdapter {

    private Context context;
    private LinearLayout.LayoutParams params;
    private List<SoftReference<Bitmap>> images;
    private final int[] imageItems = new int[]{
            R.mipmap.icon_peach,
            R.mipmap.icon_cherrie,
            R.mipmap.icon_seven,
            R.mipmap.icon_lemon,
            R.mipmap.icon_orange,
    };

    private final int IMAGE_WIDTH = ScreenUtils.dip2px (42);
    private final int IMAGE_HEIGHT = ScreenUtils.dip2px (42);

    public SlotMachineAdapter1(Context context) {
        this.context = context;
        this.params = new LinearLayout.LayoutParams(IMAGE_WIDTH, IMAGE_HEIGHT);

        images = new ArrayList<>(imageItems.length);
        for (int id : imageItems) {
            images.add(new SoftReference<>(loadImage(id)));
        }
    }

    private Bitmap loadImage(int id) {
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), id);
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, IMAGE_WIDTH, IMAGE_HEIGHT, true);
        bitmap.recycle();
        return scaled;
    }

    @Override
    public int getItemsCount() {
        return imageItems.length;
    }

    @Override
    public View getItem(int index, View cachedView, ViewGroup parent) {
        LinearLayout itemLayout;

        if (cachedView != null) {
            itemLayout = (LinearLayout) cachedView;
        } else {
            itemLayout = (LinearLayout) View.inflate(context, R.layout.layout_slot_machine_item, null);
        }

        ImageView imageView = itemLayout.findViewById(R.id.sm_image);
        imageView.setLayoutParams(params);

        SoftReference<Bitmap> bitmapRef = images.get(index);
        Bitmap bitmap = bitmapRef.get();
        if (bitmap == null) {
            bitmap = loadImage(imageItems[index]);
            images.set(index, new SoftReference<>(bitmap));
        }
        imageView.setImageBitmap(bitmap);

        return itemLayout;
    }



}
