package com.inviti.model.domainmodel;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**

 CREATE KEYSPACE filestorage WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
 USE events;
 CREATE TABLE DataStore (
 type text,
 bucket text,
 id timeuuid,
 tags set<text>,
 PRIMARY KEY ((type, bucket), id)
 ) WITH CLUSTERING ORDER BY (id DESC);





 * CREATE KEYSPACE filestorage WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
 USE filestorage;

 CREATE TABLE IF NOT EXISTS data_store (
 commit blob, -- blob representing the commit hash
 delta int, -- how much the scores have changed
 score int, -- the test score, which is determined by the client
 test blob, -- blob for the test
 PRIMARY KEY(commit, delta, test)
 );

 insert into test_by_score  (commit, delta, test, score) values
 (textAsBlob('bdb14fbe076f6b94444c660e36a400151f26fc6f'), 0, textAsBlob('{"prefix": "enwiki", "title": "\"Aghnadarragh\""}'), 100
 );

 INSERT INTO test_by_score (commit, delta, test, score) VALUES (
 textAsBlob('cdb14fbe076f6b94444c660e36a400151f26fc6f'), 0, textAsBlob('{"prefix": "enwiki", "title": "\"Aghnadarragh\""}'), 100
 );

 insert into test_by_score (commit, delta, test, score) values (
 textAsBlob('adb14fbe076f6b94444c660e36a400151f26fc6f'), 0, textAsBlob('{"prefix": "enwiki", "title": "\"Aghnadarragh\""}'), 100
 );
 */


@Table
public class DataStore {

    @PrimaryKeyColumn(name = "id", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID id;
    @PrimaryKeyColumn(name = "type", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String type;
    @PrimaryKeyColumn(name = "bucket", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String bucket;
    @Column
    private Set tags = new HashSet();

    public DataStore(UUID id, String type, String bucket, Set tags) {
        this.id = id;
        this.type = type;
        this.bucket = bucket;
        this.tags.addAll(tags);
    }

    public UUID getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getBucket() {
        return bucket;
    }

    public Set getTags() {
        return tags;
    }
}
