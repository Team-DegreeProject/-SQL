package Parser_segments;

import java.util.List;

public class SqlParserUtil {
    public String getParsedSql(String sql){
        sql=sql.trim();//去掉前后空格
        sql=sql.toLowerCase();//全部改写为小写
        sql=sql.replaceAll("\\s{1,}", " ");
        // \s表示空格，｛1，｝表示1一个以上 ，\\s第一个
        //把一个以上的空白字符转换为一个空格
        sql=""+sql+" ENDOFSQL";
        System.out.println("SQL is :"+sql);
        return SingleSqlParserFactory.generateParser(sql).getParsedSql();
    }

    /** *//**
     　* SQL语句解析的接口
     　* @param sql:要解析的sql语句
     　* @return 返回解析结果
     　*/
    public List<SqlSegment> getParsedSqlList(String sql)
    {
        sql=sql.trim();
        sql=sql.toLowerCase();
        sql=sql.replaceAll("\\s{1,}", " ");
        sql=""+sql+" ENDOFSQL";
        //System.out.println(sql);
        return SingleSqlParserFactory.generateParser(sql).RetrunSqlSegments();
    }
}
