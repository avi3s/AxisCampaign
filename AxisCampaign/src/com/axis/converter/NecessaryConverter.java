package com.axis.converter;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;

public interface NecessaryConverter<E, M> extends Serializable {

	/**
	 * To convert {M} type object to {E} type
	 * 
	 * @param m
	 * @return
	 */
	public abstract E modelToEntity(M m);

	/**
	 * To convert {M} type object to {E} type
	 * 
	 * @param m
	 * @return
	 */
	public abstract E updateModelToEntity(M m,E e);
	
	/**
	 * To convert {E} type object to {M} type
	 * 
	 * @param e
	 * @return
	 * @throws ObjectNotFound 
	 */
	public abstract M entityToModel(E e) throws ObjectNotFound;

	/**
	 * To convert {E} type list of object to {M} type list of object
	 * 
	 * @param es
	 * @return
	 * @throws DataNotFound 
	 * @throws ObjectNotFound 
	 */
	public abstract List<M> entityListToModelList(List<E> es) throws DataNotFound, ObjectNotFound;

	/**
	 * To convert {M} type list of object to {E} type list of object
	 * 
	 * @param ms
	 * @return
	 */
	public abstract List<E> modelListToEntityList(List<M> ms);
}
