BASE_PATH="/grosvenor/twitter/grosvenorkafkaflume"
DATE_PREV=`date --date="yesterday" +%Y-%m-%d`
HDFS_DATE_FORMAT=${DATE_PREV:2:10}
HDFS_PATH=$BASE_PATH'/'$HDFS_DATE_FORMAT
HDFS_WRITE_PATH=$HDFS_PATH'/FlumeData-'$HDFS_DATE_FORMAT'.txt'
HDFS_COMMAND_MERGE="hdfs dfs -cat $HDFS_PATH/FlumeData.*.txt | hdfs dfs -put - $HDFS_WRITE_PATH"
HDFS_COMMAND_PURGE="hdfs dfs -rmr $HDFS_PATH/FlumeData.*.txt"
eval $HDFS_COMMAND_MERGE
echo $
eval $HDFS_COMMAND_PURGE
echo $