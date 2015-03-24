create table pap.engine (id  bigserial not null, cc int4 not null, enginecode varchar(40) not null, enginename varchar(40), kw int4 not null, fueltype_id int8, primary key (id))
create table pap.fueltype (id  bigserial not null, fuel varchar(40) not null, primary key (id))
create table pap.make (id  bigserial not null, make varchar(40) not null, primary key (id))
create table pap.model (id  bigserial not null, from_year bytea not null, model varchar(40) not null, to_year bytea not null, make_id int8, primary key (id))
create table pap.modelengine (id  bigserial not null, from_year bytea not null, to_year bytea not null, engine_id int8, model_id int8, primary key (id))
create table pap.parts (id  bigserial not null, primary key (id))
create table pap.parts_translated (Part_id int8 not null, description varchar(255), name varchar(255), translated_KEY varchar(255), primary key (Part_id, translated_KEY))
create table pap.user (id  bigserial not null, test_date bytea not null, name varchar(40) not null, primary key (id))
alter table pap.engine add constraint FK_of0cij9ddyaoho3dghfgvjsm4 foreign key (fueltype_id) references pap.fueltype
alter table pap.model add constraint FK_hao00na6fub4artjoorc5ob71 foreign key (make_id) references pap.make
alter table pap.modelengine add constraint FK_h3cs25mibbcjo69ng7r2qj2v4 foreign key (engine_id) references pap.engine
alter table pap.modelengine add constraint FK_pb2qibyqpgqt8g46q2obhvfcf foreign key (model_id) references pap.model
alter table pap.parts_translated add constraint FK_2k4sjacqx7qvix4wda0r0679b foreign key (Part_id) references pap.parts
