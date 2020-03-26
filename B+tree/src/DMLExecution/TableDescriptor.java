package DMLExecution;

import BTree.BPlusTree;

public class TableDescriptor{

    public static final char ROW_LOCK_GRANULARITY = 'R';
    public static final char TABLE_LOCK_GRANULARITY = 'T';
    public static final char DEFAULT_LOCK_GRANULARITY = ROW_LOCK_GRANULARITY;

    public static final int BASE_TABLE_TYPE = 0;
    public static final int SYSTEM_TABLE_TYPE = 1;
    public static final int VIEW_TYPE = 2;
    public static final int GLOBAL_TEMPORARY_TABLE_TYPE = 3;
    public static final int SYNONYM_TYPE = 4;
    public static final int VTI_TYPE = 5;

    //表所对应的conglom的标识
    private volatile long tableConglomNumber = -1;

    private char lockGranularity;
    private boolean onCommitDeleteRows; //true means on commit delete rows, false means on commit preserve rows of temporary table.
    private boolean onRollbackDeleteRows; //true means on rollback delete rows. This is the only value supported.
    private boolean indexStatsUpToDate = true;
    private String indexStatsUpdateReason;
    String tableName;
    int tableType;
    BPlusTree dataDictionary;




    public TableDescriptor(BPlusTree dataDictionary, String tableName, int tableType, char lockGranularity) {
        this.dataDictionary=dataDictionary;
        this.tableName = tableName;
        this.tableType = tableType;
        this.lockGranularity = lockGranularity;
        this.columnDescriptorList = new ColumnDescriptorList();
        this.conglomerateDescriptorList = new ConglomerateDescriptorList();

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


    /**
     * 获取表对应的标识
     */
    public long getHeapConglomerateId() {
        ConglomerateDescriptor cd = null;
        if (tableConglomNumber != -1) {
            return tableConglomNumber;
        }
        ConglomerateDescriptor[] cds = getConglomerateDescriptors();
        for (int index = 0; index < cds.length; index++) {
            cd = cds[index];
            if (!cd.isIndex())
                break;
        }
        tableConglomNumber = cd.getConglomerateNumber();
        return tableConglomNumber;
    }

    public ConglomerateDescriptor[] getConglomerateDescriptors() {
        int size = conglomerateDescriptorList.size();
        ConglomerateDescriptor[] cdls = new ConglomerateDescriptor[size];
        conglomerateDescriptorList.toArray(cdls);
        return cdls;
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
        return columnDescriptorList.getColumnDescriptor(oid, columnNumber);
    }

    public ColumnDescriptor getColumnDescriptor(String columnName) {
        return columnDescriptorList.getColumnDescriptor(oid, columnName);
    }

    public String getSchemaName() {
        return schema.getSchemaName();
    }

    public void setUUID(UUID oid) {
        this.oid = oid;
    }


    public ConglomerateDescriptorList getConglomerateDescriptorList() {
        return conglomerateDescriptorList;
    }

    public ConglomerateDescriptor getConglomerateDescriptor(long conglomerateNumber) {
        return conglomerateDescriptorList.getConglomerateDescriptor(conglomerateNumber);
    }


    public int getMaxColumnID() {
        int maxColumnID = 1;
        for (ColumnDescriptor cd : columnDescriptorList) {
            maxColumnID = Math.max(maxColumnID, cd.getPosition());
        }
        return maxColumnID;
    }

}

