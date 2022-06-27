package com.josdugan.beerinventoryservice.services;

import com.josdugan.beerworkscommon.dtos.BeerOrderDto;

public interface AllocationService {

    Boolean allocateOrder(BeerOrderDto beerOrderDto);
}
