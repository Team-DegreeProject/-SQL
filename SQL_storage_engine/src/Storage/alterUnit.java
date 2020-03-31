package Storage;

public class alterUnit {
    private String tableName;

    public String getAlterType() {
        return alterType;
    }

    private String alterType;

    public String getTableName() {
        return tableName;
    }

    public alterUnit(String tn, String at){
        tableName = tn;
        alterType = at;
    }


}
