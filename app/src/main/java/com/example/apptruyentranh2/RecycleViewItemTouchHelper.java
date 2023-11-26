package com.example.apptruyentranh2;

import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.apptruyentranh2.Adapter.TruyenTranhYeuThichAdapter;
import com.example.apptruyentranh2.interfaceobject.ItemTouchHelperListeners;

public class RecycleViewItemTouchHelper extends ItemTouchHelper.SimpleCallback{
    private ItemTouchHelperListeners listeners;
    public RecycleViewItemTouchHelper(int dragDirs, int swipeDirs, ItemTouchHelperListeners listeners) {
        super(dragDirs, swipeDirs);
        this.listeners = listeners;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(listeners != null){
            listeners.onSwiped(viewHolder);
        }
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder != null) {
            View foreGround = ((TruyenTranhYeuThichAdapter.ViewHolder) viewHolder).layout_ForeGround;
            getDefaultUIUtil().onSelected(foreGround);
        }

    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c, @NonNull RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if(viewHolder != null) {
            View foreGround = ((TruyenTranhYeuThichAdapter.ViewHolder) viewHolder).layout_ForeGround;
            getDefaultUIUtil().onDrawOver(c, recyclerView, foreGround, dX, dY, actionState, isCurrentlyActive);
        }
    }


    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        if(viewHolder != null) {
            View foreGround = ((TruyenTranhYeuThichAdapter.ViewHolder) viewHolder).layout_ForeGround;
            getDefaultUIUtil().onDrawOver(c, recyclerView, foreGround, dX, dY, actionState, isCurrentlyActive);
        }
    }


    @Override
    public void clearView(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        if(viewHolder != null) {
            View foreGround = ((TruyenTranhYeuThichAdapter.ViewHolder) viewHolder).layout_ForeGround;
            if(foreGround != null) {
                getDefaultUIUtil().clearView(foreGround);
            }
        }
    }
}
