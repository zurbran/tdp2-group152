package com.tdp2.group152;

import com.tdp2.group152.services.PassengerService;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:ApplicationContext.xml")
@Rollback
public class PassengerServiceTestCase {

    @Autowired
    private PassengerService passengerService;


}
