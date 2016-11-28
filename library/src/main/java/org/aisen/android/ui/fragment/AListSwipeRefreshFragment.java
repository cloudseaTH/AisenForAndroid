package org.aisen.android.ui.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;

import org.aisen.android.R;
import org.aisen.android.support.inject.ViewInject;

import java.io.Serializable;

/**
 * 维护ListView的SwipeRefreshLayout控件
 *
 */
public abstract class AListSwipeRefreshFragment<T extends Serializable, Ts extends Serializable, Header extends Serializable>
                                            extends AListFragment<T, Ts, Header>
                                            implements SwipeRefreshLayout.OnRefreshListener {

    @ViewInject(idStr = "swipeRefreshLayout")
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public int inflateContentView() {
        return R.layout.comm_ui_list_swiperefresh;
    }

    @Override
    protected void setupRefreshView(Bundle savedInstanceSate) {
        super.setupRefreshView(savedInstanceSate);

        setupSwipeRefreshLayout();
    }

    protected void setupSwipeRefreshLayout() {
        getSwipeRefreshLayout().setOnRefreshListener(this);
        getSwipeRefreshLayout().setColorSchemeResources(android.R.color.holo_blue_bright,
                                                    android.R.color.holo_green_light,
                                                    android.R.color.holo_orange_light,
                                                    android.R.color.holo_red_light);
    }

    @Override
    public void onRefresh() {
        onPullDownToRefresh();
    }

    @Override
    public boolean setRefreshViewToLoading() {
        getSwipeRefreshLayout().setRefreshing(true);

        return false;
    }

    @Override
    public void onRefreshViewFinished(RefreshMode mode) {
        if (mode != RefreshMode.update && getSwipeRefreshLayout().isRefreshing())
            getSwipeRefreshLayout().setRefreshing(false);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return swipeRefreshLayout;
    }

}
