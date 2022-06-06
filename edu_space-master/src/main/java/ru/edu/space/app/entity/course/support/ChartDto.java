package ru.edu.space.app.entity.course.support;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public class ChartDto {

    private List<List<String>> chartData;

    public List<List<String>> getChartData() {
        return chartData;
    }

    @JsonProperty("data")
    public void setChartData(List<List<String>> chartData) {
        this.chartData = chartData;
    }
}
