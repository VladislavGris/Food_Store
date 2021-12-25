package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.exceptions.CountryNotFoundException;
import by.grishkevich.food_store_data.models.Country;
import by.grishkevich.food_store_data.repositories.CountryRepository;
import by.grishkevich.food_store_data.services.data.base.Countryservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CountryJPAService implements Countryservice {

    private final CountryRepository countryRepository;

    public CountryJPAService(CountryRepository countryRepository){
        this.countryRepository = countryRepository;
    }

    @Override
    public Iterable<Country> getAllCountries() {
        return countryRepository.findAll();
    }

    @Override
    public Country getById(Long id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country update(Country updCountry, Long id) {
        return countryRepository.findById(id)
                .map(country -> {
                    country.setName(updCountry.getName());
                    return country;
                })
                .orElseThrow(() -> new CountryNotFoundException(id));
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public void delete(Long id) {
        countryRepository.deleteById(id);
    }
}
