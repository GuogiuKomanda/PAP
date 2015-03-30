create table pap.WPart (id  bigserial not null, full_code varchar(40) not null, status varchar(20) not null, year bytea not null, model_engine_id int8, primary key (id))
create table pap.engine (id  bigserial not null, cc int4 not null, code varchar(40) not null, kw int4 not null, name varchar(40), fuel_type_id int8, primary key (id))
create table pap.fuel_type (id  bigserial not null, name varchar(40) not null, primary key (id))
create table pap.make (id  bigserial not null, name varchar(40) not null, primary key (id))
create table pap.model (id  bigserial not null, from_year bytea not null, name varchar(40) not null, to_year bytea not null, model_group_id int8, primary key (id))
create table pap.model_engine (id  bigserial not null, from_year bytea not null, to_year bytea not null, engine_id int8, model_id int8, primary key (id))
create table pap.model_group (id  bigserial not null, name varchar(40) not null, make_id int8, primary key (id))
create table pap.parts (id  bigserial not null, primary key (id))
create table pap.parts_translated (Part_id int8 not null, description varchar(255), name varchar(255), translated_KEY varchar(255), primary key (Part_id, translated_KEY))
create table pap.user (id  bigserial not null, test_date bytea not null, name varchar(40) not null, primary key (id))
alter table pap.WPart add constraint FK_58uf66rl5s3o8qhxbixo0nr0q foreign key (model_engine_id) references pap.model_engine
alter table pap.engine add constraint FK_9ps4pia1om721wo4le8orv46f foreign key (fuel_type_id) references pap.fuel_type
alter table pap.model add constraint FK_9kiiwl26urfddo12jiim4gv7e foreign key (model_group_id) references pap.model_group
alter table pap.model_engine add constraint FK_dy9ao6dd6bufiy29ds32qfatx foreign key (engine_id) references pap.engine
alter table pap.model_engine add constraint FK_m9ahiqlmx3jpfho10ipk89vk7 foreign key (model_id) references pap.model
alter table pap.model_group add constraint FK_eepeicb21asfw92fumjte1m2c foreign key (make_id) references pap.make
alter table pap.parts_translated add constraint FK_2k4sjacqx7qvix4wda0r0679b foreign key (Part_id) references pap.parts
