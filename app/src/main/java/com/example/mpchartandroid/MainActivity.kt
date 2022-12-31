package com.example.mpchartandroid

import android.graphics.Canvas
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.animation.ChartAnimator
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.renderer.BarChartRenderer
import com.github.mikephil.charting.utils.ViewPortHandler


class MainActivity : AppCompatActivity() {

    // on below line we are creating
    // variables for our bar chart
    private lateinit var barChart: BarChart

    // on below line we are creating
    // a variable for bar data
    private lateinit var barData: BarData

    // on below line we are creating a
    // variable for bar data set
    private lateinit var barDataSet: BarDataSet

    // on below line we are creating array list for bar data
    private lateinit var barEntriesList: ArrayList<BarEntry>
    private var barLabels = mutableListOf<String>("")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // on below line we are initializing
        // our variable with their ids.
        barChart = findViewById(R.id.bc_data_mainActivity)
        barChart.renderer =
            CustomBarRenderer(barChart, barChart.animator, barChart.viewPortHandler, 10f)

        // on below line we are calling get bar
        // chart data to add data to our array list
        getBarChartData()

        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(barEntriesList, "Bar Chart Data")
        // on below line we are initializing our bar data
        barData = BarData(barDataSet)

        // on below line we are setting data to our bar chart
        barChart.data = barData

        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = Color.BLACK

        // on below line we are setting color for our bar data set
        barDataSet.color = resources.getColor(R.color.cyan_200)

        // on below line we are setting text size
        barDataSet.valueTextSize = 16f

        // on below line we are enabling description as false
        barChart.description.isEnabled = false

        // disable zoom
        barChart.setScaleEnabled(false)


        val xAxis = barChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.valueFormatter = IndexAxisValueFormatter(barLabels)

        val value = barChart.axisLeft
        value.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART)
    }

    private fun getBarChartData() {
        barEntriesList = ArrayList()

        // on below li``ne we are adding data
        // to our bar entries list
        addEntry(1f, 224f, "مهر")
        addEntry(2f, 2452f, "آبان")
        addEntry(3f, 2453f, "آذر")
        addEntry(4f, 254f, "دی")
        addEntry(5f, 325f, "بهمن")
        addEntry(6f, 525f, "بهمن")
        addEntry(7f, 525f, "بهمن")
    }

    private fun addEntry(xFloat: Float, yFloat: Float, title: String) {
        barEntriesList.add(BarEntry(xFloat, yFloat))
        barLabels.add(title)
    }
}

class CustomBarRenderer constructor(
    chart: BarChart,
    animator: ChartAnimator,
    vpHandler: ViewPortHandler,
     cornerDimens: Float
) : BarChartRenderer(chart, animator, vpHandler) {

    override fun drawDataSet(c: Canvas, dataSet: IBarDataSet, index: Int) {

        for (j in 0 until mBarBuffers[index].size()) {
            if (j % 4 == 0) {
                c.drawRoundRect(
                    mBarBuffers[index].buffer[j],
                    mBarBuffers[index].buffer[j + 1],
                    mBarBuffers[index].buffer[j + 2],
                    mBarBuffers[index].buffer[j + 3],
                    12f,
                    12f,
                    mRenderPaint
                )
            }
        }

    }
}