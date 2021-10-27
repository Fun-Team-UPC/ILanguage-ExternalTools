package com.edu.upc.ilanguageexternaltool.query.api;

import com.edu.upc.ilanguageexternaltool.query.projections.ExternalToolHistoryView;
import com.edu.upc.ilanguageexternaltool.query.projections.ExternalToolHistoryViewRepository;
import com.edu.upc.ilanguageexternaltool.query.projections.ExternalToolView;
import com.edu.upc.ilanguageexternaltool.query.projections.ExternalToolViewRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/externaltool")
@Api(tags = "ExternalTool")
public class ExternalToolQueryController {
    private final ExternalToolViewRepository externalToolViewRepository;
    private final ExternalToolHistoryViewRepository externalToolHistoryViewRepository;

    public ExternalToolQueryController(ExternalToolViewRepository externalToolViewRepository, ExternalToolHistoryViewRepository externalToolHistoryViewRepository) {
        this.externalToolViewRepository = externalToolViewRepository;
        this.externalToolHistoryViewRepository = externalToolHistoryViewRepository;
    }

    @Operation(summary="Get all external tools", description="This endpoint returns all the available external tools for Ilanguage Application", tags = {"ExternalTool"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="All ExternalTool returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="ExternalTool Not Found", content = @Content(mediaType = "application/json"))

    })
    @GetMapping("")
    public ResponseEntity<List<ExternalToolView>> getAll(){
        try {
            return new ResponseEntity<List<ExternalToolView>>(externalToolViewRepository.findAll(), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary="Get external tool by id", description="This endpoint returns an specific external tool by the given ID Ilanguage Application", tags = {"ExternalTool"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="ExternalTool returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="ExternalTool Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping(value= "id/{id}", method = RequestMethod.GET)
    public ResponseEntity<ExternalToolView> getById(@PathVariable(name="id") String subscriptionId){
        try {
            return new ResponseEntity<ExternalToolView>(externalToolViewRepository.getById(subscriptionId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Operation(summary="Get external tool by name", description="This endpoint returns an specific external tool by the given ID Ilanguage Application", tags = {"ExternalTool"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="ExternalTool returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="ExternalTool Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping( value = "name/{name}",method = RequestMethod.GET)
    public ResponseEntity<ExternalToolView> getByName(@PathVariable(name="name") String externalToolName){
        try {
            return new ResponseEntity<ExternalToolView>(externalToolViewRepository.findByName(externalToolName), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Operation(summary="Get history external tool by id", description="This endpoint returns the list with the history of an specific external tool by the given ID Ilanguage Application", tags = {"ExternalTool"} )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description="ExternalTool returned", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description="ExternalTool Not Found", content = @Content(mediaType = "application/json"))

    })
    @RequestMapping(value= "historyid/{historyid}", method = RequestMethod.GET)
    public ResponseEntity<List<ExternalToolHistoryView>> getHistoryById(@PathVariable(name="historyid") String externalToolId){
        try {
            return new ResponseEntity<List<ExternalToolHistoryView>>(externalToolHistoryViewRepository.getExternalToolHistoryById(externalToolId), HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
