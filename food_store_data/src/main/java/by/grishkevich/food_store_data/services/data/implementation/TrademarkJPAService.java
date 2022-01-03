package by.grishkevich.food_store_data.services.data.implementation;

import by.grishkevich.food_store_data.exceptions.TrademarkNotFoundException;
import by.grishkevich.food_store_data.models.Trademark;
import by.grishkevich.food_store_data.repositories.TrademarkRepository;
import by.grishkevich.food_store_data.services.data.base.TrademarkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
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
        return trademarkRepository.findById(id)
                .orElseThrow(() -> new TrademarkNotFoundException(id));
    }

    @Override
    public Trademark update(Trademark updTrademark, Long id) {
        return trademarkRepository.findById(id)
                .map(trademark -> {
                    trademark.setName(updTrademark.getName());
                    return trademarkRepository.save(trademark);
                })
                .orElseThrow(() -> new TrademarkNotFoundException(id));
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
