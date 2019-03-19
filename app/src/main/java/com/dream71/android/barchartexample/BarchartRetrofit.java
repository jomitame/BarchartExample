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
        Call<PiChart> piChartCall = apiInterface.init("Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6Ijc4MzRjZDk3MDczYmYxN2Q3Y2VmNDRhNDE5NDJmNzMxYTYxYmRjNzQwOTVmZDk0MjNkMzU1NDdmMjAyMWEzMzMxZTZmODZiM2RlMTM2ZTUzIn0.eyJhdWQiOiIxIiwianRpIjoiNzgzNGNkOTcwNzNiZjE3ZDdjZWY0NGE0MTk0MmY3MzFhNjFiZGM3NDA5NWZkOTQyM2QzNTU0N2YyMDIxYTMzMzFlNmY4NmIzZGUxMzZlNTMiLCJpYXQiOjE1NTI5NzkzMjksIm5iZiI6MTU1Mjk3OTMyOSwiZXhwIjoxNTg0NjAxNzI5LCJzdWIiOiIxIiwic2NvcGVzIjpbXX0.bBt0plE7WElpV1MRYpfzko4fHYumIXmqYd6BRaHI4xGa6GHeS_bnc4Li-X9FvC0A0AMArLd0kuXDjqVOtlAEsj6C-kZ8QI-zkyCAYSp8Mjh3h1WVewOX4RBvk3oLH0Xr9oka7mqcLFLn0Mx9ETw8XEAVBLIH9QouJySymQ4X5h4vhaQb-1mAggk4urKBQRsGkwD7JYrGEmAlEq6pnnbnT_GHz2CI3dxr6OQyVPukrYC24PbimLLYeFdVF6C3HSoS45rsf08Kr-j3ChNUeLhg0BMNf8Ey7bXdKHJOJug43Co7ir-lYQioMrbLjfcaQsfsRPT1Ik1fDXpf4F8GRgwyncazc71Y7LPbQV3_8pGaqFFPz__q-ytxUnlS7mP1k0XMiqMiDXfq_uJCpfCgfn7z7bBAf1Fr5comU_GBw7y3Kh0qkAsT4bFejNGQfvx4BXN0Z1Vkkm0gqzflXQW3O3WqP27nx6CrmPzxzXuk0sSaKa00XxHPK5IC9u_2ye6OYj2D0CUbk0pqPVINAfYwyJVv3A_lf_drX0YU5J8UOgNVlZUFjN9WqVToxHN-Qs-Q8qORPd_UJuL4DQYUp0k5nowo1-hz6Ulx8Vt-LDtsjakBsXD81p9Tj5aw2a-U7rcl57aubtSldB1HY-tKA1mBUADm1a7ARyPEdPNQA8flJjDZJOE");
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
