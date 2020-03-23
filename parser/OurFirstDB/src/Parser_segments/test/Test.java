package Parser_segments.test;

import Parser_segments.SqlParserUtil;

public class Test {

    public static void main(String[] args)
    {
        //程序的入口
        String testSql="select c1,c2,c3     from    t1,t2 where condi3=3 "+"\n"+"    or condi4=5 order by o1,o2";
        SqlParserUtil test=new SqlParserUtil();
        String result=test.getParsedSql(testSql);
        System.out.println(result);

        String testSql1="DELETE FROM Websites\n" +
                "WHERE name='百度' AND country='CN';";
        SqlParserUtil test1=new SqlParserUtil();
        result=test1.getParsedSql(testSql1);
        System.out.println(result);
        //List<SqlSegment> result=test.getParsedSqlList(testSql);//保存解析结果
    }


}