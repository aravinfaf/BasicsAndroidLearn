package com.e.learn.pagination;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager linearLayoutManager;

    public PaginationScrollListener(LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int visibleitem=linearLayoutManager.getChildCount();
        int totalitemcount=linearLayoutManager.getItemCount();
        int firstVisibleItem=linearLayoutManager.findFirstVisibleItemPosition();

        if(!isLoading() && !isLastPage()){

            if( (visibleitem+firstVisibleItem)>=totalitemcount && firstVisibleItem>=0 && totalitemcount >= getTotalPageCount()){
                loadMoreItems();
            }
        }
    }

    public abstract boolean isLoading();
    public abstract boolean isLastPage();

    public abstract void loadMoreItems();
    public abstract int getTotalPageCount();
}
