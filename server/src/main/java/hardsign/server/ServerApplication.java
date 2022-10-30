package hardsign.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan
@EnableJpaRepositories
public class ServerApplication {
	public static void main(String[] args) {
		try
		{
			SpringApplication.run(ServerApplication.class, args);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
