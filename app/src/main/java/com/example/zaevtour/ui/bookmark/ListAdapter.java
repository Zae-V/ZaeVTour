package com.example.zaevtour.ui.bookmark;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zaevtour.R;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ItemViewHolder>
        implements ItemTouchHelperListener{

    ArrayList<Restaurant> items = new ArrayList<>();

    public ListAdapter(ArrayList<Restaurant> items){
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

    public void addItem(Restaurant restaurant){
        items.add(restaurant);
    }



    @Override
    public boolean onItemMove(int from_position, int to_position) {
        //이동할 객체 저장
        Restaurant restaurant = items.get(from_position);
        //이동할 객체 삭제
        items.remove(from_position);
        //이동하고 싶은 position에 추가
        items.add(to_position,restaurant);

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

        TextView list_name,list_location,list_hours;
        ImageView list_image;



        public ItemViewHolder(View itemView) {
            super(itemView);
            list_name = itemView.findViewById(R.id.list_name);
            list_location = itemView.findViewById(R.id.list_location);
            list_hours = itemView.findViewById(R.id.list_hours);
            list_image = itemView.findViewById(R.id.list_image);
        }

        public void onBind(Restaurant restaurant) {
            list_name.setText(restaurant.getName());
            list_location.setText(String.valueOf(restaurant.getLocation()));
            list_hours.setText(String.valueOf(restaurant.getHours()));
            list_image.setImageResource(restaurant.getImage());
        }
    }
}
