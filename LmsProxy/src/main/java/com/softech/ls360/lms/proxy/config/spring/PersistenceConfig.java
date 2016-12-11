package com.softech.ls360.lms.proxy.config.spring;

import java.util.Properties;

import javax.persistence.SharedCacheMode;
import javax.persistence.ValidationMode;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.softech.ls360.lms.proxy.config.spring.properties.database.DatabasePropertiesConfig;

/**
 * We first use the @Configuration annotation to inform Spring that this is a Java-based configuration file.
 * 
 * The @PropertySource annotation is used to load properties files into the Spring ApplicationContext, which accepts the 
 * location as the argument (more than one location can be provided). For XML, <context:property-placeholder> serves the same 
 * purpose.  @PropertySource annotation provides a mechanism for adding a source of name/value property pairs to Spring's 
 * Environment and it is used in conjunction with @Configuration classes.Loads in external properties into the Environment
 * 
 * Application infrastructure services can also be defined in Java classes. For example, @EnableTransactionManagement defines 
 * that we will use Spring’s transaction management feature.
 * 
 * @author basit.ahmed
 *
 */
@Configuration
@EnableTransactionManagement(mode = AdviceMode.PROXY, proxyTargetClass = false, order = 2)
@EnableJpaRepositories(
	basePackages = "com.softech.ls360.lms.proxy.repositories",
    entityManagerFactoryRef = "entityManagerFactory",
	transactionManagerRef = "transactionManager"
)
@Import({DatabasePropertiesConfig.class})
public class PersistenceConfig {
      
	private static final String PROPERTY_NAME_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH = "hibernate.max_fetch_depth";
	private static final String PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE = "hibernate.jdbc.fetch_size";
	private static final String PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE = "hibernate.jdbc.batch_size";

	private static final String[] ENTITYMANAGER_PACKAGES_TO_SCAN = {"com.softech.ls360.lms.proxy.entities", "com.softech.ls360.lms.proxy.converters"};
	   
    /**
	 * Notice the @Autowired property of the env variable, which is of the Environment type. This is the Environment 
	 * abstraction feature that Spring provides.
	 */
	@Autowired
	private Environment env;
	
	 /**
	  * The @Bean annotation is used to declare a Spring bean and the DI requirements. The @Bean annotation is equivalent to
	 *  the <bean> tag, the method name is equivalent to the id attribute within the <bean> tag.
	  * 
	  * <bean id="dataSource" class="org.apache.commons.dbcp2.BasicDataSource" destroy-method="close" 
			p:driverClassName="${jdbc.ls360.driverClassName}" 
			p:url="${jdbc.ls360.url}"   
			p:username="${jdbc.ls360.username}" 
			p:password="${jdbc.ls360.password}" />
	  * 
	  * @return
	  */
	 @Bean(destroyMethod = "close")
	 public DataSource dataSource() {
		 BasicDataSource dataSource = new BasicDataSource();
		 dataSource.setDriverClassName(env.getProperty("jdbc.ls360.driverClassName"));
		 dataSource.setUrl(env.getProperty("jdbc.ls360.url"));
		 dataSource.setUsername(env.getProperty("jdbc.ls360.username"));
		 dataSource.setPassword(env.getProperty("jdbc.ls360.password"));
		 return dataSource;
	 }
	 
	 /**
	  * The @Bean annotation is used to declare a Spring bean and the DI requirements. The @Bean annotation is equivalent to
	  * the <bean> tag, the method name is equivalent to the id attribute within the <bean> tag. Setter injection was 
	  * achieved by calling the corresponding method to get the entityManagerFactory, which is the same as using the 
	  * p:entityManagerFactory-ref tag in the XML configuration.
	  * 
	  * <bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager"
        	p:entityManagerFactory-ref="entityManagerFactory"/>
	  * 
	  * The transactionManager bean: 
      * 	The entity manager factory requires a transaction manager for transactional data access. Spring provides a 
      * transaction manager specifically for JPA (org.springframework.orm.jpa.JpaTransactionManager). The bean is declared with
      * an ID of transactionManager assigned. 
	  *
	  * @return
	  */
	 @Bean
     public JpaTransactionManager transactionManager() {
		 JpaTransactionManager transactionManager = new JpaTransactionManager();
         transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
         return transactionManager;
     }
	 
	 /**
	  * The @Bean annotation is used to declare a Spring bean and the DI requirements. The @Bean annotation is equivalent to
	  * the <bean> tag, the method name is equivalent to the id attribute within the <bean> tag.
	  * 
	  * // XML:
	 		<bean id="vendorAdaptor" class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter"  />
	  */
	@Bean
	public HibernateJpaVendorAdapter vendorAdaptor() {
		 HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		 return vendorAdapter;
	}
	 
