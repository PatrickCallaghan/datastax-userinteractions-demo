User Interactions Demo
======================

NOTE - this example requires apache cassandra version > 2.0 and the cassandra-driver-core version > 2.0.0

## Scenario

This is short demo which shows how to capture credit card transactions. The queries that will be used in this app will be 

Get all transactions for a user (within a certain time frame)

Get all transactions by issuer 

Get all transactions per minute within a certain time frame. 

The balances will be updated every 10,000 transactions 

Get the balance for a user of all transactions. 

This demo shows how to use static columns, batching, paging and counters to implement these features.  


## Schema Setup
Note : This will drop the keyspace "datastax_tickdata_demo" and create a new one. All existing data will be lost. 

The schema can be found in src/main/resources/cql/

To specify contact points use the contactPoints command line parameter e.g. '-DcontactPoints=192.168.25.100,192.168.25.101'
The contact points can take mulitple points in the IP,IP,IP (no spaces).

To create the a single node cluster with replication factor of 1 for standard localhost setup, run the following

    mvn clean compile exec:java -Dexec.mainClass="com.datastax.demo.SchemaSetup"

To run the insert

        mvn clean compile exec:java -Dexec.mainClass="com.datastax.user.interaction.Main" 
	
To remove the tables and the schema, run the following.

    mvn clean compile exec:java -Dexec.mainClass="com.datastax.demo.SchemaTeardown"
	
