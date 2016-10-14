package se.bylenny.enlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import rx.subjects.PublishSubject;

public abstract class AbstractListItem<T> extends RecyclerView.ViewHolder {

    protected final Context context;

    public AbstractListItem(View itemView, Context context) {
        super(itemView);
        this.context = context;
    }

    public abstract <I extends AbstractListItem<T>> void bind(int position, T item, PublishSubject<I> subject);
}
