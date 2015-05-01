package com.inviti.model.domainmodel;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * CREATE KEYSPACE filestorage WITH replication = {'class':'SimpleStrategy', 'replication_factor':1};
 * USE events;
 * CREATE TABLE DataStore (
 * type text,
 * bucket text,
 * id timeuuid,
 * tags set<text>,
 * PRIMARY KEY ((type, bucket), id)
 * ) WITH CLUSTERING ORDER BY (id DESC);
 *
 * alter table filestorage add image blob;
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
    @Column
    private ByteBuffer image;

    public DataStore(UUID id, String type, String bucket, Set tags, ByteBuffer image) {
        this.id = id;
        this.type = type;
        this.bucket = bucket;
        this.tags.addAll(tags);
        this.image = image;
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

    public ByteBuffer getImage() {
        return image;
    }
}
