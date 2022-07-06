package com.example.zaevtour.ui.search.category;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaevtour.R;
import com.example.zaevtour.ui.bookmark.ItemTouchHelperListener;
import com.example.zaevtour.ui.search.category.CategoryRestaurant;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ItemViewHolder>
        implements ItemTouchHelperListener {

    ArrayList<CategoryRestaurant> items = new ArrayList<>();

    public CategoryAdapter(ArrayList<CategoryRestaurant> items){
        this.items = items;
    }


    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater를 이용해서 원하는 레이아웃을 띄워줌
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.list_bookmark, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        //ItemViewHolder가 생성되고 넣어야할 코드들을 넣어준다.
        holder.onBind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(CategoryRestaurant bookmarkItem){
        items.add(bookmarkItem);
    }



    @Override
    public boolean onItemMove(int from_position, int to_position) {
        //이동할 객체 저장
        CategoryRestaurant bookmarkItem = items.get(from_position);
        //이동할 객체 삭제
        items.remove(from_position);
        //이동하고 싶은 position에 추가
        items.add(to_position, bookmarkItem);

        //Adapter에 데이터 이동알림
        notifyItemMoved(from_position,to_position);
        return true;
    }

    @Override
    public void onItemSwipe(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {

        TextView list_name,list_menu;
        ImageView list_image;



        public ItemViewHolder(View itemView) {
            super(itemView);
            list_name = itemView.findViewById(R.id.list_name);
            list_menu = itemView.findViewById(R.id.list_menu);
            list_image = itemView.findViewById(R.id.list_image);
        }

        public void onBind(CategoryRestaurant bookmarkItem) {
            list_name.setText(bookmarkItem.getName());
            list_menu.setText(String.valueOf(bookmarkItem.getLocation()));
            list_image.setImageResource(bookmarkItem.getImage());
        }
    }
}
