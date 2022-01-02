package com.example.mynewsapp.topheadlines;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynewsapp.R;

import java.util.List;

public class TopHeadlinesAdapter extends RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder>{

  List<TopHeadlinesModel> topHeadlinesModelList;
  Context context;

    public TopHeadlinesAdapter(List<TopHeadlinesModel> topHeadlinesModelList, Context context) {
        this.topHeadlinesModelList = topHeadlinesModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.top_headlines_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         holder.title.setText(topHeadlinesModelList.get(position).getTitle());
        holder.desp.setText(topHeadlinesModelList.get(position).getDesp());
        holder.source.setText(topHeadlinesModelList.get(position).getSource());
        holder.Link.setText(topHeadlinesModelList.get(position).getLink());
    }

    @Override
    public int getItemCount() {
        return topHeadlinesModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView title,desp,source,Link;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.top_healine_title);
            desp=itemView.findViewById(R.id.top_headline_desp);
            source=itemView.findViewById(R.id.top_headline_source);
            Link=itemView.findViewById(R.id.Link);

        }
    }
}
