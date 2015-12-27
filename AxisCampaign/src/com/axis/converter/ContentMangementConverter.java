package com.axis.converter;

import java.util.List;

import org.springframework.stereotype.Component;

import com.axis.exception.DataNotFound;
import com.axis.exception.ObjectNotFound;

@Component
public class ContentMangementConverter implements NecessaryConverter {

	@Override
	public Object modelToEntity(Object m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object updateModelToEntity(Object m, Object e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object entityToModel(Object e) throws ObjectNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List entityListToModelList(List es) throws DataNotFound,
			ObjectNotFound {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List modelListToEntityList(List ms) {
		// TODO Auto-generated method stub
		return null;
	}


}