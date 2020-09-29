package com.example.rabbit.recyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import com.example.rabbit.R;

import java.util.ArrayList;
import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.InnerHolder> {

    private List<Fruit> fruits;

    public FruitAdapter(List<Fruit> fruitList) {
        this.fruits = fruitList;
    }

    /**
     * 这个方法用于创建条目的view
     *
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public InnerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //传的view是条目的界面
        //两个步骤，1， 填充view，2，创建内部InnerHolder
        View view = View.inflate(parent.getContext(), R.layout.fruit_item, null);
        return new InnerHolder(view);
    }


    /**
     * 一帮用来绑定holder的，一般用来绑定数据，
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull InnerHolder holder, int position) {
        //设置数据
        holder.setData(fruits.get(position));
    }

    /**
     * 返回条目个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        if (fruits != null) {
            return fruits.size();
        }
        return 0;
    }

    public class InnerHolder extends ViewHolder {
        ImageView imageView = null;
        TextView textView = null;

        /**
         * 这里的ItemView代表某条具体点item数据
         *
         * @param itemView
         */
        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.fruit_imgage);
            textView = itemView.findViewById(R.id.fruit_name);
        }

        /**
         * 这个方法用于给具体的条目设置数据
         *
         * @param fruit
         */
        public void setData(Fruit fruit) {
            imageView.setImageResource(fruit.getImageId());
            textView.setText(fruit.getName());
        }
    }


}
