package com.dream71.android.barchartexample;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.dream71.android.barchartexample.api.ApiClient;
import com.dream71.android.barchartexample.api.ApiInterface;
import com.dream71.android.barchartexample.pojo.PiChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PiChartRetrofit extends AppCompatActivity {

    public ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pi_chart);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        apiCall();

    }


    private void apiCall() {
        Call<PiChart> piChartCall = apiInterface.init();
        piChartCall.enqueue(new Callback<PiChart>() {
            @Override
            public void onResponse(Call<PiChart> call, Response<PiChart> response) {
                setData(response.body());
            }

            @Override
            public void onFailure(Call<PiChart> call, Throwable t) {

            }
        });
    }

    private void setData(PiChart piChart) {

        PieChart pieChartList = findViewById(R.id.piechart);

        ArrayList NoOfEmp = new ArrayList();


        NoOfEmp.add(new Entry(piChart.getPieStatistics().getAssigned(), 0));
        NoOfEmp.add(new Entry(piChart.getPieStatistics().getOpened(), 1));
        NoOfEmp.add(new Entry(piChart.getPieStatistics().getInProgress(), 2));
        NoOfEmp.add(new Entry(piChart.getPieStatistics().getCompleted(), 3));
        NoOfEmp.add(new Entry(piChart.getPieStatistics().getDone(), 4));


        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Issues");
        dataSet.setValueTextSize(14);


        ArrayList chartData = new ArrayList();
        chartData.add("Assigned");
        chartData.add("Opened");
        chartData.add("Completed");
        chartData.add("Done");
        chartData.add("Rejected");


        PieData data = new PieData(chartData, dataSet);
        pieChartList.setData(data);


        dataSet.setColors(new int[]{Color.parseColor("#9c27b0"),
                Color.parseColor("#ef5350"),
                Color.parseColor("#ff5252"),
                Color.parseColor("#2196f3"),
                Color.parseColor("#7cb342")});

        //dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChartList.animateXY(500, 500);

    }

}
