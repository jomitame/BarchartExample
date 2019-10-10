package com.dream71.android.barchartexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.dream71.android.barchartexample.api.ApiClient;
import com.dream71.android.barchartexample.api.ApiInterface;
import com.dream71.android.barchartexample.pojo.PiChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BarchartRetrofit extends AppCompatActivity {


    public ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

        getData();

    }


    private void getData() {
        Call<PiChart> piChartCall = apiInterface.init();
        piChartCall.enqueue(new Callback<PiChart>() {
            @Override
            public void onResponse(Call<PiChart> call, Response<PiChart> response) {
                Log.d("CHART_RESPONSE", "" + response.body().getBarMonths().toString());
                setData(response.body());
            }

            @Override
            public void onFailure(Call<PiChart> call, Throwable t) {

            }
        });
    }


    private void setData(PiChart piChart) {

        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> completed = new ArrayList<>();

        for (int i = 0; i < piChart.getBarCompleted().size(); i++) {
            BarEntry value = new BarEntry(piChart.getBarCompleted().get(i), i); // Jan
            completed.add(value);
        }

        BarDataSet completeData = new BarDataSet(completed, "Completed Issue");
        completeData.setColor(Color.rgb(0, 155, 0));


        ArrayList<BarEntry> pending = new ArrayList<>();

        for (int i = 0; i < piChart.getBarCompleted().size(); i++) {
            BarEntry value = new BarEntry(piChart.getBarPending().get(i), i); // Jan
            pending.add(value);
        }

        BarDataSet pendingdata = new BarDataSet(pending, "Pending Issue");
        pendingdata.setColor(Color.rgb(253, 129, 0));


        ArrayList<BarEntry> rejected = new ArrayList<>();

        for (int i = 0; i < piChart.getBarCompleted().size(); i++) {
            BarEntry value = new BarEntry(piChart.getBarRejected().get(i), i); // Jan
            rejected.add(value);
        }

        BarDataSet rejectedData = new BarDataSet(pending, "Rejected Issue");
        pendingdata.setColor(Color.rgb(255, 0, 0));



        dataSets = new ArrayList<>();
        dataSets.add(completeData);
        dataSets.add(pendingdata);
        dataSets.add(rejectedData);


        ArrayList<String> xAxis = new ArrayList<>();
        for (String months : piChart.getBarMonths()) {
            Log.d("CHART_RESPONSE", "month: " + months.toString());
            xAxis.add(months);
        }

        com.github.mikephil.charting.charts.BarChart chart = (com.github.mikephil.charting.charts.BarChart) findViewById(R.id.barchart);

        BarData data = new BarData(xAxis, dataSets);

        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }
}
