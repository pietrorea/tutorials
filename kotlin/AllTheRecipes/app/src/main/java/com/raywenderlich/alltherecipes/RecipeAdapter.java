package com.raywenderlich.alltherecipes;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pietrorea on 10/21/17.
 */


public class RecipeAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Recipe> mDataSource;

    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put("Low-Carb", R.color.colorLowCarb);
        put("Low-Fat", R.color.colorLowFat);
        put("Low-Sodium", R.color.colorLowSodium);
        put("Medium-Carb", R.color.colorMediumCarb);
        put("Vegetarian", R.color.colorVegetarian);
        put("Balanced", R.color.colorBalanced);
    }};

    public RecipeAdapter(Context context, ArrayList<Recipe> items) {
        mContext = context;
        mDataSource = items;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int position) {
        return mDataSource.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        View rowView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
//
//        TextView titleTextView = (TextView) rowView.findViewById(R.id.recipe_list_title);
//        TextView subtitleTextView = (TextView) rowView.findViewById(R.id.recipe_list_subtitle);
//        TextView detailTextView = (TextView) rowView.findViewById(R.id.recipe_list_detail);
//        ImageView thumbnailImageView = (ImageView) rowView.findViewById(R.id.recipe_list_thumbnail);

        ViewHolder holder;
         if (convertView == null) {
             convertView = mInflater.inflate(R.layout.list_item_recipe, parent, false);
             holder = new ViewHolder();
             holder.thumbnailImageView = (ImageView) convertView.findViewById(R.id.recipe_list_thumbnail);
             holder.titleTextView = (TextView) convertView.findViewById(R.id.recipe_list_title);
             holder.subtitleView = (TextView) convertView.findViewById(R.id.recipe_list_subtitle);
             holder.detailTextView = (TextView) convertView.findViewById(R.id.recipe_list_detail);

             convertView.setTag(holder);
         } else {
             holder = (ViewHolder) convertView.getTag();
         }

        TextView titleTextView = holder.titleTextView;
        TextView subtitleTextView = holder.subtitleView;
        TextView detailTextView = holder.detailTextView;
        ImageView thumbnailImageView = holder.thumbnailImageView;

        Recipe recipe = (Recipe) getItem(position);
        titleTextView.setText(recipe.getTitle());
        subtitleTextView.setText(recipe.getDescription());
        detailTextView.setText(recipe.getLabel());

        Picasso.with(mContext).load(recipe.getImageUrl()).placeholder(R.mipmap.ic_launcher).into(thumbnailImageView);

        Typeface titleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-Bold.ttf");
        titleTextView.setTypeface(titleTypeFace);

        Typeface subtitleTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/JosefinSans-SemiBoldItalic.ttf");
        subtitleTextView.setTypeface(subtitleTypeFace);

        Typeface detailTypeFace = Typeface.createFromAsset(mContext.getAssets(), "fonts/Quicksand-Bold.otf");
        detailTextView.setTypeface(detailTypeFace);

        detailTextView.setTextColor(ContextCompat.getColor(mContext, LABEL_COLORS.get(recipe.getLabel())));

        return convertView;
    }

    private static class ViewHolder {
        public TextView titleTextView;
        public TextView subtitleView;
        public TextView detailTextView;
        public ImageView thumbnailImageView;
    }

}


