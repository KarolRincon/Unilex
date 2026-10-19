package com.unilex.app.Unilex.config;

import com.unilex.app.Unilex.model.Abogado;
import com.unilex.app.Unilex.repository.AbogadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private AbogadoRepository abogadoRepository;

    @Override
    public void run(String... args) throws Exception {
        if (abogadoRepository.count() == 0) {
            abogadoRepository.save(new Abogado(null, "Dra. Carolina Martínez", "carolina@unilex.co", "Derecho Civil"));
            abogadoRepository.save(new Abogado(null, "Dr. Carlos Rodríguez", "carlos@unilex.co", "Derecho Laboral"));
        }
    }
}