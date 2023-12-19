package com.accolite.chart;
import com.accolite.model.DataModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ChartGenerator {
    public static JFreeChart createChart(List<DataModel> data) {

        CategoryDataset dataset = createDataset(data);
        JFreeChart chart = ChartFactory.createBarChart(
                "Interviews by Team",
                "Team",
                "Number of Interviews",
                dataset,
                PlotOrientation.HORIZONTAL,
                true,
                true,
                false
        );

        return chart;
    }

    private static CategoryDataset createDataset(List<DataModel> data) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Example: Count interviews by team
        Map<String, Long> teamInterviewCounts = data.stream()
                .collect(Collectors.groupingBy(DataModel::getTeam, Collectors.counting()));

        teamInterviewCounts.forEach((team, count) -> {
            dataset.addValue(count, "Interviews", team);
        });

        return dataset;
    }

    public static JFreeChart createPieChart(String title,DefaultPieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart(
                title,   // chart title
                dataset,          // data
                true,             // include legend
                true,
                false);

        int width = 8000;
        int height = 480;
        return chart;
    }
}
