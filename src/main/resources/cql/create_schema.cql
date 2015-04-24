create keyspace if not exists datastax_user_interactions_demo WITH replication = {'class': 'SimpleStrategy', 'replication_factor': '1' };

use datastax_user_interactions_demo;

create table if not exists user_interactions(
user_id text,
app text,
time timestamp,
action text,
PRIMARY KEY ((user_id, app), time)
) 
WITH CLUSTERING ORDER BY (time desc);

create table if not exists user_interaction_counters(
user_id text,
app text,
action text,
count counter,
PRIMARY KEY (user_id, app, action)
);

create table if not exists user_interaction_visits(
visit_id uuid,
interaction_time timestamp,
action text,
app text,
PRIMARY KEY (visit_id, interaction_time, action)
);



truncate user_interactions;
truncate user_interaction_counters;
truncate user_interaction_visits;