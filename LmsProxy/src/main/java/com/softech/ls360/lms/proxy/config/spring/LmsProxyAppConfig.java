package com.softech.ls360.lms.proxy.config.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import com.softech.ls360.lms.proxy.dao.impl.RepositoryMarker;
import com.softech.ls360.lms.proxy.service.impl.LmsProxyServiceMarker;

/**
 * We first use the @Configuration annotation to inform Spring that this is a Java-based configuration file.
 * 
 * @ImportResource can also be used to import configuration from XML files, which means you can use XML and Java 
 * configuration classes in a mix-and-match way, although we do not recommend doing that. Mixing XML and Java 
 * configurations will make your application harder to maintain, because you need to scan through both XML files and Java
 * classes to search for a specific bean.
 * 
 * Besides @ImportResource, the @Import annotation can import other configuration classes, which means you can also have 
 * multiple Java configuration classes for various configurations (for example, one class can be dedicated to DAO beans 
 * declaration, one for the Service beans declaration, and so forth).
 * 
 * @ComponentScan defines the packages that Spring should scan for annotations for bean definitions. It’s the same as the
 * <context:component-scan> tag in the XML configuration.
 * 
 * When using the @ComponentScan annotation, you tell Spring which Java package or packages to scan for candidate classes using the
 * String[] basePackages attribute. Spring locates all classes belonging to these packages or subpackages and applies the resource 
 * filters against each class. The downside of basePackages is that it is not type-safe, and so a typo can easily go unnoticed. As 
 * an alternative, you can use the Class<?>[] basePackageClasses attribute. Spring determines the package names to scan from the 
 * classes specified.
 * 
 * When you specify basePackageClasses, Spring will scan the package (and subpackages) of the classes you specify. This is a nice 
 * trick with no-op classes/interfaces like Controllers, Services, etc. Put all your services and repositories in their own packages.
 * Create ServiceMarker empty class/interface in the pk.training.basit.site.service.impl package and RepositoryMarker empty 
 * class/interface in the pk.training.basit.site.repository.impl package. Spring will pick all the services and repositories. Since
 * all the services are in the package pk.training.basit.site.service.impl and serviceMarker empty class/interface too in this 
 * package so spring will pick all the @Service in this package. Same for @Repository
 * 
 * Equivalent xml configuration file
 * 	
		<context:annotation-config />
	
		<import resource="classpath:database/hibernate-context.xml" />
		<import resource="classpath:database/datasource-tx-jpa.xml" />
	
    	<context:component-scan base-package="com.softech.ls360.lms.proxy.manager, 
    		com.softech.ls360.lms.proxy.service, 
    		com.softech.ls360.lms.proxy.dao" /> 
	
		<import resource="lmsapi-spring-ws.xml" />
 * 
 * 
 * @author basit.ahmed
 *
 */
@Configuration
@Import({PersistenceConfig.class})
@ComponentScan(basePackageClasses = { 
		RepositoryMarker.class, 
		LmsProxyServiceMarker.class 
})
public class LmsProxyAppConfig {


}
