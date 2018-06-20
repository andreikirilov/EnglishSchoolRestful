package englishschoolrestful;

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

class JavaToMySQL {

    private static final String url = "jdbc:mysql://localhost:3306/englishschool?useSSL=false&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String user = "root";
    private static final String password = "mysql";
    private static final String table = "classes";
    private static final String table2 = "students";

    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;

    static String selAllClass(String name) {
        String query = (name.equals("")) ? "SELECT * FROM " + table + ";" : "SELECT * FROM " + table + " WHERE studentName = '" + name + "';";
        System.out.println("QUERY: " + query);
        StringBuilder stringBuilder = new StringBuilder();
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            String ID = "ID";
            String STUDENT_NAME = "Имя ученика";
            String LESSON_DATE = "Дата занятия";
            String LESSON_TOPIC = "Тема занятия";
            String LESSON_WORK = "Домашняя работа";
            String LESSON_MARK = "Итоговый результат";
            int maxRecordId = ID.length();
            int maxStudentName = STUDENT_NAME.length();
            int maxStudentLessonDate = LESSON_DATE.length();
            int maxStudentLessonTopic = LESSON_TOPIC.length();
            int maxStudentLessonHomework = LESSON_WORK.length();
            int maxStudentLessonMark = LESSON_MARK.length();
            ArrayList<String[]> results = new ArrayList<>();
            while (resultSet.next()) {
                String recordId = resultSet.getString(1);
                String studentName = resultSet.getString(2);
                String studentLessonDate = resultSet.getString(3);
                String studentLessonTopic = resultSet.getString(4);
                String studentLessonHomework = resultSet.getString(5);
                String studentLessonMark = resultSet.getString(6);
                String[] strings = {recordId, studentName, studentLessonDate, studentLessonTopic, studentLessonHomework, studentLessonMark};
                results.add(strings);
                if (recordId.length() > maxRecordId) maxRecordId = recordId.length();
                if (studentName.length() > maxStudentName) maxStudentName = studentName.length();
                if (studentLessonDate.length() > maxStudentLessonDate)
                    maxStudentLessonDate = studentLessonDate.length();
                if (studentLessonTopic.length() > maxStudentLessonTopic)
                    maxStudentLessonTopic = studentLessonTopic.length();
                if (studentLessonHomework.length() > maxStudentLessonHomework)
                    maxStudentLessonHomework = studentLessonHomework.length();
                if (studentLessonMark.length() > maxStudentLessonMark)
                    maxStudentLessonMark = studentLessonMark.length();
            }
            int lineLength = maxRecordId + maxStudentName + maxStudentLessonDate + maxStudentLessonTopic + maxStudentLessonHomework + maxStudentLessonMark + 19;
            StringBuilder line = new StringBuilder();
            for (int i = 0; i < lineLength; i++) {
                line.append("-");
            }
            stringBuilder.append(line + "\n");
            stringBuilder.append(String.format("| %" + maxRecordId + "s | %" + maxStudentName + "s | %" + maxStudentLessonDate + "s | %" + maxStudentLessonTopic + "s | %" + maxStudentLessonHomework + "s | %" + maxStudentLessonMark + "s |\n", ID, STUDENT_NAME, LESSON_DATE, LESSON_TOPIC, LESSON_WORK, LESSON_MARK));
            stringBuilder.append(line + "\n");
            for (int i = 0; i < results.size(); i++) {
                stringBuilder.append(String.format("| %" + maxRecordId + "s | %" + maxStudentName + "s | %" + maxStudentLessonDate + "s | %" + maxStudentLessonTopic + "s | %" + maxStudentLessonHomework + "s | %" + maxStudentLessonMark + "s |\n", results.get(i)[0], results.get(i)[1], results.get(i)[2], results.get(i)[3], results.get(i)[4], results.get(i)[5]));
            }
            stringBuilder.append(line);
        } catch (SQLException ignored) {
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException ignored) {
            }
        }
        return stringBuilder.toString();
    }

    static void addStudent(Students student) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(student.getPass().getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest);
        StringBuilder md5Hex = new StringBuilder(bigInt.toString(16));
        while (md5Hex.length() < 32) {
            md5Hex.insert(0, "0");
        }
        String values = student.getName() + "', '" + md5Hex.toString() + "', '" + student.getLevel();
        String query = "INSERT INTO " + table2 + " (studentName, studentPass, studentLevel) VALUES ('" + values + "');";
        System.out.println("QUERY: " + query);
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ignored) {
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException ignored) {
            }
        }
    }

    static void insClass(Classes classes) {
        String values = classes.getName() + "', '" + classes.getDate() + "', '" + classes.getTopic() + "', '" + classes.getHomework() + "', '" + classes.getMark();
        String query = "INSERT INTO " + table + " (studentName, studentLessonDate, studentLessonTopic, studentLessonHomework, studentLessonMark) VALUES ('" + values + "');";
        System.out.println("QUERY: " + query);
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ignored) {
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException ignored) {
            }
        }
    }

    static void updClass(Classes classes) {
        String values = "studentName = '" + classes.getName() + "', studentLessonDate = '" + classes.getDate() + "', studentLessonTopic = '" + classes.getTopic() + "', studentLessonHomework = '" + classes.getHomework() + "', studentLessonMark = '" + classes.getMark() + "'";
        String query = "UPDATE " + table + " SET " + values + " WHERE recordId = " + classes.getID() + ";";
        System.out.println("QUERY: " + query);
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ignored) {
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException ignored) {
            }
        }
    }

    static void delClass(Classes classes) {
        String query = "DELETE FROM " + table + " WHERE recordId = " + classes.getID() + ";";
        System.out.println("QUERY: " + query);
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            statement.executeUpdate(query);
        } catch (SQLException ignored) {
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
            } catch (SQLException ignored) {
            }
        }
    }

    static String checkUserPass(String name, String pass) {
        String query = "SELECT studentId FROM " + table2 + " WHERE studentName = '" + name + "' AND studentPass = '" + pass + "';";
        System.out.println("QUERY: " + query);
        String restResult = "failure";
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                if (resultSet.getString(1) != null) {
                    restResult = "success";
                }
            }
        } catch (SQLException ignored) {
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException ignored) {
            }
        }
        return restResult;
    }

    static JSONArray selectAllInfo(String name) {
        String query = "SELECT * FROM " + table + " WHERE studentName = '" + name + "';";
        System.out.println("QUERY: " + query);
        JSONArray results = new JSONArray();
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                String recordId = resultSet.getString(1);
                String studentName = resultSet.getString(2);
                String studentLessonDate = resultSet.getString(3);
                String studentLessonTopic = resultSet.getString(4);
                String studentLessonHomework = resultSet.getString(5);
                String studentLessonMark = resultSet.getString(6);
                results.put(new JSONObject(new Classes(recordId, studentName, studentLessonDate, studentLessonTopic, studentLessonHomework, studentLessonMark)));
            }
        } catch (SQLException ignored) {
        } finally {
            try {
                if (connection != null) connection.close();
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
            } catch (SQLException ignored) {
            }
        }
        return results;
    }
}