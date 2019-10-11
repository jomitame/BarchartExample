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


    /**
     *  Api call for retriving data for pie chart
     */
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


    /**
     *  Set Data Into the pie chart
     * @param piChart
     */

    private void setData(PiChart piChart) {

        PieChart pieChartList = findViewById(R.id.piechart);


        /**
         *  get Data for pie chart
         */

        ArrayList pieChartData = new ArrayList();
        pieChartData.add(new Entry(piChart.getPieStatistics().getAssigned(), 0));
        pieChartData.add(new Entry(piChart.getPieStatistics().getOpened(), 1));
        pieChartData.add(new Entry(piChart.getPieStatistics().getInProgress(), 2));
        pieChartData.add(new Entry(piChart.getPieStatistics().getCompleted(), 3));
        pieChartData.add(new Entry(piChart.getPieStatistics().getDone(), 4));


        PieDataSet dataSet = new PieDataSet(pieChartData, "");
        dataSet.setValueTextSize(14);


        /**
         *  set segment for pie chart
         */

        ArrayList pieChartSectionName = new ArrayList();
        pieChartSectionName.add("Assigned");
        pieChartSectionName.add("Opened");
        pieChartSectionName.add("Completed");
        pieChartSectionName.add("Done");
        pieChartSectionName.add("Rejected");


        PieData data = new PieData(pieChartSectionName, dataSet);
        pieChartList.setData(data);


        /**
         *  Segment color added into the pie chart
         */

        dataSet.setColors(new int[]{Color.parseColor("#9c27b0"),
                Color.parseColor("#ef5350"),
                Color.parseColor("#ff5252"),
                Color.parseColor("#2196f3"),
                Color.parseColor("#7cb342")});



        pieChartList.animateXY(500, 500);

    }

}
