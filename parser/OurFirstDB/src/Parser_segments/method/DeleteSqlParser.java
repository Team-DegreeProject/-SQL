package Parser_segments.method;

import Parser_segments.SqlSegment;

public class DeleteSqlParser extends BaseSingleSqlParser {
    public DeleteSqlParser(String originalSql) {
        super(originalSql);
    }
    @Override
    protected void initializeSegments() {
        segments.add(new SqlSegment("(delete from)(.+)( where | ENDOFSQL)","[,]"));
        segments.add(new SqlSegment("(where)(.+)( ENDOFSQL)","(and|or)"));
    }
}