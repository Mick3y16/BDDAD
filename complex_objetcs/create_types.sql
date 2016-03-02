CREATE TYPE segment_var AS OBJECT (
  s_index         NUMBER(2),
  initial_height  NUMBER(8, 4),
  angle           NUMBER(6, 4),
  s_length        NUMBER(10, 4),
  max_velocity    NUMBER(6, 4),
  min_velocity    NUMBER(6, 4),
  total_vehicles  NUMBER(4)
);

CREATE TYPE segment_var_list IS TABLE OF segment_var;

CREATE TYPE section_var AS OBJECT (
  roadname          VARCHAR(16),
  typology          VARCHAR(16),
  toll              NUMBER(5, 2),
  wind_speed        NUMBER(7, 4),
  wind_orientation  NUMBER(7, 4),
  segments          segment_var_list
);

CREATE TYPE section_var_list IS TABLE OF section_var;

CREATE TYPE junction_var AS OBJECT (
  name  VARCHAR(16)
);

CREATE TYPE junction_var_list IS TABLE OF junction_var;

CREATE TYPE vehicle_var AS OBJECT (
  name  VARCHAR(16)
);

CREATE TYPE vehicle_var_list IS TABLE of vehicle_var;

CREATE TYPE project_var AS OBJECT (
  p_name      VARCHAR(16),
  description VARCHAR(32),
  junctions   junction_var_list,
  sections    section_var_list,
  vehicles    vehicle_var_list
);
