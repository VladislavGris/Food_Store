package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.models.Country;
import by.grishkevich.food_store_data.services.data.implementation.CountryJPAService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/countries")
public class CountriesController {

    private CountryJPAService countryService;

    public CountriesController(CountryJPAService countryService){
        this.countryService = countryService;
    }

    @GetMapping
    public Iterable<Country> getCountries(){
        return countryService.getAllCountries();
    }

    @GetMapping("/{id}")
    public Country getCountry(@PathVariable Long id){
        return countryService.getById(id);
    }

    @PostMapping
    public Country newCountry(@RequestBody Country country){
        return countryService.save(country);
    }

    @PutMapping("/{id}")
    public Country updateCountry(@RequestBody Country country, @PathVariable Long id){
        return countryService.update(country,id);
    }

    @DeleteMapping("/{id}")
    public void deleteCountry(@PathVariable Long id){
        countryService.delete(id);
    }
}
