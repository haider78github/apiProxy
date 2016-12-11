package com.softech.ls360.lms.api.proxy.test;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.softech.ls360.lms.api.proxy.config.spring.LmsApiProxyAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=LmsApiProxyAppConfig.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public abstract class LmsApiProxyAbstractTest {

}
