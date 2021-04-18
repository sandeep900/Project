package com.nt.service;

import java.util.Random;


import com.nt.bo.CoronaBo;
import com.nt.dao.CoranaDao;
import com.nt.dao.CoranaDaoImpl;
import com.nt.dto.CoronaDto;

public  class PatientManagementServiceImpl implements PatientManagementService {

	private CoranaDao dao;
	
	public PatientManagementServiceImpl() {
      dao= new CoranaDaoImpl();
	}
	
	
	@Override
	public String register(CoronaDto dto) throws Exception {

		
		
    CoronaBo bo=new CoronaBo();
    bo.setPname(dto.getPname());
    bo.setPaddrs(dto.getPaddrs());
    bo.setPage(dto.getPage());
    bo.setPstate(dto.getPstate());
    
   int count=dao.insert(bo);
   if(count==0) {
	   return "registre failed";
   }
   else {
	   return "Register success";
   }


	
	}

}
