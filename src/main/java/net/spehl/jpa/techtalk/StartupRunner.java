package net.spehl.jpa.techtalk;

import net.spehl.jpa.techtalk.model.Person;
import net.spehl.jpa.techtalk.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

@Component
public class StartupRunner implements CommandLineRunner {

    @Autowired
    PersonRepository repo;

    @Override
    public void run(String... strings) throws Exception {
        Person p = new Person("name", 100.0, UUID.randomUUID());

        repo.save(p);
    }
}
