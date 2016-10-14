package se.bylenny.enlist;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import rx.Subscriber;
import rx.Subscription;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private static final String STATE = "STATE";

    private CoordinatorLayout coordinatorLayout;
    private RecyclerView listView;
    private GeneratedListAdapter adapter;
    private Subscription subscription;
    private Snackbar snackbar;
    private int layoutState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        listView = (RecyclerView) findViewById(R.id.list);
        setLayout(R.id.action_list);
        adapter = new GeneratedListAdapter(200);
        listView.setAdapter(adapter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE, layoutState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (null != savedInstanceState) {
            setLayout(savedInstanceState.getInt(STATE, R.id.action_list));
        }
    }

    private void setLayout(int layoutState) {
        RecyclerView.LayoutManager manager = null;
        switch (layoutState) {
            case R.id.action_list:
                manager = new LinearLayoutManager(this);
                break;
            case R.id.action_grid:
                manager = new GridLayoutManager(this, 2);
                break;
            case R.id.action_tiled:
                manager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
                break;
        }
        if (null != manager) {
            this.layoutState = layoutState;
            listView.setLayoutManager(manager);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        subscription = adapter.asObservable().subscribe(new Subscriber<GeneratedListItem>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, e.getMessage(), e);
            }

            @Override
            public void onNext(GeneratedListItem generatedListItem) {
                String message = generatedListItem.getItem().getString();
                showMessage(message);
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        subscription.unsubscribe();
        subscription = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setLayout(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void showMessage(String message) {
        if (null != snackbar) {
            snackbar.dismiss();
        }
        snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}
