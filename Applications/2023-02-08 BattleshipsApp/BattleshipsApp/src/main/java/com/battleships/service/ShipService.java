package com.battleships.service;

import com.battleships.domain.dto.BattleDto;
import com.battleships.domain.dto.ShipAddDto;
import com.battleships.domain.dto.ShipDto;
import com.battleships.domain.dto.UserDto;
import com.battleships.domain.dto.UserWithShipsDto;
import com.battleships.mapper.MapStructMapper;
import com.battleships.domain.entity.ShipEntity;
import com.battleships.session.CurrentUser;
import com.battleships.repository.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ShipService {

    private final ShipRepository shipRepository;
    private final MapStructMapper mapper;
    private final CurrentUser currentUser;
    private final UserService userService;
    private final CategoryService categoryService;

    @Autowired
    public ShipService(
            ShipRepository shipRepository,

            MapStructMapper mapper,
            CurrentUser currentUser,

            UserService userService,
            CategoryService categoryService
    ) {
        this.shipRepository = shipRepository;
        this.mapper = mapper;
        this.currentUser = currentUser;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public Optional<ShipEntity> getByName(String name) {

        return shipRepository.findByName(name);
    }

    public Boolean addShip(ShipAddDto shipAddDto) {
        if (!currentUser.isLoggedIn()) {
            return false;
        }

        ShipEntity newShip = mapper.toEntity(shipAddDto);

        newShip.setOwner(userService.getById(currentUser.getId()));
        newShip.setCategory(categoryService.getByShipType(shipAddDto.getShipType()));

        shipRepository.saveAndFlush(newShip);

        return true;
    }

    public UserWithShipsDto getUserWithShips(Boolean logged) {

        if (!currentUser.isLoggedIn()) {
            return UserWithShipsDto.of(new UserDto(), List.of());
        }


        UserDto byId = mapper.toDto(userService.getById(currentUser.getId()));

        List<ShipDto> listShipsDto;

        if (logged) {
            listShipsDto = mapper.toDtoList(shipRepository.findAllByOwnerId(currentUser.getId()).orElseThrow(NoSuchElementException::new));
        } else {
            listShipsDto = mapper.toDtoList(shipRepository.findAllByOwnerIdNot(currentUser.getId()).orElseThrow(NoSuchElementException::new));
        }

        return UserWithShipsDto.of(byId, listShipsDto);
    }

    public List<ShipEntity> getAllSorted() {
        return shipRepository.findAllByOrderByNameAscHealthAscPowerAsc();
    }


    public void fight(BattleDto battleDto) {
        Long leftId = battleDto.getLoggedUserShipId();
        Long rightId = battleDto.getNotLoggedUserShipId();

        ShipEntity leftShip = shipRepository.findById(leftId).orElseThrow(NoSuchElementException::new);
        ShipEntity rightShip = shipRepository.findById(rightId).orElseThrow(NoSuchElementException::new);

        leftShip.setHealth(leftShip.getHealth() - rightShip.getPower());
        rightShip.setHealth(rightShip.getHealth() - leftShip.getPower());

        updateShip(leftId, leftShip);
        updateShip(rightId, rightShip);
    }

    private void updateShip(Long id, ShipEntity ship) {
        if (ship.getHealth() <= 0) {
            shipRepository.deleteById(id);
        } else {
            shipRepository.saveAndFlush(ship);
        }
    }

}
