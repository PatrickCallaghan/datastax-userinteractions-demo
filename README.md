User Interactions Demo
======================

NOTE - this example requires apache cassandra version > 2.0 and the cassandra-driver-core version > 2.0.0

## Scenario

This is short demo which shows how to capture user interactions. The idea is that users use a variety of different apps and we want to track every interaction that they have each of these applications. We can then look at the interactions themselves as a group and see a count of each of the users views.

e.g.

select * from user_interaction_counters where user_id = 'U1' and app = 'mobilefx';

select * from user_interactions where user_id = 'U1' and app = 'mobilefx';


## Schema Setup
Note : This will drop the keyspace "datastax_user_interactions_demo" and create a new one. All existing data will be lost. 

The schema can be found in src/main/resources/cql/

To specify contact points use the contactPoints command line parameter e.g. '-DcontactPoints=192.168.25.100,192.168.25.101'
The contact points can take mulitple points in the IP,IP,IP (no spaces).

To create the a single node cluster with replication factor of 1 for standard localhost setup, run the following

    mvn clean compile exec:java -Dexec.mainClass="com.datastax.demo.SchemaSetup"

To run the insert

        mvn clean compile exec:java -Dexec.mainClass="com.datastax.user.interaction.Main" 
	
To remove the tables and the schema, run the following.

    mvn clean compile exec:java -Dexec.mainClass="com.datastax.demo.SchemaTeardown"
	
