package Parser_segments.method;

import Parser_segments.SqlSegment;

public class UpdateSqlParser extends BaseSingleSqlParser {
    public UpdateSqlParser(String originalSql) {
        super(originalSql);
    }
    @Override
    protected void initializeSegments() {
        segments.add(new SqlSegment("(update)(.+)(set)","[,]"));
        segments.add(new SqlSegment("(set)(.+)( where | ENDOFSQL)","[,]"));
        segments.add(new SqlSegment("(where)(.+)( ENDOFSQL)","(and|or)"));
    }
}