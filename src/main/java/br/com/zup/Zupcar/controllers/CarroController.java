package br.com.zup.Zupcar.controllers;

import br.com.zup.Zupcar.dtos.CarroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carros")
public class CarroController {

    private List<CarroDTO> concessionaria = new ArrayList<>();

    @GetMapping("/gol")
    public CarroDTO exibirGol() {
        CarroDTO gol = new CarroDTO("fiat", "cinza", "1.6", 2021);
        return gol;
    }

    @GetMapping
    public List<CarroDTO> exibirCarros() {
        return concessionaria;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCarro(@RequestBody CarroDTO carroDTO) {
        concessionaria.add(carroDTO);
    }

    @GetMapping("/{nomeDoCarro}")
    public CarroDTO exibirCarro(@PathVariable String nomeDoCarro) {
//        String carro = String.format("{nomeDoCarro");
        for (CarroDTO objeto : concessionaria) {
            if (objeto.getModelo().equalsIgnoreCase(nomeDoCarro)) {
                return objeto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/{nomeDoCarro}")
    public CarroDTO atualizarCarro(@PathVariable String nomeDoCarro, @RequestBody CarroDTO carro) {
        for (CarroDTO objeto : concessionaria) {
            if (objeto.getModelo().equalsIgnoreCase(nomeDoCarro)) {
                objeto.setModelo(nomeDoCarro);
                return objeto;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}

