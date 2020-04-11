package storage.Storage;


import table.TableDescriptor;

public class descriptorSaver {
    TableDescriptor tabledes;

    public descriptorSaver(TableDescriptor desToBeSaved){
        tabledes = desToBeSaved;
    }

    String tableName = tabledes.getTableName();
    String schema = tabledes.getSchemaName();
    char lockGranularity = tabledes.getLockGranularity();

}
