package com.weatherbroker.controller;

import com.weatherbroker.exeption.CustomOrganizationException;

import com.weatherbroker.service.OrganisationService;
import com.weatherbroker.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "/api/organization")
public class OrganizationController {

    private final Logger logger = LoggerFactory.getLogger(OrganizationController.class);

    @Autowired
    private OrganisationService organizationService;

    /**
     * Поиск организации по нескольким параметрам
     * @param orgListViewRequest - объект содержащий параметры для поиска
     * @return список организаций подходящие критериям поиска
     */
    @PostMapping(value = "/list")
    public ResponseEntity searchOrganization(@RequestBody @Valid OrgListViewRequest orgListViewRequest) throws CustomOrganizationException, ParseException {
        List<OrgListViewResponse> listOrganizations = organizationService.search(orgListViewRequest);
        DataView<List<OrgListViewResponse>> dataView = new DataView<>(listOrganizations);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Поиск по Id организации
     * @param id - Id организации
     * @return - Организация найденная по id
     */
//    @GetMapping(path = "/{id}")
    @RequestMapping(value = "/{id}", method = {GET})
    public ResponseEntity findOrganizationById(@PathVariable Long id) throws CustomOrganizationException {
        OrgViewResponse orgViewResponse = organizationService.findById(id);
        DataView<OrgViewResponse> dataView = new DataView<>(orgViewResponse);
        return new ResponseEntity<>(dataView, HttpStatus.FOUND);
    }

    /**
     * Изменение(обновление) организации
     * @param orgViewRequest - объект содержащий параметры для обновления
     */
    @PostMapping(value = "/update")
    public ResponseEntity updaterOrganization(@RequestBody @Valid OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        organizationService.update(orgViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }

    /**
     * Сохранение организации
     * @param orgSaveViewRequest - объект содержащий параметры для сохранения
     */
    @PostMapping(value = "/save")
    public ResponseEntity saveOrganization(@RequestBody @Valid OrgSaveViewRequest orgSaveViewRequest) throws CustomOrganizationException, ParseException {
        organizationService.save(orgSaveViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.CREATED);
    }

    /**
     * Удаление организации
     * @param orgViewRequest - объект содержащий id организации
     */
    @PostMapping(value = "/delete")
    public ResponseEntity deleteOrganization(@RequestBody OrgViewRequest orgViewRequest) throws CustomOrganizationException, ParseException {
        organizationService.delete(orgViewRequest);
        return new ResponseEntity<>(new PositiveResponseView(), HttpStatus.OK);
    }
}
