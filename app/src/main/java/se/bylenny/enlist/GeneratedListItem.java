package se.bylenny.enlist;

import android.content.Context;
import android.view.View;
import android.widget.Button;

import rx.subjects.PublishSubject;

public class GeneratedListItem extends AbstractListItem<Item> {

    private final View backgroundView;
    private final Button textView;
    private Item item;

    public GeneratedListItem(View itemView, Context context) {
        super(itemView, context);
        backgroundView = itemView.findViewById(R.id.background);
        textView = (Button) itemView.findViewById(R.id.button);
    }

    @Override
    public <I extends AbstractListItem<Item>> void bind(
            int position, Item item, final PublishSubject<I> subject) {
        this.item = item;
        backgroundView.setBackgroundColor(item.getBackground());
        textView.setText(item.getString());
        textView.setTextColor(item.getColor());
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subject.onNext((I) GeneratedListItem.this);
            }
        });
    }

    public Item getItem() {
        return item;
    }
}
