package DMLExecution;

import BTree.BPlusTree;

public class TableDescriptor implements TableSchema{


    public static final char DEFAULT_LOCK_GRANULARITY = ROW_LOCK_GRANULARITY;

    //表所对应的conglom的标识
    private volatile long tableConglomNumber = -1;

    private char lockGranularity;
    private boolean onCommitDeleteRows; //true means on commit delete rows, false means on commit preserve rows of temporary table.
    private boolean onRollbackDeleteRows; //true means on rollback delete rows. This is the only value supported.
    private boolean indexStatsUpToDate = true;
    private String indexStatsUpdateReason;
    private String tableName;
    private int tableType;
    private BPlusTree dataDictionary;
    private int schema;




    public TableDescriptor(BPlusTree dataDictionary, String tableName, int tableType, char lockGranularity,int schema) {
        this.dataDictionary=dataDictionary;
        this.tableName = tableName;
        this.tableType = tableType;
        this.lockGranularity = lockGranularity;
        this.columnDescriptorList = new ColumnDescriptorList();
        this.schema=schema;
    }


    /**
     * 根据表描述获取列描述
     */
    ColumnDescriptorList columnDescriptorList;

    public ColumnDescriptorList getColumnDescriptorList() {
        return columnDescriptorList;
    }

    public char getLockGranularity() {
        return lockGranularity;
    }

    public String getName() {
        return tableName;
    }

    public int getTableType() {
        return tableType;
    }



    public String[] getColumnNamesArray() {
        int size = getNumberOfColumns();
        String[] s = new String[size];
        for (int i = 0; i < size; i++) {
            s[i] = getColumnDescriptor(i + 1).getColumnName();
        }
        return s;
    }


    //获取表中列的数目
    public int getNumberOfColumns() {
        return getColumnDescriptorList().size();
    }

    public ColumnDescriptor getColumnDescriptor(int columnNumber) {
        return columnDescriptorList.getColumnDescriptor(columnNumber);
    }

    public ColumnDescriptor getColumnDescriptor(String columnName) {
        return columnDescriptorList.getColumnDescriptor(columnName);
    }

    public String getSchemaName() { return schemaName[schema];}


    public int getMaxColumnID() {
        int maxColumnID = 1;
        for (ColumnDescriptor cd : columnDescriptorList) {
            maxColumnID = Math.max(maxColumnID, cd.getPosition());
        }
        return maxColumnID;
    }

}

