package demoapp.service;

import org.springframework.stereotype.Service;

@Service
public class DespedidaService {
    public String despedir(String nombre) {
        return "Bye-bye  " + nombre;
    }
}
