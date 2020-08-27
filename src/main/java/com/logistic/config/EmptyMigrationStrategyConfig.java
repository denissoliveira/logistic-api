package com.logistic.config;

/**
 * Desabilitar a migração automática Flyway na inicialização, mas ainda sermos capazes de acionar a migração manualmente
 * 
 * @author denis
 *
 */
//@Configuration
public class EmptyMigrationStrategyConfig {
	
//	@Bean
//    public FlywayMigrationStrategy flywayMigrationStrategy() {
//        return flyway -> {
//        };
//    }
	
	/**
	 * Faça isso em uma classe de teste
	 */
//	@Autowired
//    private Flyway flyway;
// 
//    @Test
//    public void skipAutomaticAndTriggerManualFlywayMigration() {
//        flyway.migrate();
//    }

}
