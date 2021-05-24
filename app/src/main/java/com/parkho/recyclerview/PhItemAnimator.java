package com.parkho.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.view.animation.BounceInterpolator;

public class PhItemAnimator extends SimpleItemAnimator {

    private Context mContext;

    public PhItemAnimator(Context a_context) {

        mContext = a_context;
    }

    @Override
    public boolean animateRemove(RecyclerView.ViewHolder a_holder) {
        return false;
    }

    @Override
    public boolean animateAdd(RecyclerView.ViewHolder a_holder) {
        Animator animator = AnimatorInflater.loadAnimator(mContext, R.animator.zoom_in);
        animator.setInterpolator(new BounceInterpolator());
        animator.setTarget(a_holder.itemView);
        animator.start();

        return true;
    }

    @Override
    public boolean animateMove(RecyclerView.ViewHolder a_holder, int a_fromX, int a_fromY, int a_toX, int a_toY) {
        return false;
    }

    @Override
    public boolean animateChange(RecyclerView.ViewHolder a_oldHolder, RecyclerView.ViewHolder a_newHolder, int a_fromLeft, int a_fromTop, int a_toLeft, int a_toTop) {
        return false;
    }

    @Override
    public void runPendingAnimations() {

    }

    @Override
    public void endAnimation(RecyclerView.ViewHolder a_item) {

    }

    @Override
    public void endAnimations() {

    }

    @Override
    public boolean isRunning() {
        return false;
    }
}