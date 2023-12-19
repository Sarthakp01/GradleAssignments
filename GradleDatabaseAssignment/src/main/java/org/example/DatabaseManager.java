package org.example;// DatabaseManager.java
import com.accolite.model.DataModel;
import com.accolite.pdf.PdfGenerator;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import org.jfree.data.general.DefaultPieDataset;

import java.util.List;

public class DatabaseManager {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/sarthak_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Acc0@user";
    private static final String TABLE_NAME = "Accolite_Data"; // Table name to be created

    private static final BasicDataSource dataSource = new BasicDataSource();

    static {
        dataSource.setUrl(JDBC_URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
    }

    public static void createTable() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            String createTableQuery = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    "id INT AUTO_INCREMENT PRIMARY KEY," +
                    "date VARCHAR(255)," +
                    "month VARCHAR(255)," +
                    "team VARCHAR(255)," +
                    "panelName VARCHAR(255)," +
                    "round VARCHAR(255)," +
                    "skill VARCHAR(255)," +
                    "time VARCHAR(255)," +
                    "candidateCurrentLoc VARCHAR(255)," +
                    "candidatePreferredLoc VARCHAR(255)," +
                    "candidateName VARCHAR(255)" +
                    ")";

            statement.executeUpdate(createTableQuery);

            System.out.println("Table '" + TABLE_NAME + "' created successfully.");

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static void insertData(List<DataModel> data) {
        data.parallelStream().forEach(record -> {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO " + TABLE_NAME + " (date, month, team, panelName, round, skill, time, " +
                                 "candidateCurrentLoc, candidatePreferredLoc, candidateName) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

                preparedStatement.setString(1, record.getDate());
                preparedStatement.setString(2, record.getMonth());
                preparedStatement.setString(3, record.getTeam());
                preparedStatement.setString(4, record.getPanelName());
                preparedStatement.setString(5, record.getRound());
                preparedStatement.setString(6, record.getSkill());
                preparedStatement.setString(7, record.getTime());
                preparedStatement.setString(8, record.getCandidateCurrentLoc());
                preparedStatement.setString(9, record.getCandidatePreferredLoc());
                preparedStatement.setString(10, record.getCandidateName());

                // Add other parameters as needed, ensuring to check for null values

                // Execute the query
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                //e.printStackTrace();
            } catch (Exception e) {
                //e.printStackTrace();
            }
        });
    }
    public static void teamWithMaxInterviews() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            // Query to find the team with the maximum number of interviews for October and November 2023
            String query = "SELECT team, COUNT(*) as interviewCount\n" +
                    "FROM accolite_data\n" +
                    "WHERE month IN ('Oct-23', 'Nov-23')\n" +
                    "GROUP BY team\n" +
                    "ORDER BY interviewCount DESC\n" +
                    "LIMIT 1;";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Team with Maximum Interviews: " + resultSet.getString("team"));
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static void teamWithMinInterviews() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            // Query to find the team with the minimum number of interviews for October and November 2023
            String query = "SELECT team, COUNT(*) as interviewCount\n" +
                    "FROM accolite_data\n" +
                    "WHERE month IN ('Oct-23', 'Nov-23')\n" +
                    "GROUP BY team\n" +
                    "ORDER BY interviewCount ASC\n" +
                    "LIMIT 1;";

            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                System.out.println("Team with Minimum Interviews: " + resultSet.getString("team"));
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static void top3Panels() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            // Query to find the top 3 panels for the month of October and November 2023 using lambda streams
            String query = "SELECT panelName, COUNT(*) as interviewCount\n" +
                    "FROM accolite_data\n" +
                    "WHERE month IN ('Oct-23', 'Nov-23')\n" +
                    "GROUP BY panelName\n" +
                    "ORDER BY interviewCount DESC\n" +
                    "LIMIT 3;";

            ResultSet resultSet = statement.executeQuery(query);
            DefaultPieDataset dataset = new DefaultPieDataset();
            while (resultSet.next()) {
                dataset.setValue(resultSet.getString("panelName"), resultSet.getInt("interviewCount") );
                System.out.println("Panel: " + resultSet.getString("panelName") +
                        ", Interview Count: " + resultSet.getInt("interviewCount"));
            }

            PdfGenerator.createPanelPdf(dataset,"C:\\Users\\sarthak.parande\\GradleDatabaseAssignment\\output\\Top3Panels.pdf");

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static void top3Skills() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            // Query to find the top 3 skills for the month of October and November 2023 using a view on the database
            String query = "CREATE OR REPLACE VIEW top_skills_view AS\n" +
                    "SELECT skill, COUNT(*) as interviewCount\n" +
                    "FROM accolite_data\n" +
                    "WHERE month IN ('Oct-23', 'Nov-23')\n" +
                    "GROUP BY skill\n" +
                    "ORDER BY interviewCount DESC\n" +
                    "LIMIT 3;\n" +
                    "\n";
            statement.executeUpdate(query);
            String Query2 = "SELECT * FROM top_skills_view;";
            ResultSet resultSet = statement.executeQuery(Query2);
            DefaultPieDataset dataset = new DefaultPieDataset();
            while (resultSet.next()) {
                dataset.setValue(resultSet.getString("skill"),resultSet.getInt("interviewCount"));
                System.out.println("Skill: " + resultSet.getString("skill") +
                        ", Interview Count: " + resultSet.getInt("interviewCount"));
            }
            String path = "C:\\Users\\sarthak.parande\\GradleDatabaseAssignment\\output\\Top3Skills.pdf";
            PdfGenerator.generateNewPdf(path);
            PdfGenerator.createSkillsPdf(dataset,path);

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }

    public static void skillsInPeakTime() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            // Query to find the top 3 skills for which the interviews were conducted in the Peak Time
            String query = "SELECT skill, COUNT(*) as interviewCount\n" +
                    "FROM accolite_data\n" +
                    "WHERE time = 'Peak Time'\n" +
                    "GROUP BY skill\n" +
                    "ORDER BY interviewCount DESC\n" +
                    "LIMIT 3;";

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                System.out.println("Skill in Peak Time: " + resultSet.getString("skill") +
                        ", Interview Count: " + resultSet.getInt("interviewCount"));
            }

        } catch (SQLException e) {
            //e.printStackTrace();
        }
    }
}