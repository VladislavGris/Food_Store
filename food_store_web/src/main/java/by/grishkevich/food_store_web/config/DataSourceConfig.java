package by.grishkevich.food_store_web.config;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public DataSource getDataSource(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSourceBuilder.url("jdbc:sqlserver://DESKTOP-LRSVKUO;databaseName=FoodStore");
        dataSourceBuilder.username("food_store_user");
        dataSourceBuilder.password("abcd0503");
        return dataSourceBuilder.build();
    }
}
