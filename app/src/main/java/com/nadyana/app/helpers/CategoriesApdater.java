package com.nadyana.app.helpers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nadyana.app.R;
import com.nadyana.app.objects.Category;

import java.util.List;

public class CategoriesApdater extends RecyclerView.Adapter<CategoriesApdater.CategoriesViewHolder> {

    private Context context;
    private List<Category> categories;

    public CategoriesApdater(Context mContext, List<Category> categories) {
        context = mContext;
        this.categories = categories;
    }

    @Override
    public CategoriesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.category_list_item, parent, false);

        return new CategoriesViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final CategoriesViewHolder holder, final int position) {
        Category category = categories.get(position);
        holder.name.setText(category.getCategoryName());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoriesViewHolder extends RecyclerView.ViewHolder {
        public TextView name;

        public CategoriesViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.category_name);
        }
    }
}
