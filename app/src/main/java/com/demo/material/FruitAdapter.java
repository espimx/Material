package com.demo.material;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private Context mContext;
    private List<Fruit> mFruitList;

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        ImageView mFruitImage;
        TextView mFruitName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = (CardView) itemView;
            mFruitImage = itemView.findViewById(R.id.fruit_image);
            mFruitName = itemView.findViewById(R.id.fruit_name);
        }
    }

    @NonNull
    @Override
    public FruitAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (mContext == null) {
            mContext = parent.getContext();
        }
        View view = LayoutInflater.from(mContext).inflate(R.layout.fruit_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FruitAdapter.ViewHolder holder, int position) {
        final Fruit fruit = mFruitList.get(position);
        holder.mFruitName.setText(fruit.getName());
        Glide.with(mContext).load(fruit.getImageId()).into(holder.mFruitImage);
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = holder.getAdapterPosition();
                Fruit fruitItem = mFruitList.get(position);
                Intent intent = new Intent(mContext, FruitActivity.class);
                intent.putExtra(FruitActivity.FRUIT_NAME, fruitItem.getName());
                intent.putExtra(FruitActivity.FRUIT_IMAGE_ID, fruitItem.getImageId());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }
}
