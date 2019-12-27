package com.salajim.musab.catlovers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.salajim.musab.catlovers.R;
import com.salajim.musab.catlovers.models.Cats;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;

public class CatsAdapter extends RecyclerView.Adapter<CatsAdapter.MyViewHolder> {
    private final static int MAX_WIDTH = 200;
    private final static int MAX_HEIGHT = 200;
    private Context mContext;
    private List<Cats> catsList;

    public CatsAdapter(Context mContext, List<Cats> catsList) {
        this.mContext = mContext;
        this.catsList = catsList;
    }
    @NonNull
    @Override
    public CatsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cats, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatsAdapter.MyViewHolder holder, int position) {
        final Cats cats = catsList.get(position);

        Picasso.with(mContext)
                .load(cats.getImageUrl())
                .resize(MAX_WIDTH, MAX_HEIGHT)
                .centerCrop()
                .into(holder.mCatsImage);
    }

    @Override
    public int getItemCount() {
        return catsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.catsImage)
        ImageView mCatsImage;
        private Context mContext;

        public MyViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
        }
    }
}
