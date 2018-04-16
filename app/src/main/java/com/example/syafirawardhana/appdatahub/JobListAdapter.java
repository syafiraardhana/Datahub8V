package com.example.syafirawardhana.appdatahub;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.syafirawardhana.appdatahub.apihelper.model.SurveyModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syafira W Ardhana on 29/03/2018.
 */

public class JobListAdapter extends RecyclerView.Adapter<JobListAdapter.ItemViewHolder> {
    private Context mContext;
    private List<SurveyModel> dataSurvey = new ArrayList<>();
    private String sessionId;

    public JobListAdapter(Context context, String sessionId) {
        this.mContext = context;
        this.sessionId = sessionId;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        final SurveyModel job = dataSurvey.get(position);

        holder.tvJobName.setText(job.name);
        holder.tvDescription.setText(job.description);
        holder.tvStatus.setText(job.status);
        holder.tvIdJob.setText(job.id);
        holder.layRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Memiliki Survey ID : " + job.id, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("surveyId", job.id);
                intent.putExtra("sessionId", sessionId);
                mContext.startActivity(intent);
            }
        });
    }

    public void setData(List<SurveyModel> data) {
        this.dataSurvey = data;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return dataSurvey.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvJobName;
        TextView tvDescription;
        TextView tvStatus;
        TextView tvIdJob;
        LinearLayout layRoot;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvJobName = itemView.findViewById(R.id.tvNamaJob);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            tvIdJob = itemView.findViewById(R.id.tvIdJob);
            layRoot = itemView.findViewById(R.id.lay_root);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
