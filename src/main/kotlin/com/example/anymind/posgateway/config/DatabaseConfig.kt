package com.example.anymind.posgateway.config

import com.baidu.fsg.uid.worker.dao.WorkerNodeDAO
import com.example.anymind.posgateway.mapper.PaymentMapper
import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.mybatis.spring.mapper.MapperScannerConfigurer
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.datasource.DataSourceTransactionManager
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@Configuration
@EnableTransactionManagement
//@MapperScan(basePackageClasses = [PaymentMapper::class, WorkerNodeDAO::class])
@MapperScan(value = ["com.baidu.fsg.uid.worker.dao", "com.example.anymind.posgateway.mapper"])
class DatabaseConfig {
    @Bean
    fun transactionManager(dataSource: DataSource) = DataSourceTransactionManager(dataSource)

    @Bean("sqlSessionFactory")
    fun sqlSessionFactory(
        dataSource: DataSource
    ): SqlSessionFactory {
        val sqlSessionFactoryBean = SqlSessionFactoryBean()
        sqlSessionFactoryBean.setDataSource(dataSource)
        val resolver = PathMatchingResourcePatternResolver()
        sqlSessionFactoryBean.setMapperLocations(*resolver.getResources("classpath*:/META-INF/mybatis/mapper/WORKER*.xml"));
        return sqlSessionFactoryBean.getObject()!!
    }

//    @Bean
//    fun mapperScanner(): MapperScannerConfigurer {
//        val mapperScanner = MapperScannerConfigurer()
//        mapperScanner.setBasePackage("com.baidu.fsg.uid.worker.dao")
//        mapperScanner.setSqlSessionFactoryBeanName("sqlSessionFactory")
//        mapperScanner.setAnnotationClass(org.springframework.stereotype.Repository::class.java)
//        return mapperScanner
//    }
}