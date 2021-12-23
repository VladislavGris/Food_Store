package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.models.Trademark;
import by.grishkevich.food_store_data.repositories.TrademarkRepository;
import by.grishkevich.food_store_data.services.data.base.TrademarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@Slf4j
public class TrademarkJPAService implements TrademarkService {

    private final TrademarkRepository trademarkRepository;

    public TrademarkJPAService(TrademarkRepository trademarkRepository){
        this.trademarkRepository = trademarkRepository;
    }

    @Override
    public Iterable<Trademark> getAllTrademarks() {
        return trademarkRepository.findAll();
    }

    @Override
    public Trademark getById(Long id) {
        try{
            return trademarkRepository.findById(id).get();
        }catch (NoSuchElementException ex){
            return null;
        }
    }

    @Override
    public Trademark update(Trademark updTrademark, Long id) {
        return trademarkRepository.findById(id)
                .map(trademark -> {
                    trademark.setName(updTrademark.getName());
                    return trademark;
                }).orElseGet(() -> {
                    updTrademark.setId(id);
                    return trademarkRepository.save(updTrademark);
                });
    }

    @Override
    public Trademark save(Trademark trademark) {
        return trademarkRepository.save(trademark);
    }

    @Override
    public void delete(Long id) {
        trademarkRepository.deleteById(id);
    }
}
