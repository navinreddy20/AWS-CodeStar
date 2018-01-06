package com.aws.codestar.projecttemplates.dao;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aws.codestar.projecttemplates.model.Alien;

@Repository
public class AlienDao 
{
	@Autowired
	SessionFactory sf;
	
	@Transactional
	public Alien getAlien(int aid)
	{
		
		Session session = sf.getCurrentSession();
		Alien a = session.get(Alien.class, aid);
		
		return a;
	}
}
