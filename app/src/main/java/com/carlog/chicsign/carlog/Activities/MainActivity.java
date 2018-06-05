package com.carlog.chicsign.carlog.Activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.carlog.chicsign.carlog.Database.ScrapDB;
import com.carlog.chicsign.carlog.Dialog.AddItemDialog;
import com.carlog.chicsign.carlog.Interface.FolderScrapModel;
import com.carlog.chicsign.carlog.R;
import com.carlog.chicsign.carlog.model.Model;

import java.util.ArrayList;

/**
 * Created by Chicsign on 2018-05-19.
 */

public class MainActivity extends Activity implements DialogInterface.OnDismissListener, View.OnClickListener {

    RecyclerView mRecyclerView;
    private Context mContext;
    private GridLayoutManager mGridLayoutManager;
    private ViewModeAdapter mEditModeAdapter;
    private LayoutInflater mInflater;
    private ArrayList<FolderScrapModel> mEditList = new ArrayList<>();
    private Model scrapModel;
    private ScrapDB scrapDB = null;
    SwipeController swipeController = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = this;
        getDBInfo();
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_edit);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);
        mGridLayoutManager = new GridLayoutManager(mContext, 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mEditModeAdapter = new ViewModeAdapter(mContext, mEditList, R.layout.card_item);
        mRecyclerView.setAdapter(mEditModeAdapter);
        swipeController = new SwipeController(new SwipeControllerActions() {
            @Override
            public void onRightClicked(int position) {
                mEditModeAdapter.remove(position);
                mEditModeAdapter.notifyItemRemoved(position);
                mEditModeAdapter.notifyItemRangeChanged(position, mEditModeAdapter.getItemCount());
            }
        });

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);

        mRecyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
                swipeController.onDraw(c);
            }
        });


        TextView addBtn = (TextView) findViewById(R.id.input_add);
        addBtn.setOnClickListener(this);

    }

    public class ViewModeAdapter extends RecyclerView.Adapter<ViewModeAdapter.ViewModeHolder> {
        Context mContext;
        ArrayList<FolderScrapModel> mEditList;
        int mLayout;

        public ViewModeAdapter(Context _context, ArrayList<FolderScrapModel> _editList, int _layout) {
            this.mContext = _context;
            this.mEditList = _editList;
            this.mLayout = _layout;
        }

        @Override
        public ViewModeHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = mInflater.from(parent.getContext()).inflate(mLayout, null);
            return new ViewModeHolder(view);
        }

        @SuppressLint("NewApi")
        @Override
        public void onBindViewHolder(final ViewModeHolder holder, final int position) {
            String price = "";
            String liter = "";

            mEditList.get(position).getClass().getClasses();


            holder.mLinItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                }
            });

            final Model sm = (Model) mEditList.get(position);
            price = sm.getPrice();
            liter = sm.getLiter();
            holder.mLinItem.setTag(sm);
            holder.mTxtPrice.setText(price);
            holder.mTxtLiter.setText(liter);

        }

        @Override
        public int getItemCount() {
            return mEditList.size();
        }

        public void updateAdapter(ArrayList<FolderScrapModel> _updateList) {
            this.mEditList = _updateList;
            this.notifyDataSetChanged();
        }

        public void remove(int position) {
            ScrapDB.getScrapDB(mContext).scrap_delete((Model) mEditList.get(position));
            mEditModeAdapter.updateAdapter(getDBInfo());
            Toast.makeText(mContext,"삭제 되었습니다.", Toast.LENGTH_SHORT).show();
        }

        public class ViewModeHolder extends RecyclerView.ViewHolder {

            TextView mTxtPrice;
            TextView mTxtLiter;
            LinearLayout mLinItem;

            public ViewModeHolder(View itemView) {
                super(itemView);

                mTxtPrice = (TextView) itemView.findViewById(R.id.tv_price);
                mTxtLiter = (TextView) itemView.findViewById(R.id.tv_liter);
                mLinItem = (LinearLayout) itemView.findViewById(R.id.lin_item);
            }
        }
    }

    private ArrayList<FolderScrapModel> getDBInfo() {

        scrapDB = ScrapDB.getScrapDB(mContext);
        scrapModel = new Model();
        mEditList = new ArrayList<>();

        mEditList.addAll(scrapDB.scrap_select(scrapModel));

        return mEditList;
    }

    @Override
    public void onDismiss(DialogInterface dialogInterface) {
        AddItemDialog dialog = (AddItemDialog) dialogInterface;
        String price = dialog.getPrice();
        String liter = dialog.getLiter();
        scrapModel.setPrice(price);
        scrapModel.setLiter(liter);
        scrapDB.scrap_insert(scrapModel);
        mEditModeAdapter.updateAdapter(getDBInfo());
    }

    @Override
    public void onClick(View v) {

        AddItemDialog addDl = new AddItemDialog(MainActivity.this);
        addDl.setOnDismissListener(MainActivity.this);
        addDl.getWindow().setSoftInputMode (WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        addDl.show();
    }
}
