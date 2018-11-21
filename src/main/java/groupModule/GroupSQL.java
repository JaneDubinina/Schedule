package groupModule;

        import java.sql.*;
        import java.util.ArrayList;
        import java.util.HashMap;
        import java.util.List;
        import java.util.Map;

public class GroupSQL {
    Connection conn = null;
    Statement statement;
    Map<Integer,Group> groups = new HashMap<>();
    final static private String sql = "select * from groups_";
    public GroupSQL(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn =
                    DriverManager.getConnection("jdbc:mysql://localhost:3306/world?" +
                            "user=root&password=jun20jun&serverTimezone=UTC");
            statement = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        } catch (ClassNotFoundException e) {
            System.out.println("=(");
        }

    }
    public Map<Integer,Group> readGrops()
    {

        ResultSet res = null;
        try {
            res = statement.executeQuery("select * from groups_");

            while (res.next()) {

                System.out.println(String.format(
                        "id=%s firstname=%s",
                        res.getString("group_number"),
                        res.getString("avg_mark")));
                Group l = new Group(res.getString("group_number"), res.getString("avg_mark"));
                groups.put(Integer.parseInt( res.getString("group_number")),l);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return groups;
    }
    public List<Group>getGeroups()
    {
        return new ArrayList<>(groups.values());
    }

    public static void main(String[] args) {
        GroupSQL testSQL = new GroupSQL();
        testSQL.readGrops();
    }

}
