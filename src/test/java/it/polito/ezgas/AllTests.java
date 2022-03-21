package it.polito.ezgas;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import it.polito.ezgas.entity.UserTest;
import it.polito.ezgas.repository.GasStationRepositoryTests;
import it.polito.ezgas.repository.UserRepositoryTests;
import it.polito.ezgas.service.impl.UserServiceImplStepN;
import it.polito.ezgas.service.impl.UserServiceImplTests;
import it.polito.ezgas.service.impl.GasStationServiceImplTests;
import it.polito.ezgas.converter.GasStationConverterTests;
import it.polito.ezgas.converter.UserConverterTests;
import it.polito.ezgas.dto.IdPwTest;
import it.polito.ezgas.dto.LoginDtoTest;
import it.polito.ezgas.entity.GasStationTest;

@RunWith(Suite.class)
@SuiteClasses({ UserTest.class, GasStationTest.class, IdPwTest.class, 
	LoginDtoTest.class, UserServiceImplStepN.class,
	UserServiceImplTests.class, UserConverterTests.class, UserRepositoryTests.class,
	GasStationServiceImplTests.class, GasStationConverterTests.class,
	GasStationRepositoryTests.class})

public class AllTests {

}