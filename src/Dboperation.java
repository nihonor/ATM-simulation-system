import java.sql.*;
public class Dboperation {
    private Connection connection;
public Dboperation(DB db){
    this.connection=db.getConnection();

}
public int insert(String query,Object... params) throws SQLException{
    return executeUpdate(query,params);
}

public int put(String query,Object... params) throws SQLException{
    return executeUpdate(query,params);
}
public int delete(String query,Object... params) throws SQLException{
    return executeUpdate(query,params);
}
public ResultSet get(String query,Object... params) throws SQLException{
    PreparedStatement statement=connection.prepareStatement(query);
    setParameters(statement,params);
    return statement.executeQuery();
}

private int executeUpdate(String query, Object... params) throws SQLException{
    try(PreparedStatement statement=connection.prepareStatement(query)){
        setParameters(statement,params);
        return statement.executeUpdate();
    }
}
private void setParameters (PreparedStatement statement,Object... params) throws SQLException{
    for(int i=0;i<params.length;i++){
        statement.setObject(i+1,params[i]);
    }
}
}
