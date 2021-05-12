package com.example.datagemfire;

import org.apache.geode.cache.client.ClientRegionShortcut;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.gemfire.config.annotation.ClientCacheApplication;
import org.springframework.data.gemfire.config.annotation.EnableEntityDefinedRegions;
import org.springframework.data.gemfire.repository.config.EnableGemfireRepositories;

import java.util.Arrays;
import java.util.stream.StreamSupport;

@SpringBootApplication
@ClientCacheApplication(name = "AccessingDataGemFireApp")
@EnableEntityDefinedRegions(
        basePackageClasses = Person.class,
        clientRegionShortcut = ClientRegionShortcut.LOCAL
)
@EnableGemfireRepositories
public class DataGemfireApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataGemfireApplication.class, args);
    }

    @Bean
    ApplicationRunner run(PersonRepository personRepository) {
        return args -> {
            Person alice = new Person("Adult Alice", 40);
            Person bob = new Person("Baby Bob", 1);
            Person carol = new Person("Teen Carol", 13);

            System.out.println("Before accessing data in Apache Geode...");

            Arrays.asList(alice, bob, carol).forEach(person -> System.out.println("\t" + person));

            System.out.println("Saving Alice, Bob and Carol to Pivotal GemFire...");

            personRepository.save(alice);
            personRepository.save(bob);
            personRepository.save(carol);

            System.out.println("Lookup each person by name...");

            Arrays.asList(alice.getName(), bob.getName(), carol.getName())
                    .forEach(name -> System.out.println("\t" + personRepository.findByName(name)));

            System.out.println("Query adults (over 18):");

            StreamSupport.stream(personRepository.findByAgeGreaterThan(18).spliterator(), false)
                    .forEach(person -> System.out.println("\t" + person));

            System.out.println("Query babies (less than 5):");

            StreamSupport.stream(personRepository.findByAgeLessThan(5).spliterator(), false)
                    .forEach(person -> System.out.println("\t" + person));

            System.out.println("Query teens (between 12 and 20):");

            StreamSupport.stream(personRepository.findByAgeGreaterThanAndAgeLessThan(12, 20).spliterator(), false)
                    .forEach(person -> System.out.println("\t" + person));
        };
    }

}
