package com.mengzhou44.demo;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CourseJdbcRepository implements ICourseRepository<Course, Long> {
	@Autowired
	private JdbcTemplate springJdbcTemplate;

	private static String INSERT_QUERY =

			"""
						insert into course (id, name, author)
						values(?, ?,?);

					""";

	private static String DELETE_QUERY =

			"""
						delete from course
						where id = ?

					""";

	private static String SELECT_QUERY =

			"""
						select * from course
						where id = ?

					""";

	private static String SELECT_ALL_QUERY =

			"""
						select * from course
					""";

	public void save(Course course) {
		springJdbcTemplate.update(INSERT_QUERY,
				course.getId(), course.getName(), course.getAuthor());
	}

	public void deleteById(Long id) {
		springJdbcTemplate.update(DELETE_QUERY, id);
	}

	public Course findById(Long id) {
		// ResultSet -> Bean => Row Mapper =>
		return springJdbcTemplate.queryForObject(SELECT_QUERY,
				new BeanPropertyRowMapper<>(Course.class), id);

	}

	public Course[] findAll() {

		ArrayList<Course> list = new ArrayList<>();

		var rows = springJdbcTemplate.queryForList(SELECT_ALL_QUERY);

		for (Map<String, Object> row : rows) {
			Course course = new Course();
			course.setId((long) row.get("id"));
			course.setName((String) row.get("name"));
			course.setAuthor((String) row.get("author"));

			list.add(course);
		}

		return list.toArray(new Course[list.size()]);
	}

}
