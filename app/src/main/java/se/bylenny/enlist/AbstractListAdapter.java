package se.bylenny.enlist;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import rx.Observable;
import rx.subjects.PublishSubject;

public abstract class AbstractListAdapter<T, I extends AbstractListItem<T>> extends RecyclerView.Adapter<I> {

    private PublishSubject<I> clickPublisher = PublishSubject.create();

    @Override
    public I onCreateViewHolder(ViewGroup parent, int viewType) {
        return createViewHolder(parent);
    }

    @Override
    public void onBindViewHolder(I holder, int position) {
        T item = getItem(position);
        holder.bind(position, item, clickPublisher);
    }

    public abstract T getItem(int position);

    public Observable<I> asObservable() {
        return clickPublisher;
    }

    public abstract I createViewHolder(ViewGroup parent);
}
