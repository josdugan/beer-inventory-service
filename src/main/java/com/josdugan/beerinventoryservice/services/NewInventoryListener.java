package com.josdugan.beerinventoryservice.services;

import com.josdugan.beerinventoryservice.domain.BeerInventory;
import com.josdugan.beerinventoryservice.repositories.BeerInventoryRepository;
import com.josdugan.beerworkscommon.constants.MessageQueues;
import com.josdugan.beerworkscommon.dtos.BeerDto;
import com.josdugan.beerworkscommon.events.NewInventoryEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class NewInventoryListener {

    private final BeerInventoryRepository beerInventoryRepository;

    @JmsListener(destination = MessageQueues.NEW_INVENTORY_QUEUE)
    public void listen(NewInventoryEvent event) {
        log.debug("Got Inventory: " + event.toString());

        BeerDto beerDto = event.getBeerDto();

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(beerDto.getId())
                .upc(beerDto.getUpc())
                .quantityOnHand(beerDto.getQuantityOnHand())
                .build());
    }
}
