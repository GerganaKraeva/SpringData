import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final DBTools DB_TOOLS = new DBTools("root", "root", "minions_db");

    private static final BufferedReader READER = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws SQLException, IOException {

        exercise_06();


    }

    private static void exercise_09() throws SQLException, IOException {

        CallableStatement callableStatement = DB_TOOLS.getConnection().prepareCall("CALL usp_get_older(?)");
        callableStatement.setInt(1, Integer.parseInt(READER.readLine()));

        ResultSet resultSet = callableStatement.executeQuery();
        resultSet.next();
        System.out.printf("%s %d\n", resultSet.getString(1), resultSet.getInt(2));


    }

    private static void exercise_08() throws IOException, SQLException {

        int[] id_minions = Arrays.stream((READER.readLine().split(" "))).mapToInt(Integer::parseInt).toArray();

        Arrays.stream(id_minions).forEach(id_minion -> {
            try {
                PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                        "UPDATE minions " +
                                "SET age=age+1, name=LOWER(name) " +
                                "WHERE id = ?");
                preparedStatement.setInt(1, id_minion);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });


        ResultSet resultSet = DB_TOOLS.getConnection().createStatement().executeQuery(
                "SELECT name, age " +
                        "FROM minions;"

        );
        while (resultSet.next()) {
            System.out.printf("%s %d\n",
                    resultSet.getString(1),
                    resultSet.getInt(2));
        }
    }


    private static void exercise_07() throws IOException, SQLException {
        ResultSet result = DB_TOOLS.getConnection().createStatement().executeQuery("SELECT name FROM minions");

//        ArrayDeque<String> names = new ArrayDeque<>();
//
//        while (result.next()) {
//            names.add(result.getString(1));
//        }
//        while (!names.isEmpty()) {
//            System.out.println(names.removeFirst());
//            if (!names.isEmpty()) {
//                System.out.println(names.removeLast());
//            }
//        }
        List<String> names = new ArrayList<>();

        while (result.next()) {
            names.add(result.getString(1));
        }
        while (names.size() != 0) {
            System.out.println(names.get(0));
            names.remove(0);

            if (names.size() != 0) {
                System.out.println(names.get(names.size() - 1));
                names.remove(names.size() - 1);
            }
        }

    }

    private static void exercise_06() throws IOException, SQLException {
        int villainId = Integer.parseInt(READER.readLine());
        String villainName = findVillainByName(villainId);
        if (villainName.isEmpty()) {
            System.out.println("No such villain was found");
        } else {
            int releasedMinionsCount = releasedMinions(villainId);
            deleteVillains(villainName);
            System.out.printf("%s was deleted%n%d minions released", villainName, releasedMinionsCount);
        }


    }

    private static void deleteVillains(String villainName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "DELETE FROM villains WHERE name = ?");
        preparedStatement.setString(1, villainName);
        preparedStatement.executeUpdate();
    }

    private static int releasedMinions(int villainId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "DELETE FROM minions_villains WHERE villain_id = ?");
        preparedStatement.setInt(1, villainId);
        return preparedStatement.executeUpdate();
    }

    private static String findVillainByName(int villainId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "SELECT name FROM villains WHERE id=?");
        preparedStatement.setInt(1, villainId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        return "";
    }

    private static void exercise_05() throws IOException, SQLException {
        String country = READER.readLine();
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "UPDATE  towns " +
                        "SET name = (name) " +
                        "WHERE country=?;");
        preparedStatement.setString(1, country);
        int i = preparedStatement.executeUpdate();

        if (i == 0) {
            System.out.println("No town names were affected.");
        } else {
            System.out.printf("%d town names were affected.\n", i);
            preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                    "SELECT name " +
                            "FROM towns " +
                            "WHERE COUNTRY =?;");
            preparedStatement.setString(1, country);
            ResultSet result = preparedStatement.executeQuery();
            List<String> towns = new ArrayList<>();

            while (result.next()) {
                towns.add(result.getString(1));
            }
            System.out.printf("[%s]", String.join(", ", towns));

        }


    }

    private static void exercise_04() throws IOException, SQLException {

        String[] minions = READER.readLine().split("\\s+");

        String minionName = minions[1];
        int minionAge = Integer.parseInt(minions[2]);
        String town = minions[3];
        String[] villains = READER.readLine().split("\\s+");
        String villainName = villains[1];

        int townId = getTownId(town);

        if (townId == 0) {
            townId = insertTown(town);
            System.out.printf("Town %s was added to the database.%n", town);
        }

        int idMinion = createMinion(minionName, minionAge, townId);

        int villainId = findVillainId(villainName);
        if (villainId == 0) {
            villainId = createVillain(villainName);
            System.out.printf("Villain %s was added to the database.%n", villainName);
        }
        setIdOfMinionToVillain(idMinion, villainId);
        System.out.printf("Successfully added %s to be minion of %s.", minionName, villainName);
    }

    private static void setIdOfMinionToVillain(int idMinion, int villainId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "INSERT INTO minions_villains VALUES(?,?)");
        preparedStatement.setInt(1, idMinion);
        preparedStatement.setInt(2, villainId);
        preparedStatement.executeUpdate();
    }

    private static int createVillain(String villainName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "INSERT INTO villains (name, evilness_factor) VALUES (?,'evil')");
        preparedStatement.setString(1, villainName);

        preparedStatement.executeUpdate();

        PreparedStatement preparedStatement1 = DB_TOOLS.getConnection().prepareStatement(
                "SELECT id " +
                        "FROM villains " +
                        "WHERE name=?");
        preparedStatement1.setString(1, villainName);
        ResultSet resultSet = preparedStatement1.executeQuery();
        if (resultSet.next()) ;

        return resultSet.getInt(1);


    }

    private static int findVillainId(String villainName) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "SELECT id " +
                        "FROM villains " +
                        "WHERE name = ?");
        preparedStatement.setString(1, villainName);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    private static int createMinion(String minionName, int minionAge, int townId) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "INSERT INTO minions(name, age, town_id) VALUES (?,?,?)");
        preparedStatement.setString(1, minionName);
        preparedStatement.setInt(2, minionAge);
        preparedStatement.setInt(3, townId);
        preparedStatement.executeUpdate();
        PreparedStatement preparedStatement1 = DB_TOOLS.getConnection().prepareStatement(
                "SELECT id " +
                        "FROM minions " +
                        "WHERE name = ?");
        preparedStatement1.setString(1, minionName);
        ResultSet resultSet = preparedStatement1.executeQuery();
        resultSet.next();

        return resultSet.getInt(1);

    }

    private static int insertTown(String town) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(
                "INSERT INTO towns(name) VALUES(?)");
        preparedStatement.setString(1, town);
        preparedStatement.executeUpdate();

        PreparedStatement preparedStatement1 = DB_TOOLS.getConnection().prepareStatement(
                "SELECT id " +
                        "FROM towns " +
                        "WHERE name = ?");
        preparedStatement1.setString(1, town);
        ResultSet resultSet = preparedStatement1.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    }

    private static int getTownId(String town) throws SQLException {
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement("" +
                "SELECT id " +
                "FROM towns " +
                "WHERE name= ?");
        preparedStatement.setString(1, town);
        PreparedStatement preparedStatement1 = preparedStatement;
        ResultSet resultSet = preparedStatement1.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        }
        return 0;
    }

    private static void exercise_03() throws IOException, SQLException {

        int villainId = Integer.parseInt(READER.readLine());
        String sql = "SELECT name FROM villains WHERE id= ?";
        PreparedStatement preparedStatement = DB_TOOLS.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, villainId);
        ResultSet result = preparedStatement.executeQuery();

        if (!result.next()) {
            System.out.printf("No villain with ID %d exists in database", villainId);
            return;
        }
        String villainName = result.getString(1);
        System.out.println("Villain " + villainName);

        sql = "SELECT m.name, m.age FROM minions AS m  " +
                "JOIN minions_villains AS mv " +
                "ON mv.minion_id=m.id " +
                " WHERE villain_id= ?;";
        preparedStatement = DB_TOOLS.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1, villainId);
        result = preparedStatement.executeQuery();

        int index = 0;
        while (result.next()) {
            System.out.printf("%d. %s %d",
                    ++index, result.getString(1),
                    result.getInt(2)).append(System.lineSeparator());
        }

    }

    public static void exercise_02() throws SQLException {

        String sql = "SELECT v.name, COUNT(mv.minion_id) AS `minions_count` " +
                "FROM villains AS v " +
                "JOIN minions_villains AS mv " +
                "ON mv.villain_id=v.id " +
                "GROUP BY v.name " +
                "HAVING `minions_count`>15 " +
                "ORDER BY `minions_count` DESC;";
        ResultSet result = DB_TOOLS.getConnection().createStatement().executeQuery(sql);

        while (result.next()) {
            System.out.printf("%s %d\n",
                    result.getString(1),
                    result.getLong(2));
        }
    }
}