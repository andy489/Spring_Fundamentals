package com.reseller.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor(staticName = "of")
@NoArgsConstructor
public class OfferModel {

    private List<OfferDto> myOffers;

    private List<OfferDto> boughtOffers;

    private List<OfferDto> otherOffers;

}
