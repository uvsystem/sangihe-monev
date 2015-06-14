package com.unitedvision.sangihe.monev.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
import com.unitedvision.sangihe.monev.repository.OperatorRepository;
import com.unitedvision.sangihe.monev.service.OperatorService;

@Service
@Transactional(readOnly = true)
public class OperatorServiceImpl implements OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Operator simpan(Operator operator) throws PersistenceException {
		return operatorRepository.save(operator);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean hapus(Operator operator) {
		operatorRepository.delete(operator);
		
		return true;
	}
	
	@Override
	public Operator get(int id) throws EntityNotExistsException {
		return operatorRepository.findOne(id);
	}

	@Override
	public Operator get(String username) throws EntityNotExistsException {
		return operatorRepository.findByUsername(username);
	}

	@Override
	public List<Operator> get(Skpd skpd) throws EntityNotExistsException {
		return operatorRepository.findBySkpd(skpd);
	}

	@Override
	public List<Operator> get() throws EntityNotExistsException {
		return operatorRepository.findAll();
	}
}
