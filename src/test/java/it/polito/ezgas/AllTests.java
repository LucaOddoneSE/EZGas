package it.polito.ezgas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.polito.ezgas.entity.UserTest;
import it.polito.ezgas.service.impl.UserServiceImplStepN;
import it.polito.ezgas.service.impl.UserServiceImplStep2Tests;
import it.polito.ezgas.dto.IdPwTest;
import it.polito.ezgas.dto.LoginDtoTest;
import it.polito.ezgas.entity.GasStationTest;

@RunWith(Suite.class)
@SuiteClasses({ UserTest.class, GasStationTest.class, IdPwTest.class, 
	LoginDtoTest.class, UserServiceImplStepN.class, UserServiceImplStep2Tests.class,
	UserServiceImplStepN.class})
public class AllTests {

}