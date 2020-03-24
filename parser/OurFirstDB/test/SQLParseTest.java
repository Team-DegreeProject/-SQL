
import org.junit.Test;

import java.sql.*;


public class SQLParseTest {


    @Test
    public void test_createStudent() throws SQLException {
        Statement s = connection.createStatement();
        s.execute("create table student(sno int,age int)");
    }

    @Test
    public void test_insertStudent() throws SQLException {
        Statement s = connection.createStatement();
        for(int i=1;i<12;i++){
            s.execute(String.format("insert into student values (%d,%d)",i,i));

        }
        System.out.println("test here1~");
    }

    @Test
    public void test_selectStudent() throws SQLException{
        Statement s = connection.createStatement();
        ResultSet resultSet = s.executeQuery("select * from student");

        System.out.println("test here2~（1）");
        while (resultSet.next()){
            System.out.print(resultSet.getInt("sno")+" ");
            System.out.print(resultSet.getInt("age"));
            System.out.println();
        }
        System.out.println("test here2~（2）");
    }

    @Test
    public void test_updateStudent() throws SQLException {
        Statement s = connection.createStatement();
        s.execute("update student set sno = 2 where sno =3");
    }

    @Test
    public void test_deleteStudent() throws SQLException {
        Statement s = connection.createStatement();
        s.execute("delete from student where sno =2");
    }
}
