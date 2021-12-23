package by.grishkevich.food_store_data.services.data.base;

import by.grishkevich.food_store_data.models.Country;

public interface Countryservice {
    Iterable<Country> getAllCountries();
    Country getById(Long id);
    Country update(Country updCountry,Long id);
    Country save(Country country);
    void delete(Long id);
}
