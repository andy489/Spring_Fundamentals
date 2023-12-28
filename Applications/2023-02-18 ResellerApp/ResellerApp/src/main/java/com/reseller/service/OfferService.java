package com.reseller.service;

import com.reseller.mapper.MapStructMapper;
import com.reseller.model.dto.OfferAddDto;
import com.reseller.model.view.OfferModel;
import com.reseller.model.entity.OfferEntity;
import com.reseller.repository.OfferRepository;
import com.reseller.session.CurrentUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OfferService {

    private final OfferRepository offerRepository;
    private final CurrentUser currentUser;
    private final MapStructMapper mapper;
    private final UserService userService;
    private final ConditionService conditionService;
    private final Logger LOGGER = LoggerFactory.getLogger(OfferService.class);

    @Autowired
    public OfferService(
            OfferRepository offerRepository,
            CurrentUser currentUser,
            MapStructMapper mapper,
            UserService userService,
            ConditionService conditionService
    ) {
        this.offerRepository = offerRepository;
        this.currentUser = currentUser;
        this.mapper = mapper;
        this.userService = userService;
        this.conditionService = conditionService;
    }

    public Boolean addOffer(OfferAddDto offerAddDto) {
        if (!currentUser.isLoggedIn()) {
            return false;
        }

        OfferEntity newOffer = mapper.toEntity(offerAddDto);

        newOffer.setOwner(userService.getById(currentUser.getId()));
        newOffer.setCondition(conditionService.getByConditionName(offerAddDto.getConditionName()));

        offerRepository.saveAndFlush(newOffer);

        return true;
    }

    public OfferModel getOffers() {
        Long currentId = currentUser.getId();

        OfferModel offerModel = new OfferModel();

        offerModel.setMyOffers(offerRepository.findByOwnerId(currentId).stream().map(mapper::toDto).toList());
        offerModel.setBoughtOffers(offerRepository.findByBuyerId(currentId).stream().map(mapper::toDto).toList());
        offerModel.setOtherOffers(offerRepository.findByOwnerIdNotAndBuyerIdEquals(currentId, null)
                .stream().map(mapper::toDto).toList());

        return offerModel;
    }

    public Boolean removeOffer(Long offerId) {

        if (!checkLoggedInAndNonNegativeId(offerId)) {
            return false;
        }

        Optional<OfferEntity> byId = offerRepository.findById(offerId);

        if (byId.isEmpty()) {
            return false;
        }

        OfferEntity offerEntity = byId.get();

        if (!currentUser.getId().equals(offerEntity.getOwner().getId())) {
            return false;
        }

        offerRepository.deleteById(offerId);

        LOGGER.info("User [{}] with id [{}] removed offer with id [{}].",
                currentUser.getUsername(), currentUser.getId(), offerEntity.getId());

        return true;
    }

    public Boolean buyOffer(Long offerId) {
        if (!checkLoggedInAndNonNegativeId(offerId)) {
            return false;
        }

        Optional<OfferEntity> byId = offerRepository.findById(offerId);

        if (byId.isEmpty()) {
            return false;
        }

        OfferEntity offerEntity = byId.get();

        if (currentUser.getId().equals(offerEntity.getOwner().getId())) {
            return false;
        }

        offerEntity.setBuyer(userService.getById(currentUser.getId()));
        offerRepository.saveAndFlush(offerEntity);

        LOGGER.info("User [{}] with id [{}] bought offer with id [{}].",
                currentUser.getUsername(), currentUser.getId(), offerEntity.getId());

        return true;
    }

    private Boolean checkLoggedInAndNonNegativeId(Long offerId) {
        return currentUser.getLoggedIn() && offerId > 0;
    }
}
