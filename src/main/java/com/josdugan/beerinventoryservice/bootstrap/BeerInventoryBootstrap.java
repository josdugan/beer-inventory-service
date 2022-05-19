package com.josdugan.beerinventoryservice.bootstrap;

import com.josdugan.beerinventoryservice.domain.BeerInventory;
import com.josdugan.beerinventoryservice.repositories.BeerInventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Component
public class BeerInventoryBootstrap implements CommandLineRunner {

    public static final String BEER_1_UPC = "012345678911";
    public static final String BEER_2_UPC = "012345678922";
    public static final String BEER_3_UPC = "012345678933";
    public static final UUID BEER_1_UUID = UUID.fromString("0a818933-087d-47f2-ad83-2f986ed087eb");
    public static final UUID BEER_2_UUID = UUID.fromString("a712d914-61ea-4623-8bd0-32c0f6545bfd");
    public static final UUID BEER_3_UUID = UUID.fromString("026cc3c8-3a0c-4083-a05b-e908048c1b08");

    private final BeerInventoryRepository beerInventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        if (beerInventoryRepository.count() == 0) {
            loadInitialInventory();
        }
    }

    private void loadInitialInventory() {
        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(BEER_1_UUID)
                .upc(BEER_1_UPC)
                .quantityOnHand(50)
                .build()
        );

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(BEER_2_UUID)
                .upc(BEER_2_UPC)
                .quantityOnHand(50)
                .build()
        );

        beerInventoryRepository.save(BeerInventory.builder()
                .beerId(BEER_3_UUID)
                .upc(BEER_3_UPC)
                .quantityOnHand(50)
                .build()
        );

        log.debug("Loaded Inventory. Record count: " + beerInventoryRepository.count());

    }
}
