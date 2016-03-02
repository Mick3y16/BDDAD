DECLARE
  proj project_var;
BEGIN
  proj := project_var(
    'Test Project',
    'Description',
    junction_var_list(
      junction_var('n01'),
      junction_var('n02')
    ),
    section_var_list(
      section_var(
        'E01-n01-n02',
        'Highway',
        0.0,
        3.0,
        -5.0,
        segment_var_list(
          segment_var(
            1,
            0.0,
            0.0,
            10000,
            33.33,
            0.0,
            100
          )
        )
      )
    ),
    vehicle_var_list()
  );

  insertproject(proj);
END;