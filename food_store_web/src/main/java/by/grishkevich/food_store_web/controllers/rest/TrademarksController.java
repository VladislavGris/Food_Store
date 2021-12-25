package by.grishkevich.food_store_web.controllers.rest;

import by.grishkevich.food_store_data.models.Trademark;
import by.grishkevich.food_store_data.services.data.implementation.TrademarkJPAService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("api/trademarks")
public class TrademarksController {

    private TrademarkJPAService trademarkService;

    public TrademarksController(TrademarkJPAService trademarkService){
        this.trademarkService = trademarkService;
    }

    @GetMapping
    public Iterable<Trademark> getTrademarks(){
        return trademarkService.getAllTrademarks();
    }

    @GetMapping("/{id}")
    public Trademark getTrademark(@PathVariable Long id){
        return trademarkService.getById(id);
    }

    @PostMapping
    public Trademark newTrademark(@Valid @RequestBody Trademark trademark){
        return trademarkService.save(trademark);
    }

    @PutMapping("/{id}")
    public Trademark updateTrademark(@Valid @RequestBody Trademark trademark, @PathVariable Long id){
        return trademarkService.update(trademark,id);
    }

    @DeleteMapping("/{id}")
    public void deleteTrademark(@PathVariable Long id){
        trademarkService.delete(id);
    }

}
