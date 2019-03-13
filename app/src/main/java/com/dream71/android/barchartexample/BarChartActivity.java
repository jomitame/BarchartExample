package com.dream71.android.barchartexample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        com.github.mikephil.charting.charts.BarChart chart = (com.github.mikephil.charting.charts.BarChart) findViewById(R.id.barchart);

        BarData data = new BarData(getXAxisValues(), getDataSet());

        chart.setData(data);
        chart.setDescription("My Chart");
        chart.animateXY(2000, 2000);
        chart.invalidate();

    }


    private ArrayList<BarDataSet> getDataSet() {
        ArrayList<BarDataSet> dataSets = null;

        ArrayList<BarEntry> valueSet1 = new ArrayList<>();
        BarEntry v1e1 = new BarEntry(110.000f, 0); // Jan
        valueSet1.add(v1e1);
        BarEntry v1e2 = new BarEntry(40.000f, 1); // Feb
        valueSet1.add(v1e2);
        BarEntry v1e3 = new BarEntry(60.000f, 2); // Mar
        valueSet1.add(v1e3);
        BarEntry v1e4 = new BarEntry(30.000f, 3); // Apr
        valueSet1.add(v1e4);
        BarEntry v1e5 = new BarEntry(90.000f, 4); // May
        valueSet1.add(v1e5);
        BarEntry v1e6 = new BarEntry(100.000f, 5); // Jun
        valueSet1.add(v1e6);

        ArrayList<BarEntry> valueSet2 = new ArrayList<>();
        BarEntry v2e1 = new BarEntry(150.000f, 0); // Jan
        valueSet2.add(v2e1);
        BarEntry v2e2 = new BarEntry(90.000f, 1); // Feb
        valueSet2.add(v2e2);
        BarEntry v2e3 = new BarEntry(120.000f, 2); // Mar
        valueSet2.add(v2e3);
        BarEntry v2e4 = new BarEntry(60.000f, 3); // Apr
        valueSet2.add(v2e4);
        BarEntry v2e5 = new BarEntry(20.000f, 4); // May
        valueSet2.add(v2e5);
        BarEntry v2e6 = new BarEntry(80.000f, 5); // Jun
        valueSet2.add(v2e6);


        ArrayList<BarEntry> valueSet3 = new ArrayList<>();
        BarEntry v1vv1 = new BarEntry(110.000f, 0); // Jan
        valueSet3.add(v1vv1);
        BarEntry v1vv2 = new BarEntry(40.000f, 1); // Feb
        valueSet3.add(v1vv2);
        BarEntry v1vv3 = new BarEntry(60.000f, 2); // Mar
        valueSet3.add(v1vv3);
        BarEntry v1vv4 = new BarEntry(30.000f, 3); // Apr
        valueSet3.add(v1vv4);
        BarEntry v1vv5 = new BarEntry(90.000f, 4); // May
        valueSet3.add(v1vv5);
        BarEntry v1vv6 = new BarEntry(100.000f, 5); // Jun
        valueSet3.add(v1vv6);

        BarDataSet barDataSet1 = new BarDataSet(valueSet1, "Pending Issue");
        barDataSet1.setColor(Color.rgb(0, 155, 0));

        BarDataSet barDataSet2 = new BarDataSet(valueSet2, "Rejected Issue");
        barDataSet2.setColors(ColorTemplate.COLORFUL_COLORS);

        BarDataSet barDataSet3 = new BarDataSet(valueSet2, "Approved Issue");
        barDataSet3.setColors(ColorTemplate.JOYFUL_COLORS);

        dataSets = new ArrayList<>();
        dataSets.add(barDataSet1);
        dataSets.add(barDataSet2);
        dataSets.add(barDataSet3);

        return dataSets;
    }

    private ArrayList<String> getXAxisValues() {
        ArrayList<String> xAxis = new ArrayList<>();
        xAxis.add("JAN");
        xAxis.add("FEB");
        xAxis.add("MAR");
        xAxis.add("APR");
        xAxis.add("MAY");
        xAxis.add("JUN");
        return xAxis;
    }

}
