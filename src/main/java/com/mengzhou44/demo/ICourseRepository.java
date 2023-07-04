package com.mengzhou44.demo;

public interface ICourseRepository<T, ID> {

	public void save(T t);

	public T findById(ID id);

	public void deleteById(ID id);

}
