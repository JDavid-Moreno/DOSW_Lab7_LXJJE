package edu.eci.dosw.TechCup;

import edu.eci.dosw.TechCup.controller.AuthenticationController;
import edu.eci.dosw.TechCup.controller.TournamentController;
import edu.eci.dosw.TechCup.controller.UserController;
import edu.eci.dosw.TechCup.model.*;
import edu.eci.dosw.TechCup.service.UserService;
import edu.eci.dosw.TechCup.service.UserServiceProd;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class TechCupApplicationTests {
	@Autowired
	UserController userController;
	@Autowired
	AuthenticationController authenticationController;
	@Autowired
	TournamentController tournamentController;

	@Test
	void contextLoads() {
	}
	@Test
	void testUserCreation()
	{
		User u = new User("juan.roa-h@mail.escuelaing.edu.co","passwordcifrado123","Juan David", "Roa Hernandez", LocalDate.of(2005,3,22), Program.INGENIERIA_SISTEMAS);
		u.setIdentifiacion(IdentificationType.CC, "123456789");
		assertEquals("Roa Hernandez", u.getLastName());
		assertEquals("Juan David", u.getName());
		assertEquals("juan.roa-h@mail.escuelaing.edu.co", u.getEmail());
		assertEquals("123456789", u.getIdentification());
		assertEquals(LocalDate.of(2005,3,22), u.getBirthDate());
	}
	@Test
	void testArchivoCreation(){
		byte[] bytes = new byte[] {1,2,3,4,5,6,7,8,9,10,11,12};
		LocalDate date = LocalDate.now();
		File a = new File(bytes, "application/pdf", date );
		assertEquals(bytes, a.getBytes());
		assertEquals("application/pdf", a.getMime());
		assertEquals(date, a.getLastMod());
	}
	@Test
	void testBuscarUsuarioPorCorreo(){
		User u = new User("juan.roa-h@mail.escuelaing.edu.co","passwordcifrado123","Juan David", "Roa Hernandez", LocalDate.of(2005,3,22), Program.INGENIERIA_SISTEMAS);
		UserServiceProd su = new UserServiceProd();
		//falta setear repo usuario
		User busqueda = su.searchUserByEmail("juan.roa-h@mail.escuelaing.edu.co");
		assertEquals(u, busqueda);
	}
	@Test
	void testBuscarUsuarioPorIdentifiacion(){
		User u = new User("juan.roa-h@mail.escuelaing.edu.co","passwordcifrado123","Juan David", "Roa Hernandez", LocalDate.of(2005,3,22), Program.INGENIERIA_SISTEMAS);
		u.setIdentifiacion(IdentificationType.CC, "123456789");
		UserServiceProd su = new UserServiceProd();
		//falta setear repo usuario
		User busqueda = su.searchUserByIdentification("123456789");
		assertEquals(u, busqueda);
	}
	@Test
	void testCrearPerfilDeportivo(){
		PerfilDeportivo pd = new PerfilDeportivo(Position.PORTERO, "1");
		assertEquals("1", pd.getDorsalPredefinida());
		assertEquals(Position.PORTERO, pd.getPosicionPredefinida());
	}
	@Test
	void testObtenerPerfilDeportivoPorCorreoUsuario(){
		User u = new User("juan.roa-h@mail.escuelaing.edu.co","passwordcifrado123","Juan David", "Roa Hernandez", LocalDate.of(2005,3,22), Program.INGENIERIA_SISTEMAS);
		PerfilDeportivo pd = new PerfilDeportivo(Position.PORTERO, "1");
		u.setPerfilDeportivo(pd);
		UserServiceProd su = new UserServiceProd();
		PerfilDeportivo pdbusqueda = su.buscarPerfilDeportivoPorUsuario("juan.roa-h@mail.escuelaing.edu.co");
		assertEquals(pd, pdbusqueda);
	}
	@Test
	void testObtenerPerfilDeportivoPorUsuario(){
		User u = new User("juan.roa-h@mail.escuelaing.edu.co","passwordcifrado123","Juan David", "Roa Hernandez", LocalDate.of(2005,3,22), Program.INGENIERIA_SISTEMAS);
		PerfilDeportivo pd = new PerfilDeportivo(Position.PORTERO, "1");
		u.setPerfilDeportivo(pd);
		UserServiceProd su = new UserServiceProd();
		PerfilDeportivo pdbusqueda = su.buscarPerfilDeportivoPorUsuario(u);
		assertEquals(pd, pdbusqueda);
	}
}
