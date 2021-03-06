package com.josdugan.beerinventoryservice.mappers;

import com.josdugan.beerinventoryservice.domain.BeerInventory;
import com.josdugan.beerworkscommon.dtos.BeerInventoryDto;
import org.mapstruct.Mapper;

@Mapper(uses = DateMapper.class)
public interface BeerInventoryMapper {

    BeerInventory beerInventoryDtoToBeerInventory(BeerInventoryDto beerInventoryDto);

    BeerInventoryDto beerInventoryToBeerInventoryDto(BeerInventory beerInventory);
}
