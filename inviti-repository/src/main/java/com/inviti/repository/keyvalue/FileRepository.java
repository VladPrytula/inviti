package com.inviti.repository.keyvalue;

import com.inviti.model.domainmodel.DataStore;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends CassandraRepository<DataStore>{
}
