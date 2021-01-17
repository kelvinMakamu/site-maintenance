package components.data;

import org.junit.rules.ExternalResource;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class DatabaseRule extends ExternalResource {

    @Override
    protected void before(){
        Database.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/site_maintenance_test",null,null);
    }

    @Override
    protected void after() {
        String engineersDeleteQuery  = "DELETE FROM engineers";
        String sitesDeleteQuery      = "DELETE FROM sites";
        try(Connection connection = Database.sql2o.open()){
            connection.createQuery(engineersDeleteQuery).executeUpdate();
            connection.createQuery(sitesDeleteQuery).executeUpdate();
        }
    }
}