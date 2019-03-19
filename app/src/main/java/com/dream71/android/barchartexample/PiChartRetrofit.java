package com.dream71.android.barchartexample;

import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.dream71.android.barchartexample.api.ApiClient;
import com.dream71.android.barchartexample.api.ApiInterface;
import com.dream71.android.barchartexample.pojo.PiChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

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
        Call<PiChart> piChartCall = apiInterface.init("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijc4MzRjZDk3MDczYmYxN2Q3Y2VmNDRhNDE5NDJmNzMxYTYxYmRjNzQwOTVmZDk0MjNkMzU1NDdmMjAyMWEzMzMxZTZmODZiM2RlMTM2ZTUzIn0.eyJhdWQiOiIxIiwianRpIjoiNzgzNGNkOTcwNzNiZjE3ZDdjZWY0NGE0MTk0MmY3MzFhNjFiZGM3NDA5NWZkOTQyM2QzNTU0N2YyMDIxYTMzMzFlNmY4NmIzZGUxMzZlNTMiLCJpYXQiOjE1NTI5NzkzMjksIm5iZiI6MTU1Mjk3OTMyOSwiZXhwIjoxNTg0NjAxNzI5LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.bBt0plE7WElpV1MRYpfzko4fHYumIXmqYd6BRaHI4xGa6GHeS_bnc4Li-X9FvC0A0AMArLd0kuXDjqVOtlAEsj6C-kZ8QI-zkyCAYSp8Mjh3h1WVewOX4RBvk3oLH0Xr9oka7mqcLFLn0Mx9ETw8XEAVBLIH9QouJySymQ4X5h4vhaQb-1mAggk4urKBQRsGkwD7JYrGEmAlEq6pnnbnT_GHz2CI3dxr6OQyVPukrYC24PbimLLYeFdVF6C3HSoS45rsf08Kr-j3ChNUeLhg0BMNf8Ey7bXdKHJOJug43Co7ir-lYQioMrbLjfcaQsfsRPT1Ik1fDXpf4F8GRgwyncazc71Y7LPbQV3_8pGaqFFPz__q-ytxUnlS7mP1k0XMiqMiDXfq_uJCpfCgfn7z7bBAf1Fr5comU_GBw7y3Kh0qkAsT4bFejNGQfvx4BXN0Z1Vkkm0gqzflXQW3O3WqP27nx6CrmPzxzXuk0sSaKa00XxHPK5IC9u_2ye6OYj2D0CUbk0pqPVINAfYwyJVv3A_lf_drX0YU5J8UOgNVlZUFjN9WqVToxHN-Qs-Q8qORPd_UJuL4DQYUp0k5nowo1-hz6Ulx8Vt-LDtsjakBsXD81p9Tj5aw2a-U7rcl57aubtSldB1HY-tKA1mBUADm1a7ARyPEdPNQA8flJjDZJOE");
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
        //NoOfEmp.add(new Entry(piChart.getPieStatistics().getInProgress(), 2));
        NoOfEmp.add(new Entry(piChart.getPieStatistics().getCompleted(), 3));
        NoOfEmp.add(new Entry(piChart.getPieStatistics().getDone(), 4));
        NoOfEmp.add(new Entry(piChart.getPieStatistics().getRejected(), 5));
        //NoOfEmp.add(new Entry(piChart.getPieStatistics().getExpired(), 6));
        NoOfEmp.add(new Entry(5, 6));
        NoOfEmp.add(new Entry(3, 2));


        PieDataSet dataSet = new PieDataSet(NoOfEmp, "Issues");
        dataSet.setValueTextSize(14);


        ArrayList year = new ArrayList();
        year.add("Assigned");
        year.add("Opened");
        year.add("In progress");
        year.add("Completed");
        year.add("Done");
        year.add("Rejected");
        year.add("Expired");


        PieData data = new PieData(year, dataSet);
        pieChartList.setData(data);

        /*
        dataSet.setColors(new int[]{Color.parseColor("#9c27b0"),
                Color.parseColor("#ef5350"),
                Color.parseColor("#ff5252"),
                Color.parseColor("#2196f3"),
                Color.parseColor("#009688"),
                Color.parseColor("#29b6f6"),
                Color.parseColor("#7cb342")});
                */
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        pieChartList.animateXY(1000, 1000);

    }

}
