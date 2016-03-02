CREATE TABLE project(
	project_id	integer,
	name 	    	varchar(16),
	description varchar(32),
	CONSTRAINT PK_project PRIMARY KEY(project_id)
);

CREATE TABLE junction(
	junction_id integer,
	project_id	integer,
	name		    varchar(16),
	CONSTRAINT PK_junction PRIMARY KEY(junction_id),
	CONSTRAINT FK_junction_project_id FOREIGN KEY (project_id) REFERENCES project (project_id)
);

CREATE TABLE road_typology(
	road_type_id  integer,
	typology 		  varchar(16),
	CONSTRAINT PK_road_type PRIMARY KEY(road_type_id)
);

CREATE TABLE section(
	section_id       integer,
	project_id       integer,
	road_name        varchar(16),
	road_typology_id integer,
	toll             number(5, 2),
	wind_speed       number(7, 4),
	wind_orientation number(7, 4),
	CONSTRAINT PK_section PRIMARY KEY(section_id),
	CONSTRAINT FK_section_project_id FOREIGN KEY (project_id) REFERENCES project (project_id),
	CONSTRAINT FK_section_rt_id FOREIGN KEY (road_typology_id) REFERENCES road_typology (road_type_id)
);

CREATE TABLE segment(
	segment_id               integer,
	section_id               integer,
	s_index                  number(2),
	initial_height           number(8, 4),
	angle                    number(6, 4),
	s_length                 number(10, 4),
	maximum_velocity         number(6, 4),
	minimum_velocity         number(6, 4),
	maximum_number_vehicles  number(4),
	CONSTRAINT PK_segment PRIMARY KEY(segment_id),
	CONSTRAINT FK_segment_section_id FOREIGN KEY (section_id) REFERENCES section (section_id)
);
