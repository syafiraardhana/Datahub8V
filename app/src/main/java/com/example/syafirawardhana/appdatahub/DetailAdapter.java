package com.example.syafirawardhana.appdatahub;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.syafirawardhana.appdatahub.apihelper.model.DetailModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Syafira W Ardhana on 09/04/2018.
 */

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ItemViewHolder>{
    private Context mContext;
    private List<DetailModel> data = new ArrayList<>();
    private String surveyId;

    public DetailAdapter(Context context, ArrayList<DetailModel> data, String surveyId){
        this.mContext = context;
        this.data = data;
        this.surveyId =  surveyId;
    }

    @Override
    public ItemViewHolder onCreateViewHolder (ViewGroup parent , int ViewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_item, parent, false);
        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder (final ItemViewHolder holder , int position){
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
        DetailModel detail = data.get(position);
        holder.tvIdReport.setText(detail.id);
        holder.tvUuidSurvey.setText(detail.uuid);
        holder.tvSurveyId.setText(detail.surveyId);
        holder.tvIdSurveyor.setText(detail.surveyorId);
        holder.tvUserId.setText(detail.userId);
        holder.tvJobId.setText(detail.jobId);
        holder.tvTimezone.setText(detail.timezone);
        holder.tvStatusReport.setText(detail.status);
        holder.tvCreatedDate.setText(detail.createdDate);
        holder.tvCreatedAt.setText(detail.createdAt);
        holder.tvUpdateAt.setText(detail.updatedAt);
//        holder.tvQuestionsAnswers.setText(detail.questionsAnwersText());
    }

    @Override
    public int getItemCount() {return data.size();}

    public void setData(List<DetailModel> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvIdReport,tvUuidSurvey,tvSurveyId,tvIdSurveyor,tvUserId,tvJobId;
        TextView tvTimezone,tvStatusReport,tvCreatedDate,tvCreatedAt,tvUpdateAt,tvQuestionsAnswers;

        public ItemViewHolder(View itemView) {
            super(itemView);
            tvIdReport = itemView.findViewById(R.id.tvIdReport);
            tvUuidSurvey = itemView.findViewById(R.id.tvUuidSurvey);
            tvSurveyId = itemView.findViewById(R.id.tvSurveyId);
            tvIdSurveyor = itemView.findViewById(R.id.tvIdSurveyor);
            tvUserId = itemView.findViewById(R.id.tvUserId);
            tvJobId = itemView.findViewById(R.id.tvJobId);
            tvTimezone = itemView.findViewById(R.id.tvTimezone);
            tvStatusReport = itemView.findViewById(R.id.tvStatusReport);
            tvCreatedDate = itemView.findViewById(R.id.tvCreatedDate);
            tvCreatedAt = itemView.findViewById(R.id.tvCreatedAt);
            tvUpdateAt = itemView.findViewById(R.id.tvUpdateAt);
//            tvQuestionsAnswers = itemView.findViewById(R.id.tvQuestionsAnswer);
        }
        @Override
        public void onClick(View view) {}
    }
}
