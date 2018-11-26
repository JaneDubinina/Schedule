package groupModule;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupSQL {
    DataSource dataSource;
    Map<Integer,Group> groups = new HashMap<>();
    final static private String sql = "select * from groups_";
    public GroupSQL(DataSource dataSource){
            this.dataSource = dataSource;
    }
    public Map<Integer,Group> readGrops()
    {

        ResultSet res = null;
        try {
            Connection conn = dataSource.getConnection();
            Statement statement = conn.createStatement();
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
    public List<Group>getGroups()
    {
        return new ArrayList<>(groups.values());
    }
    public  void AddGroup(String name, String avg_mark)
    {
        groups.put(Integer.parseInt(name), new Group(name, avg_mark));
    }
}
