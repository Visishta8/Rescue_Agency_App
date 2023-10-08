package com.example.recycler_view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DishRecyclerViewAdapter extends RecyclerView.Adapter<DishRecyclerViewAdapter.ViewHolder> {

    private List<Dish> arr;
    private LayoutInflater inflater;
    private int expandedPosition = -1; // Store the currently expanded item position

    public DishRecyclerViewAdapter(Context context,List<Dish> data){
        this.inflater = LayoutInflater.from(context);
        this.arr = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.awnitem_dish,parent,false);
        return new ViewHolder(v);
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dish d = arr.get(position);
        holder.imgRes.setImageResource(d.image);
        holder.txtName.setText(d.name);
        holder.txtCon.setText("Contact:"+d.numItem );

        // Check if the current item is expanded
        final boolean isExpanded = position == expandedPosition;

        // Set the visibility of your expanded view components based on 'isExpanded'
        // Set the visibility of your expanded view components based on 'isExpanded'
        if (isExpanded) {
            // Show expanded content
            holder.expandedLayout.setVisibility(View.VISIBLE);
            holder.txtEmail.setText("Email: " + d.email);
            holder.txtLocation.setText("Location: " + d.location);
            holder.txtExpertiseArea.setText("Expertise Area: " + d.expertiseArea);
            holder.txtRegistrationDate.setText("Registration Date: " + d.registrationDate);
        } else {
            // Hide expanded content
            holder.expandedLayout.setVisibility(View.GONE);
        }

        // Create a final copy of 'position'
        final int itemPosition = position;

        // Set an OnClickListener to toggle item expansion
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Toggle item expansion
                expandedPosition = isExpanded ? -1 : position;
                notifyDataSetChanged();
            }
        });
    }


    @Override
    public int getItemCount() {
        return arr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView imgRes;
        public TextView txtName;
        public TextView txtCon;
        // Add views for expanded layout
        public LinearLayout expandedLayout;
        public TextView txtEmail;
        public TextView txtLocation;
        public TextView txtExpertiseArea;
        public TextView txtRegistrationDate;
         public ViewHolder(@NonNull View itemView){
            super(itemView);
            imgRes = itemView.findViewById(R.id.imgRes);
            txtName = itemView.findViewById(R.id.txtName);
            txtCon = itemView.findViewById(R.id.txtCon);
             // Initialize views for expanded layout
             expandedLayout = itemView.findViewById(R.id.expandedLayout);
             txtEmail = itemView.findViewById(R.id.expanded_txtEmail);
             txtLocation = itemView.findViewById(R.id.expanded_txtLocation);
             txtExpertiseArea = itemView.findViewById(R.id.expanded_txtExpertise);
             txtRegistrationDate = itemView.findViewById(R.id.expanded_txtRegistrationDate);
        }
    }
}
