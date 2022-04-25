package com.dawes;

import javax.persistence.Persistence;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dawes.servicios.UsuariosServicios;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SpringBootTest
public class ProyectoFinCicloApplicationTests {

//	@Autowired
//	CategoryService sCat;
//	
	@Autowired
	UsuariosServicios sUsu;
//	
//	@Autowired
//	CommentServicios sCom;
//	
//	@Autowired
//	PostCategoryServicios sPC;
//	
//	@Autowired
//	PostServicios sPost;
//	
//	@Autowired
//	RolServicios sRol;
	
	
	@BeforeClass
	public static void crearEsquema() {
		Persistence.generateSchema("Alonso_PROYECTO", null);
	}
}