	/**
	 * JPA EntityManagerFactory bean (emf): 
     * 		The emf bean is the most important part. First, we declare the bean to use the 
     * LocalContainerEntityManagerFactoryBean. Within the bean, several properties are provided. First, as you might expected, 
     * we need to inject the datasource bean. Second, we configure the property jpaVendorAdapter with the class 
     * HibernateJpaVendorAdapter, because we are using Hibernate. Third, we instruct the entity factory to scan for the domain
     * objects with ORM annotations under the package com.softech.ls360.integration.regulators.model (specified by the 
     * <property name="packagesToScan"> tag). 
     * 
     * Note that this feature has been available only since Spring 3.1, and with the support of domain class scanning, you can
     * skip the definition of the persistence unit in the META-INF/persistence.xml file. Finally, the jpaProperties property 
     * provides configuration details for the persistence provider, Hibernate.
	 * 
	 * <bean id="ls360Emf" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean" 
        	p:dataSource-ref="ls360DataSource" 
        	p:jpaVendorAdapter-ref="vendorAdaptor"          
        	p:packagesToScan="com.softech.ls360.integration.regulators.model" 
        	p:jpaProperties-ref="jpaHibernateProperties" />
	 * 
	 * @return
	 */
	@Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		 
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdaptor());
        entityManagerFactoryBean.setDataSource(dataSource());
        entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        entityManagerFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);   
        entityManagerFactoryBean.setSharedCacheMode(SharedCacheMode.ENABLE_SELECTIVE);
        entityManagerFactoryBean.setValidationMode(ValidationMode.NONE);
        entityManagerFactoryBean.setJpaProperties(jpaHibernateProperties());
      
        return entityManagerFactoryBean;
     }
	 
	 
	 /**
	  *  hibernate.dialect 
                 The database dialect for the queries that Hibernate should use. Hibernate supports the SQL dialects for 
                 many databases. Those dialects are subclasses of org.hibernate.dialect.Dialect. Major dialects  include 
                 H2Dialect, Oracle10gDialect, PostgreSQLDialect, MySQLDialect, SQLServerDialect, and so on.
                 
                 Here we are using our own custom class because if our query have a sql server type nvarchar, then we will get an
                 error "No Dialect mapping for JDBC type: -9". So in our custom class we just register this type with hibernate.
                 
             hibernate.max_fetch_depth 
                 Declares the “depth” for outer joins when the mapping objects have associations with other mapped  objects. 
                 This setting prevents Hibernate from fetching too much data with a lot of nested  associations. A commonly 
                 used value is 3. 
                 
             hibernate.jdbc.fetch_size 
                 The number of records from the underlying JDBC resultset that Hibernate should use to retrieve the records
                 from the database for each fetch. For example, a query was submitted to the database, and the resultset 
                 contains 500 records. If the fetch size is 50, then Hibernate will need to fetch 10 times to get all the 
                 data.
                 
             hibernate.jdbc.batch_size 
                 Instructs Hibernate on the number of update operations that should be grouped together into a batch. This 
                 is very useful for performing batch job operations in Hibernate. Obviously, when we are doing a batch job 
                 updating hundreds of thousands of records, we would like Hibernate to group the queries in batches, rather
                 than submit the updates one by one.
                 
             hibernate.show_sql 
                 Indicates whether Hibernate should output the SQL queries to the log file or console. You should turn this 
                 on in a development environment, which can greatly help in the testing and troubleshooting process.
	  * 
	  *  <util:properties id="jpaHibernateProperties">
    		<prop key="hibernate.dialect">com.softech.ls360.database.dialect.SQLServerNativeDialect</prop>
    		<prop key="hibernate.max_fetch_depth">3</prop>
    		<prop key="hibernate.jdbc.fetch_size">50</prop>
    		<prop key="hibernate.jdbc.batch_size">10</prop>
    		<prop key="hibernate.show_sql">true</prop>
		</util:properties>
	  * 
	  * @return
	  */
	 private Properties jpaHibernateProperties() {
         Properties properties = new Properties();
         properties.put(PROPERTY_NAME_HIBERNATE_DIALECT, env.getProperty(PROPERTY_NAME_HIBERNATE_DIALECT));
         properties.put(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH, env.getProperty(PROPERTY_NAME_HIBERNATE_MAX_FETCH_DEPTH));
         properties.put(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_FETCH_SIZE));
         properties.put(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE, env.getProperty(PROPERTY_NAME_HIBERNATE_JDBC_BATCH_SIZE));
         
         return properties;       
	 }
	 
	
}
