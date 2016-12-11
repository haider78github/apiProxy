package com.softech.ls360.storefront.proxy.test;

import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.softech.ls360.storefront.proxy.config.spring.StorefrontProxyAppConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=StorefrontProxyAppConfig.class)
@DirtiesContext(classMode=ClassMode.AFTER_CLASS)
public abstract class StorefrontProxyAbstractTest {

}
