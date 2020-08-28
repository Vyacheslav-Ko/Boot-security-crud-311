package web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
    @ComponentScan(value = "web") //2708
    @PropertySource("classpath:db.properties")
    @EnableTransactionManagement(proxyTargetClass = true) //скобки 2708
    @EnableJpaRepositories("web")
    public class AppContext {


        private Environment environment;

        @Autowired
        public AppContext (Environment environment) {
            this.environment = environment;
        }

        private Properties hibernateProperties() {
            Properties props = new Properties();
            props.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
            props.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
            props.put("hibernate.hbm2ddl.auto", environment.getRequiredProperty("hibernate.hbm2ddl.auto"));
            return props;
        }

        @Bean
        public DataSource dataSource() {
            DriverManagerDataSource dataSource = new DriverManagerDataSource();
            dataSource.setDriverClassName(environment.getProperty("db.driver"));
            dataSource.setUrl(environment.getProperty("db.url"));
            dataSource.setUsername(environment.getProperty("db.username"));
            dataSource.setPassword(environment.getProperty("db.password"));
            return dataSource;
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
            LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
            entityManagerFactoryBean.setDataSource(dataSource());
            entityManagerFactoryBean.setPackagesToScan("web");
            JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
            entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);
            entityManagerFactoryBean.setJpaProperties(hibernateProperties());
            return entityManagerFactoryBean;
        }

        @Bean
        public JpaTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
            JpaTransactionManager jpaTransactionManager = new JpaTransactionManager();
            jpaTransactionManager.setEntityManagerFactory(entityManagerFactory);
            return jpaTransactionManager;
        }
}
