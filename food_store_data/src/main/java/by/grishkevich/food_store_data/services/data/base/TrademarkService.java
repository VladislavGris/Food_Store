package by.grishkevich.food_store_data.services.data.base;

import by.grishkevich.food_store_data.models.Trademark;

public interface TrademarkService {
    Iterable<Trademark> getAllTrademarks();
    Trademark getById(Long id);
    Trademark update(Trademark updTrademark, Long id);
    Trademark save(Trademark trademark);
    void delete(Trademark trademark);
}
