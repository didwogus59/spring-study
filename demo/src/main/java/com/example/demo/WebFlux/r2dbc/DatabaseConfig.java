// package com.example.demo.WebFlux.r2dbc;


// import org.springframework.context.annotation.Bean;

// import io.r2dbc.spi.ConnectionFactories;

// // import java.io.IOException;
// // import java.io.UncheckedIOException;
// // import java.nio.file.Files;
// // import java.nio.file.Path;
// // import java.util.Properties;

// import io.r2dbc.spi.ConnectionFactory;
// /**
//  * <p>
//  * Configuration for connecting code samples to an Oracle Database instance.
//  * </p><p>
//  * The configuration is read from a properties file in the current directory
//  * by default, or from a file specified as
//  * <code>-DCONFIG_FILE=/path/to/your/config.properties</code>
//  * </p>
//  */
// import io.r2dbc.spi.ConnectionFactoryOptions;
// public class DatabaseConfig {
//   @Bean
//   ConnectionFactory ConnectionFactory() {
//     return ConnectionFactories.get(ConnectionFactoryOptions.parse("r2dbc:://localhost:1521/xe")
//             .mutate()
//             .option(ConnectionFactoryOptions.USER, "system")
//             .option(ConnectionFactoryOptions.PASSWORD, "1")
//             .build());
//   }
// }


//   // /** Path to a configuration file: config.properties */
//   // private static final Path CONFIG_PATH =
//   //   Path.of(System.getProperty("CONFIG_FILE", "oracle.properties"));

//   // /** Configuration that is read from a file at {@link #CONFIG_PATH} */
//   // private static final Properties CONFIG;
//   // static {
//   //   try (var fileStream = Files.newInputStream(CONFIG_PATH)) {
//   //     CONFIG = new Properties();
//   //     CONFIG.load(fileStream);
//   //   }
//   //   catch (IOException readFailure) {
//   //     throw new UncheckedIOException(readFailure);
//   //   }
//   // }

//   // /** Host name where an Oracle Database instance is running */
//   // static final String HOST = CONFIG.getProperty("oracle_host");

//   // /** Port number where an Oracle Database instance is listening */
//   // static final int PORT = Integer.parseInt(CONFIG.getProperty("oracle_port"));

//   // /** Service name of an Oracle Database */
//   // static final String SERVICE_NAME = CONFIG.getProperty("oracle_name");

//   // /** User name that connects to an Oracle Database */
//   // static final String USER = CONFIG.getProperty("oracle_user");

//   // /** Password of the user that connects to an Oracle Database */
//   // static final String PASSWORD = CONFIG.getProperty("oracle_password");

//   // // /** The file system path of a wallet directory */
//   // // static final String WALLET_LOCATION =
//   // //   CONFIG.getProperty("WALLET_LOCATION");
//   // private static final String DESCRIPTOR = "(DESCRIPTION=" +
//   //   "(ADDRESS=(HOST="+HOST+")(PORT="+PORT+")(PROTOCOL=tcp))" +
//   //   "(CONNECT_DATA=(SERVICE_NAME="+SERVICE_NAME+")))";

//   // String r2dbcUrl = "r2dbc:oracle://?oracle.r2dbc.descriptor="+DESCRIPTOR;